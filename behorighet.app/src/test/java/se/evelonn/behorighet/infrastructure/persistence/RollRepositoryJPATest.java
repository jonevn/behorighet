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

	@Test
	public void ingenRollFinns() {
		assertThat(rollRepositoryJPA.hämtaAllaRoller()).isEmpty();
	}

	@Test
	public void rättAntalAnvändareFinns() {
		rollRepositoryJPA.sparaRoll(Roll.skapaNy("första"));
		rollRepositoryJPA.sparaRoll(Roll.skapaNy("andra"));

		assertThat(rollRepositoryJPA.hämtaAllaRoller()).hasSize(2);
	}

	@Test
	public void användareFinnsInte() {
		assertThat(rollRepositoryJPA.hämtaRoll(RollId.från(UUID.randomUUID()))).isEmpty();
	}

	@Test
	public void användareFinns() {
		Roll roll = rollRepositoryJPA.sparaRoll(Roll.skapaNy("roll"));

		assertThat(rollRepositoryJPA.hämtaRoll(roll.id())).contains(roll);
	}
}
