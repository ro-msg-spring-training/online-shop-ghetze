package ro.msg.learning.shop.entitites;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

	@OneToMany(mappedBy = "order",cascade= CascadeType.ALL)
	private List<OrderDetail> orderDetailsList;
}
