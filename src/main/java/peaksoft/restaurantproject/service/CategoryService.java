package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.entity.Category;
import java.util.List;

public interface CategoryService {
    Category saveCategory(Category category);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
    Category updateCategory(Long id, Category category);
    String deleteCategory(Long id);
}