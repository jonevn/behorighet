package se.evelonn.behorighet.domain.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RollId implements Serializable {

	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;

	public RollId() {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RollId other = (RollId) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}