package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.entities.concretes.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
    Category getById(int id);
}
