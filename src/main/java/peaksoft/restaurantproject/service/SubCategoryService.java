package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.subCategory.SubCategoryRequest;
import peaksoft.restaurantproject.dto.subCategory.SubCategoryResponse;

import java.util.List;

public interface SubCategoryService {
    SubCategoryResponse saveSubCategory(Long categoryId, SubCategoryRequest request);
    List<SubCategoryResponse> getAllSubCategoriesByCategoryId(Long categoryId);
    SubCategoryResponse getSubCategoryById(Long id);
    SubCategoryResponse updateSubCategory(Long id, SubCategoryRequest request);
    SimpleResponse deleteSubCategory(Long id);
}