package com.hamzabekkaoui.SimpleEcommerceApp.service.impl;

import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.CategoryRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.CategoryResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.entities.Category;
import com.hamzabekkaoui.SimpleEcommerceApp.exeption.ResourceNotFoundException;
import com.hamzabekkaoui.SimpleEcommerceApp.mapper.CategoryMapper;
import com.hamzabekkaoui.SimpleEcommerceApp.repositories.CategoryRepository;
import com.hamzabekkaoui.SimpleEcommerceApp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> getListOfCategories() {
        List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream()
               .map(categoryMapper::categoryToCategoryResponse)
               .collect(Collectors.toList());

    }

    @Override
    public Page<CategoryResponse> getPageOfCategories(int page, int size) {

        Page<Category> pageOfCategories = categoryRepository.findAll(PageRequest.of(page, size));

        List<CategoryResponse> categoryResponseList = pageOfCategories.getContent()
                .stream()
                .map(categoryMapper::categoryToCategoryResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(categoryResponseList , pageOfCategories.getPageable() , pageOfCategories.getTotalElements());
    }

    @Override
    public CategoryResponse getCategoryByName(String name) {
        Category category = categoryRepository.findByCategoryName(name)
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));
        return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));
        return categoryMapper.categoryToCategoryResponse(category);
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        categoryRepository.findByCategoryName(categoryRequest.categoryName())
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));
        Category category = categoryMapper.categoryRequestToCategory(categoryRequest);
        Category created = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryResponse(created);
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoryRequest) {

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));

        if(categoryRequest.categoryName() != null && !categoryRequest.categoryName().equals(category.getCategoryName()))
            category.setCategoryName(categoryRequest.categoryName());
        if(categoryRequest.categoryDescription() != null && !categoryRequest.categoryDescription().equals(category.getCategoryDescription()))
            category.setCategoryDescription(categoryRequest.categoryDescription());
        Category updated = categoryRepository.save(category);

        return categoryMapper.categoryToCategoryResponse(updated);
    }

    @Override
    public boolean deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category doesn't exist"));
        categoryRepository.delete(category);
        return true;
    }
}
