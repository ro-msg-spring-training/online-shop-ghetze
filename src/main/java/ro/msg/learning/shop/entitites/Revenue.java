package ro.msg.learning.shop.entitites;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "revenues")
public class Revenue extends EntityWithID {
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@Column(name = "date")
	private LocalDate date;

	@Column(name = "sum")
	private BigDecimal sum;
}
