package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.category.CategoryRequest;
import peaksoft.restaurantproject.dto.category.CategoryResponse;
import peaksoft.restaurantproject.entity.Category;
import peaksoft.restaurantproject.repository.CategoryRepo;
import peaksoft.restaurantproject.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.name());

        Category savedCategory = categoryRepo.save(category);
        return mapToResponse(savedCategory);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepo.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Категория с id: " + id + " не найдена"));
        return mapToResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {
        Category category = categoryRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Категория с id: " + id + " не найдена"));

        category.setName(categoryRequest.name());
        return mapToResponse(categoryRepo.save(category));
    }

    @Override
    public SimpleResponse deleteCategory(Long id) {
        if (!categoryRepo.existsById(id)) {
            throw new RuntimeException("Категория не найдена");
        }
        categoryRepo.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Категория успешно удалена")
                .build();
    }

    private CategoryResponse mapToResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}