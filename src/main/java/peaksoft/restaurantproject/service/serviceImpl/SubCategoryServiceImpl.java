package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.subCategory.SubCategoryRequest;
import peaksoft.restaurantproject.dto.subCategory.SubCategoryResponse;
import peaksoft.restaurantproject.entity.Category;
import peaksoft.restaurantproject.entity.SubCategory;
import peaksoft.restaurantproject.repository.CategoryRepo;
import peaksoft.restaurantproject.repository.SubCategoryRepo;
import peaksoft.restaurantproject.service.SubCategoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public SubCategoryResponse saveSubCategory(Long categoryId, SubCategoryRequest request) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() ->
                new RuntimeException("Категория с id: " + categoryId + " не найдена"));

        SubCategory subCategory = new SubCategory();
        subCategory.setName(request.name());
        subCategory.setCategory(category);

        SubCategory savedSubCategory = subCategoryRepo.save(subCategory);
        return mapToResponse(savedSubCategory);
    }

    @Override
    public List<SubCategoryResponse> getAllSubCategoriesByCategoryId(Long categoryId) {
        if (!categoryRepo.existsById(categoryId)) {
            throw new RuntimeException("Категория с id: " + categoryId + " не найдена");
        }

        return subCategoryRepo.findAllByCategoryId(categoryId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SubCategoryResponse getSubCategoryById(Long id) {
        SubCategory subCategory = subCategoryRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Подкатегория с id: " + id + " не найдена"));
        return mapToResponse(subCategory);
    }

    @Override
    public SubCategoryResponse updateSubCategory(Long id, SubCategoryRequest request) {
        SubCategory subCategory = subCategoryRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Подкатегория с id: " + id + " не найдена"));

        subCategory.setName(request.name());
        return mapToResponse(subCategoryRepo.save(subCategory));
    }

    @Override
    public SimpleResponse deleteSubCategory(Long id) {
        if (!subCategoryRepo.existsById(id)) {
            throw new RuntimeException("Подкатегория не найдена");
        }
        subCategoryRepo.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Подкатегория успешно удалена")
                .build();
    }

    private SubCategoryResponse mapToResponse(SubCategory subCategory) {
        return SubCategoryResponse.builder()
                .id(subCategory.getId())
                .name(subCategory.getName())
                .categoryId(subCategory.getCategory() != null ? subCategory.getCategory().getId() : null)
                .categoryName(subCategory.getCategory() != null ? subCategory.getCategory().getName() : null)
                .build();
    }
}