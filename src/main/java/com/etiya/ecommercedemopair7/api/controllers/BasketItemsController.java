package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IBasketItemService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.request.basketItems.AddBasketItemRequest;
import com.etiya.ecommercedemopair7.business.response.basketItems.AddBasketItemResponse;
import com.etiya.ecommercedemopair7.business.response.basketItems.GetAllBasketItemResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "basket-items")
public class BasketItemsController {
    private IBasketItemService basketItemService;

    @Autowired
    public BasketItemsController(IBasketItemService basketItemService) {
        this.basketItemService = basketItemService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<GetAllBasketItemResponse>>> getAll() {
        return ResponseEntity.ok(basketItemService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<DataResult<AddBasketItemResponse>> add(@RequestBody AddBasketItemRequest addBasketItemRequest) {
        return new ResponseEntity<>(basketItemService.add(addBasketItemRequest), HttpStatus.CREATED);
    }

    @GetMapping("/get-all-basket-items-with-slice")
    public ResponseEntity<DataResult<Slice<GetAllBasketItemResponse>>> getAllBasketItemsWithSlice(@RequestParam("page") int page,
                                                                                                  @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(basketItemService.getAllBasketItemsWithSlice(pageable));
    }
}
