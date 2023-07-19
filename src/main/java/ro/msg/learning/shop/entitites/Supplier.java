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
@Table(name = "suppliers")
public class Supplier extends EntityWithID {
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "supplier")
	private List<Product> products;
}
