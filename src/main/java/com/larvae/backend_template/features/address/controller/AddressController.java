package com.larvae.backend_template.features.address.controller;

import java.util.List;
import java.util.UUID;

import com.larvae.backend_template.common.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.larvae.backend_template.features.address.dto.request.AddressRequest;
import com.larvae.backend_template.features.address.dto.response.AddressResponse;
import com.larvae.backend_template.features.address.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
	
	private final AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	
	}
	
	@GetMapping
	public ResponseEntity<ApiResponse<Page<AddressResponse>>> getAddresses(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size){
		Page<AddressResponse> response = addressService.getAll(page, size);
		return ResponseEntity.ok(ApiResponse.success(response));
	}
	
	@PostMapping
	public ResponseEntity<ApiResponse<AddressResponse>> create(@Valid @RequestBody AddressRequest request){
		AddressResponse response = addressService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<AddressResponse>> findById(@PathVariable UUID id){
		AddressResponse response = addressService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<AddressResponse>> update(@PathVariable UUID id,@Valid @RequestBody AddressRequest request){
		AddressResponse response = addressService.update(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.success(response));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id){
		
		addressService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/actions/batch-delete")
	public ResponseEntity<Void> deleteMany(@RequestBody List<UUID> ids){
		addressService.deleteByMany(ids);
		return ResponseEntity.noContent().build();
	}
}
