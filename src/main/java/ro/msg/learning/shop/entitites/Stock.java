package ro.msg.learning.shop.entitites;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "stock")
@NoArgsConstructor
public class Stock extends EntityWithID {

	public Stock(UUID id, Product product, Location location, Integer quantity) {
		super(id);
		this.product = product;
		this.location = location;
		this.quantity = quantity;
	}

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@Column(name = "quantity")
	private Integer quantity;
}
