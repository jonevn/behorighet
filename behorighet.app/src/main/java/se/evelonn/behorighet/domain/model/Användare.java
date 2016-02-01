package se.evelonn.behorighet.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "anvandare")
@NamedQueries({ @NamedQuery(name = Användare.HÄMTA_ALLA_ANVÄNDARE_QUERY, query = "select a from Användare a") })
public class Användare extends BaseEntity {

	public static final String HÄMTA_ALLA_ANVÄNDARE_QUERY = "hämtaAllaAnvändare";

	@NotNull
	@Id
	@Column(name = "id", columnDefinition = "BINARY(16)")
	private UUID id;

	@NotNull
	@Column(name = "namn", nullable = false, unique = true)
	private String namn;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "anvandare_roll", joinColumns = {
			@JoinColumn(name = "anvandare_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "roll_id", referencedColumnName = "id") })
	private List<Roll> roller = new ArrayList<>();

	protected Användare() {
		// JPA
	}

	private Användare(UUID id, String användarnamn) {
		this.id = id;
		this.namn = användarnamn;
	}

	public static Användare skapa(String namn) {
		Användare användare = new Användare(UUID.randomUUID(), namn);
		användare.validera();
		return användare;
	}

	public static Användare från(UUID id, String användarnamn) {
		Användare användare = new Användare(id, användarnamn);
		användare.validera();
		return användare;
	}

	public UUID id() {
		return id;
	}

	public void id(UUID id) {
		this.id = id;
	}

	public String namn() {
		return namn;
	}

	public List<Roll> roller() {
		return roller;
	}

	public void roller(List<Roll> roller) {
		this.roller = roller;
	}

	public Användare läggTillRoll(Roll roll) {
		this.roller.add(roll);
		return this;
	}

	public Användare kopieraVärdenFrån(Användare användare) {
		this.namn = användare.namn;
		this.roller = användare.roller;
		return this;
	}

}
