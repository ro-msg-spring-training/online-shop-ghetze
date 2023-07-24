package ro.msg.learning.shop.entitites;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "revenue")
public class Revenue extends EntityWithID {
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "sum")
	private BigDecimal sum;
}
