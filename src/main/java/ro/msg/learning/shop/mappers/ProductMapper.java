package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.ProductDTO;
import ro.msg.learning.shop.entitites.Product;
import ro.msg.learning.shop.entitites.ProductCategory;
import ro.msg.learning.shop.entitites.Supplier;

import java.util.List;

@Component
public class ProductMapper {

	public static ProductDTO toDTO(Product product) {
		return new ProductDTO(
			product.getId(),
			product.getName(),
			product.getDescription(),
			product.getPrice(),
			product.getWeight(),
			product.getImageUrl(),
			product.getCategory().getId(),
			product.getSupplier().getId()
		);
	}

	public static Product toEntity(ProductDTO dto) {
			var productCategory = new ProductCategory();
			productCategory.setId(dto.getCategoryId());

			var supplier = new Supplier();
			supplier.setId(dto.getSupplierId());

			Product product= new Product();
			product.setId(dto.getId());
			product.setName(dto.getName());
			product.setDescription(dto.getDescription());
			product.setPrice(dto.getPrice());
			product.setWeight(dto.getWeight());
			product.setCategory(productCategory);
			product.setImageUrl(dto.getImageUrl());
			product.setSupplier(supplier);
			return product;
	}

	public static List<ProductDTO> mapEntityListToDtoList(List<Product> entities){
			return entities.stream().map(entity->toDTO(entity)).toList();
	}

	public static List<Product> mapDTOListToEntityList(List<ProductDTO> entities){
		return entities.stream().map(dto->toEntity(dto)).toList();
	}
}
