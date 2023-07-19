package ro.msg.learning.shop.entitites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "products")
public class Product extends EntityWithID {

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "weight")
	private Double weight;

	@Column(name = "img_url")
	private String imageUrl;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategory category;

	@OneToMany(mappedBy = "product")
	private List<Stock> stocks;

	@OneToMany(mappedBy = "product")
	private List<OrderDetail> orderDetails;

}
