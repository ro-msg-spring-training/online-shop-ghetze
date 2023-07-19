package ro.msg.learning.shop.entitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Table(name = "locations")
public class Location extends EntityWithID {
	@Column(name = "name")
	private String name;

	@Column(name = "address_country")
	private String addressCountry;

	@Column(name = "address_city")
	private String addressCity;

	@Column(name = "address_county")
	private String addressCounty;

	@Column(name = "address_street")
	private String addressStreet;

	@OneToMany(mappedBy = "shippedFrom")
	private List<OrderDetail> ordersDetails;

	@OneToMany(mappedBy = "location")
	private List<Revenue> revenues;

	@OneToMany(mappedBy = "location")
	private List<Stock> stocks;
}
