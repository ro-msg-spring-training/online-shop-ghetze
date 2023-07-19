package ro.msg.learning.shop.entitites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order extends EntityWithID {
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "address_country")
	private String addressCountry;

	@Column(name = "address_city")
	private String addressCity;

	@Column(name = "address_county")
	private String addressCounty;

	@Column(name = "address_street")
	private String addressStreet;

	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetailsList;
}
