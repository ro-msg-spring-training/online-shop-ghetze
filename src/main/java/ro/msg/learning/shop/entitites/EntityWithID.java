package ro.msg.learning.shop.entitites;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.UUID;

@MappedSuperclass
public class EntityWithID {
	@Id
	private UUID id;

	// Default constructor (required by JPA)
	public EntityWithID() {
		this.id = UUID.randomUUID();
	}

	// Constructor to set the id explicitly
	public EntityWithID(UUID id) {
		this.id = id;
	}

	// Getters and Setters
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}
}