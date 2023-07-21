package ro.msg.learning.shop.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.entitites.Product;
import ro.msg.learning.shop.mappers.ProductMapper;
import ro.msg.learning.shop.services.interfaces.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private final ProductService productService;


	@GetMapping
	public ResponseEntity<List<ProductDTO>> getAll() {
		List<Product> products = productService.getAll();
		List<ProductDTO> productDTOS = products.stream().map(ProductMapper::toDTO).toList();
		return new ResponseEntity<>(productDTOS, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductDTO> getById(@PathVariable UUID id) {
		Product existingProduct = productService.getById(id);
		ProductDTO productToFind = ProductMapper.toDTO(existingProduct);
		return new ResponseEntity<>(productToFind, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ProductDTO> create(@RequestBody ProductDTO productDTO) {
		Product product = productService.create(ProductMapper.toEntity(productDTO));
		ProductDTO productToAdd = ProductMapper.toDTO(product);
		return new ResponseEntity<>(productToAdd, HttpStatus.CREATED);
	}

	@PostMapping("/{id}")
	public ResponseEntity<ProductDTO> update(@PathVariable UUID id, @RequestBody ProductDTO productToUpdate) {
		Product updatedProduct = ProductMapper.toEntity(productToUpdate);
		updatedProduct.setId(id);
		Product savedProduct = productService.update(updatedProduct);
		return new ResponseEntity<>(ProductMapper.toDTO(savedProduct), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		productService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
