package ro.msg.learning.shop.entitites;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product")
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
