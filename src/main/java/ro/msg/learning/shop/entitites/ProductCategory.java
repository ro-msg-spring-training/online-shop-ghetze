package ro.msg.learning.shop.entitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "product_category")
public class ProductCategory extends EntityWithID {
	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "category")
	private List<Product> products;
}

