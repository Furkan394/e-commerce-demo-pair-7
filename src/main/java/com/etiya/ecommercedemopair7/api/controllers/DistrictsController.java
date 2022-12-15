package com.etiya.ecommercedemopair7.api.controllers;

import com.etiya.ecommercedemopair7.business.abstracts.IDistrictService;
import com.etiya.ecommercedemopair7.business.constants.Paths;
import com.etiya.ecommercedemopair7.business.response.districts.GetAllDistrictResponse;
import com.etiya.ecommercedemopair7.business.response.districts.GetDistrictResponse;
import com.etiya.ecommercedemopair7.core.utilities.results.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "districts")
public class DistrictsController {

    private IDistrictService districtService;

    @Autowired
    public DistrictsController(IDistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping
    public ResponseEntity<DataResult<List<GetAllDistrictResponse>>> getAll() {
        return ResponseEntity.ok(districtService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResult<GetDistrictResponse>> getById(@PathVariable int id) {
        return ResponseEntity.ok(districtService.getById(id));
    }

    @GetMapping("/get-all-districts-with-slice")
    public ResponseEntity<DataResult<Slice<GetAllDistrictResponse>>> getAllDistrictsWithSlice(@RequestParam("page") int page,
                                                                                              @RequestParam("size") int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return ResponseEntity.ok(districtService.getAllDistrictsWithSlice(pageable));
    }
}
