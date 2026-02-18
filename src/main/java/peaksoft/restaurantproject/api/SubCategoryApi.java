package peaksoft.restaurantproject.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.restaurantproject.dto.SimpleResponse;
import peaksoft.restaurantproject.dto.subCategory.SubCategoryRequest;
import peaksoft.restaurantproject.dto.subCategory.SubCategoryResponse;
import peaksoft.restaurantproject.service.SubCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
public class SubCategoryApi {

    private final SubCategoryService subCategoryService;

    @PostMapping("/category/{categoryId}")
    public SubCategoryResponse saveSubCategory(@PathVariable Long categoryId,
                                               @RequestBody @Valid SubCategoryRequest request) {
        return subCategoryService.saveSubCategory(categoryId, request);
    }

    @GetMapping("/category/{categoryId}")
    public List<SubCategoryResponse> getAllSubCategoriesByCategoryId(@PathVariable Long categoryId) {
        return subCategoryService.getAllSubCategoriesByCategoryId(categoryId);
    }

    @GetMapping("/{id}")
    public SubCategoryResponse getSubCategoryById(@PathVariable Long id) {
        return subCategoryService.getSubCategoryById(id);
    }

    @PutMapping("/{id}")
    public SubCategoryResponse updateSubCategory(@PathVariable Long id,
                                                 @RequestBody @Valid SubCategoryRequest request) {
        return subCategoryService.updateSubCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public SimpleResponse deleteSubCategory(@PathVariable Long id) {
        return subCategoryService.deleteSubCategory(id);
    }
}