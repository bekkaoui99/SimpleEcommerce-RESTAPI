package com.hamzabekkaoui.SimpleEcommerceApp.web;


import com.hamzabekkaoui.SimpleEcommerceApp.dto.request.CategoryRequest;
import com.hamzabekkaoui.SimpleEcommerceApp.dto.response.CategoryResponse;
import com.hamzabekkaoui.SimpleEcommerceApp.service.ProductService;
import com.hamzabekkaoui.SimpleEcommerceApp.service.impl.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryServiceImpl categoryService;

    @GetMapping("/list")
    public List<CategoryResponse> getAllCategories(){
        return categoryService.getListOfCategories();
    }

    @GetMapping("/page")
    public Page<CategoryResponse> getPageOfCategories(
            @RequestParam(name = "pageNumber" , defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize" , defaultValue = "0") int pageSize
    ){
        return categoryService.getPageOfCategories(pageNumber , pageSize);
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategory(@PathVariable(name = "id") Long id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/name")
    public CategoryResponse getCategoryByName(@PathVariable(name = "name") String name){
        return categoryService.getCategoryByName(name);
    }

    @PostMapping
    public CategoryResponse createCategory(@RequestBody CategoryRequest categoryRequest){
        return categoryService.createCategory(categoryRequest);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Long id , @RequestBody CategoryRequest categoryRequest){
        return categoryService.updateCategory(id,categoryRequest);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable Long id){
        return categoryService.deleteCategory(id);
    }


}
