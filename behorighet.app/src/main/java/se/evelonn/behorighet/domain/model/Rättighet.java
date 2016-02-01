package se.evelonn.behorighet.domain.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rattighet")
public class Rättighet extends BaseEntity {

	@NotNull
	@Id
	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;

	@NotNull
	@Column(name = "namn", nullable = false, unique = true)
	private String namn;

	public Rättighet() {
		// JPA
	}

	private Rättighet(UUID id, String namn) {
		this.id = id;
		this.namn = namn;
	}

	public static Rättighet skapa(String namn) {
		Rättighet rättighet = new Rättighet(UUID.randomUUID(), namn);
		rättighet.validera();
		return rättighet;
	}

	public UUID id() {
		return id;
	}

	public String namn() {
		return namn;
	}
}