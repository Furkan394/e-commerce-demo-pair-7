package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.ICategoryService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.categories.AddCategoryRequest;
import com.etiya.ecommercedemopair7.business.response.categories.AddCategoryResponse;
import com.etiya.ecommercedemopair7.business.response.categories.GetAllCategoryResponse;
import com.etiya.ecommercedemopair7.business.response.categories.GetCategoryResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import com.etiya.ecommercedemopair7.entities.concretes.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "categories")
public class CategoriesContoller {

    private ICategoryService categoryService;

    @Autowired
    public CategoriesContoller(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<GetAllCategoryResponse>>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetCategoryResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @GetMapping("get-by-name")
    public ResponseEntity<DataResult<Category>> getByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(categoryService.getByName(name));
    }

    @GetMapping("custom-get-by-name")
    public ResponseEntity<DataResult<Category>> customGetByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(categoryService.customGetByName(name));
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<AddCategoryResponse>> add(@RequestBody @Valid AddCategoryRequest addCategoryRequest) {
        return new ResponseEntity<>(categoryService.add(addCategoryRequest), HttpStatus.CREATED);
    }

    @GetMapping("/get-all-categories-with-slice")
    public ResponseEntity<DataResult<Slice<GetAllCategoryResponse>>> getAllCategoriesWithSlice(@RequestParam("page") int page,
                                                                                               @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(categoryService.getAllCategoriesWithSlice(pageable));
    }
}
