package ro.msg.learning.shop.entitites;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public class EntityWithID {
	@Id
	@Column(unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
}