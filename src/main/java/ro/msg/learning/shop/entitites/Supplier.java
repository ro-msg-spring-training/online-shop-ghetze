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
@Table(name = "supplier")
@NoArgsConstructor
public class Supplier extends EntityWithID {
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "supplier")
	private List<Product> products;

	public Supplier(UUID id , String name )
	{
		super(id);
		this.name = name;
	}
}
