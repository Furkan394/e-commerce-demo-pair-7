package com.etiya.ecommercedemopair7.business.abstracts;

import com.etiya.ecommercedemopair7.business.abstracts.concretes.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> getAll();
    Category getById(int id);
    Category getByName(String name);
    Category customGetByName(String name);
}
