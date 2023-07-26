package ro.msg.learning.shop.entitites;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class EntityWithID {

	@Id
	private UUID id = UUID.randomUUID();
}