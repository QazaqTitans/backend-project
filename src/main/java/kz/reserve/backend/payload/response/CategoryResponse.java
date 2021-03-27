package kz.reserve.backend.payload.response;

import kz.reserve.backend.domain.Category;

import java.util.List;

public class CategoryResponse {
    private List<Category> categoryList;

    public CategoryResponse(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
}