package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.Category;

import javax.validation.constraints.NotBlank;

public class CategoryRequest {
    @NotBlank
    private String name;

    private Category parentCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
}