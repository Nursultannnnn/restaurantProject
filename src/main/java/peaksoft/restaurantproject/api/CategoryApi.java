package peaksoft.restaurantproject.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantproject.dto.PaginationResponse;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.category.CategoryRequest;
import peaksoft.restaurantproject.dto.category.CategoryResponse;
import peaksoft.restaurantproject.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryApi {

    private final CategoryService categoryService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public CategoryResponse saveCategory(@RequestBody @Valid CategoryRequest request) {
        return categoryService.saveCategory(request);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public PaginationResponse<CategoryResponse> getAllCategories(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        return categoryService.getAllCategories(page, size);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Long id, @RequestBody @Valid CategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}