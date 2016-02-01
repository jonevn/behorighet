package se.evelonn.behorighet.infrastructure.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import se.evelonn.behorighet.domain.model.Användare;

public class AnvändareRepositoryJPATest extends EntityTestCase {

	private AnvändareRepositoryJPA användareRepositoryJPA;

	@Before
	public void setEntityManager() {
		användareRepositoryJPA = new AnvändareRepositoryJPA();
		användareRepositoryJPA.setEntityManager(entityManager);
	}

	@Test
	public void ingenAnvändareFinns() {
		assertThat(användareRepositoryJPA.hämtaAllaAnvändare()).isEmpty();
	}

	@Test
	public void rättAntalAnvändareFinns() {
		användareRepositoryJPA.sparaAnvändare(Användare.skapa("första"));
		användareRepositoryJPA.sparaAnvändare(Användare.skapa("andra"));

		assertThat(användareRepositoryJPA.hämtaAllaAnvändare()).hasSize(2);
	}

	@Test
	public void användareFinnsInte() {
		assertThat(användareRepositoryJPA.hämtaAnvändare(UUID.randomUUID())).isEmpty();
	}

	@Test
	public void användareFinns() {
		Användare användare = användareRepositoryJPA.sparaAnvändare(Användare.skapa("användare"));

		assertThat(användareRepositoryJPA.hämtaAnvändare(användare.id())).contains(användare);
	}
}
