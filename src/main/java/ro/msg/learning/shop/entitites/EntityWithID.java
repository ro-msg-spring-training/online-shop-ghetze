package ro.msg.learning.shop.entitites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class EntityWithID {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private UUID id = UUID.randomUUID();
}