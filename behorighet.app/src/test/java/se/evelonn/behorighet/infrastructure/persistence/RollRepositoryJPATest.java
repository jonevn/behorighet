package se.evelonn.behorighet.infrastructure.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import se.evelonn.behorighet.domain.model.Roll;
import se.evelonn.behorighet.domain.model.RollId;

public class RollRepositoryJPATest extends EntityTestCase {

	private RollRepositoryJPA rollRepositoryJPA;

	@Before
	public void setEntityManager() {
		rollRepositoryJPA = new RollRepositoryJPA();
		rollRepositoryJPA.setEntityManager(entityManager);
	}

	// @Test
	// public void ingenRollFinns() {
	// assertThat(rollRepositoryJPA.hämtaAllaRoller()).isEmpty();
	// }

	@Test
	public void rättRollerAnvändareFinns() {
		rollRepositoryJPA.sparaRoll(Roll.skapaNy("första", "första beskrivning"));
		rollRepositoryJPA.sparaRoll(Roll.skapaNy("andra", "andra beskrivning"));

		// Två skapas här och en skjuts in via persistence.xml
		assertThat(rollRepositoryJPA.hämtaAllaRoller()).hasSize(3);
	}

	@Test
	public void rollFinnsInte() {
		assertThat(rollRepositoryJPA.hämtaRoll(RollId.från(UUID.randomUUID()))).isEmpty();
	}

	@Test
	public void rollFinns() {
		Roll roll = rollRepositoryJPA.sparaRoll(Roll.skapaNy("roll", "roll beskrivning"));

		assertThat(rollRepositoryJPA.hämtaRoll(roll.id())).contains(roll);
	}
}