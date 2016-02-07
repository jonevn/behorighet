package se.evelonn.behorighet.domain.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rattighet")
@NamedQueries({ @NamedQuery(name = Rättighet.HÄMTA_ALLA_RÄTTIGHETER_QUERY, query = "select r from Rättighet r") })
public class Rättighet extends BaseEntity {

	public static final String HÄMTA_ALLA_RÄTTIGHETER_QUERY = "hämtaAllaRättigheter";

	@NotNull
	@Id
	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;

	@NotNull
	@Column(name = "namn", nullable = false, unique = true)
	private String namn;

	@Column(name = "beskrivning")
	private String beskrivning;

	public Rättighet() {
		// JPA
	}

	private Rättighet(UUID id, String namn, String beskrivning) {
		this.id = id;
		this.namn = namn;
		this.beskrivning = beskrivning;
	}

	public static Rättighet skapa(String namn, String beskrivning) {
		Rättighet rättighet = new Rättighet(UUID.randomUUID(), namn, beskrivning);
		rättighet.validera();
		return rättighet;
	}

	public UUID id() {
		return id;
	}

	public String namn() {
		return namn;
	}

	public String beskrivning() {
		return beskrivning;
	}
}