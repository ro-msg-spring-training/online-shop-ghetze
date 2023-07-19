package ro.msg.learning.shop.entitites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "stocks")
public class Stock extends EntityWithID {

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@Column(name = "quantity")
	private Integer quantity;
}
