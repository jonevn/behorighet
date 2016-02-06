package se.evelonn.behorighet.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "roll")
@NamedQueries({ @NamedQuery(name = Roll.HÄMTA_ALLA_ROLLER_QUERY, query = "select r from Roll r") })
public class Roll extends BaseEntity {

	public static final String HÄMTA_ALLA_ROLLER_QUERY = "hämtaAllaRoller";

	@NotNull
	@EmbeddedId
	private RollId id;

	@NotNull
	@Column(name = "namn", unique = true, nullable = false)
	private String namn;

	@Column(name = "beskrivning")
	private String beskrivning;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "roll_rattighet", joinColumns = {
			@JoinColumn(name = "roll_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "rattighet_id", referencedColumnName = "id") })
	private List<Rättighet> rättigheter = new ArrayList<>();

	protected Roll() {
		// JPA
	}

	private Roll(RollId id, String namn, String beskrivning) {
		this.id = id;
		this.namn = namn;
	}

	public static Roll skapaNy(String namn, String beskrivning) {
		Roll roll = new Roll(RollId.skapa(), namn, beskrivning);
		roll.validera();
		return roll;
	}

	public static Roll från(RollId id, String namn, String beskrivning) {
		Roll roll = new Roll(id, namn, beskrivning);
		roll.validera();
		return roll;
	}

	public RollId id() {
		return id;
	}

	public String namn() {
		return namn;
	}

	public String beskrivning() {
		return beskrivning;
	}

	public List<Rättighet> rättigheter() {
		return rättigheter;
	}

	public Roll läggTillRättighet(Rättighet rättighet) {
		this.rättigheter.add(rättighet);
		return this;
	}

	public Roll kopieraVärdenFrån(Roll roll) {
		this.namn = roll.namn;
		this.rättigheter = roll.rättigheter;
		this.beskrivning = roll.beskrivning;
		return this;
	}
}