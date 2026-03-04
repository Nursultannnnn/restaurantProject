package peaksoft.restaurantproject.service;

import peaksoft.restaurantproject.dto.PaginationResponse;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.category.CategoryRequest;
import peaksoft.restaurantproject.dto.category.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse saveCategory(CategoryRequest categoryRequest);
    PaginationResponse<CategoryResponse> getAllCategories(int page, int size);
    CategoryResponse getCategoryById(Long id);
    CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest);
    SimpleResponse deleteCategory(Long id);
}