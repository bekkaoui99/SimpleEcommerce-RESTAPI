package com.hamzabekkaoui.SimpleEcommerceApp.service;

import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.CategoryRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.CategoryResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.entities.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getListOfCategories();
    Page<CategoryResponse> getPageOfCategories(int page , int size);
    CategoryResponse getCategoryByName(String name);
    CategoryResponse getCategoryById(Long id);
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse updateCategory(Long id ,CategoryRequest categoryRequest);
    boolean deleteCategory(Long id );
}
