package com.main.java.features.address.controller;

import java.util.List;
import java.util.UUID;

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

import com.main.java.features.address.dto.request.AddressRequest;
import com.main.java.features.address.dto.response.AddressResponse;
import com.main.java.features.address.service.AddressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {
	
	private final AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	
	}
	
	@GetMapping
	public ResponseEntity<Page<AddressResponse>> getAddresses(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "10")int size){
		Page<AddressResponse> response = addressService.getAll(page, size);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping
	public ResponseEntity<AddressResponse> create(@Valid @RequestBody AddressRequest request){
		AddressResponse response = addressService.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AddressResponse> findById(@PathVariable UUID id){
		AddressResponse response = addressService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<AddressResponse> update(@PathVariable UUID id,@Valid @RequestBody AddressRequest request){
		AddressResponse response = addressService.update(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable UUID id){
		
		addressService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/batch")
	public ResponseEntity<String> deleteMany(@RequestBody List<UUID> ids){
		addressService.deletebyMany(ids);
		return ResponseEntity.noContent().build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
