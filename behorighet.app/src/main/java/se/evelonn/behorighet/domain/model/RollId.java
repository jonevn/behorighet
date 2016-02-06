package se.evelonn.behorighet.domain.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RollId implements Serializable {

	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;

	protected RollId() {
		// JPA
	}

	private RollId(UUID id) {
		this.id = id;
	}

	public static RollId skapa() {
		return new RollId(UUID.randomUUID());
	}

	public static RollId från(String id) {
		return new RollId(UUID.fromString(id));
	}

	public static RollId från(UUID id) {
		return new RollId(id);
	}

	public String värde() {
		return id.toString();
	}
}