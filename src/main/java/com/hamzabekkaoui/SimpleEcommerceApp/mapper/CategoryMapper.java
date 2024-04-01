package com.hamzabekkaoui.SimpleEcommerceApp.mapper;

import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.CategoryRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.CategoryResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.entities.Category;
import org.springframework.stereotype.Service;


@Service
public class CategoryMapper {

    public CategoryResponse categoryToCategoryResponse(Category category){

        return CategoryResponse.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .build();
    }

    public Category categoryRequestToCategory(CategoryRequest categoryRequest){

        return Category.builder()
                .categoryName(categoryRequest.categoryName())
                .categoryDescription(categoryRequest.categoryDescription())
                .build();
    }


}
