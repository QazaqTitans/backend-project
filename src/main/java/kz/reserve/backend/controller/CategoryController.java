package kz.reserve.backend.controller;

import kz.reserve.backend.domain.Category;
import kz.reserve.backend.payload.request.CategoryRequest;
import kz.reserve.backend.payload.response.CategoryResponse;
import kz.reserve.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<?> getCategories() {
        return categoryService.getCategories();
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @PostMapping()
    public ResponseEntity<?> addCategory(@Valid @ModelAttribute CategoryRequest categoryRequest) {
        return categoryService.addCategory(categoryRequest);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@Valid @Min(1) @PathVariable Long id, @Valid @ModelAttribute CategoryRequest categoryRequest) {
        return categoryService.updateCategory(id, categoryRequest);
    }

    @PreAuthorize("hasAuthority('superAdmin')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@Valid @Min(1) @PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}
