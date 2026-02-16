package peaksoft.restaurantproject.service.serviceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import peaksoft.restaurantproject.entity.Category;
import peaksoft.restaurantproject.entity.SubCategory;
import peaksoft.restaurantproject.repository.CategoryRepo;
import peaksoft.restaurantproject.repository.SubCategoryRepo;
import peaksoft.restaurantproject.service.SubCategoryService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepo subCategoryRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public SubCategory saveSubCategory(Long categoryId, SubCategory subCategory) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() ->
                new RuntimeException("Category not found"));
        // Устанавливаем связь
        subCategory.setCategory(category);
        return subCategoryRepo.save(subCategory);
    }

    @Override
    public List<SubCategory> getAllSubCategoriesByCategoryId(Long categoryId) {
        return List.of();
    }

    @Override
    public SubCategory getSubCategoryById(Long id) {
        return subCategoryRepo.findById(id).orElseThrow(() ->
                new RuntimeException("SubCategory not found"));
    }

    @Override
    public SubCategory updateSubCategory(Long id, SubCategory subCategory) {
        return null;
    }

    @Override
    public String deleteSubCategory(Long id) {
        return "";
    }

    // Остальные методы (update, delete) по аналогии
}