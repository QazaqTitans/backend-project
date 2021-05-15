package kz.reserve.backend.payload.request;

import kz.reserve.backend.domain.Category;

import javax.validation.constraints.NotBlank;

public class CategoryRequest {
    @NotBlank
    private String name;

    private Long parentCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Long parentCategory) {
        this.parentCategory = parentCategory;
    }
}