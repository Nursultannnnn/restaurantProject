package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.entity.Category;
import peaksoft.restaurantproject.repository.CategoryRepo;
import peaksoft.restaurantproject.service.CategoryService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    @Override
    public Category saveCategory(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepo.findById(id).orElseThrow(() ->
                new RuntimeException("Category with id: " + id + " not found"));
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category oldCategory = getCategoryById(id);
        oldCategory.setName(category.getName());
        return categoryRepo.save(oldCategory);
    }

    @Override
    public String deleteCategory(Long id) {
        if (!categoryRepo.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepo.deleteById(id);
        return "Category with id: " + id + " successfully deleted";
    }
}