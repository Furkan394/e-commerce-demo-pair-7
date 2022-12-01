package com.etiya.ecommercedemopair7.business.concretes;

import com.etiya.ecommercedemopair7.business.abstracts.IProductService;
import com.etiya.ecommercedemopair7.entities.concretes.Product;
import com.etiya.ecommercedemopair7.repository.abstracts.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductManager implements IProductService {

    private IProductRepository IProductRepository;

    @Autowired
    public ProductManager(IProductRepository IProductRepository) {
        this.IProductRepository = IProductRepository;
    }

    @Override
    public List<Product> getAll() {
        return IProductRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        return IProductRepository.findById(id).orElseThrow();
    }
}
