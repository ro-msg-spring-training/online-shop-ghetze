package ro.msg.learning.shop.entitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "location")
@NoArgsConstructor
public class Location extends EntityWithID {
	@Column(name = "name")
	private String name;

	public Location(UUID id, String name, String addressCountry, String addressCity, String addressCounty, String addressStreet) {
		super(id);
		this.name = name;
		this.addressCountry = addressCountry;
		this.addressCity = addressCity;
		this.addressCounty = addressCounty;
		this.addressStreet = addressStreet;
	}

	public Location(UUID id)
	{
		super(id);
	}

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
