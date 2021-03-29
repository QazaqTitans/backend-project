package kz.reserve.backend.service;

import kz.reserve.backend.domain.Category;
import kz.reserve.backend.payload.request.CategoryRequest;
import kz.reserve.backend.payload.response.CategoryResponse;
import kz.reserve.backend.payload.response.MessageResponse;
import kz.reserve.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity<?> getCategories() {
        List<Category> categoryList = categoryRepository.findAll();
        return ResponseEntity.ok(new CategoryResponse(categoryList));
    }

    public ResponseEntity<?> addCategory(CategoryRequest categoryRequest) {
        try {
            Category category = new Category();

            category.setName(categoryRequest.getName());
            category.setParentCategory(categoryRequest.getParentCategory());
            categoryRepository.save(category);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    public ResponseEntity<?> updateCategory(Long categoryId, CategoryRequest categoryRequest) {
        try {
            Category category = categoryRepository.getOne(categoryId);

            category.setName(categoryRequest.getName());
            category.setParentCategory(category.getParentCategory());

            categoryRepository.save(category);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }

    public ResponseEntity<?> deleteCategory(Long categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }

        return ResponseEntity.ok(new MessageResponse("Success"));
    }
}