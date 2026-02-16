package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.entity.SubCategory;
import java.util.List;

public interface SubCategoryService {
    SubCategory saveSubCategory(Long categoryId, SubCategory subCategory);
    List<SubCategory> getAllSubCategoriesByCategoryId(Long categoryId);
    SubCategory getSubCategoryById(Long id);
    SubCategory updateSubCategory(Long id, SubCategory subCategory);
    String deleteSubCategory(Long id);
}