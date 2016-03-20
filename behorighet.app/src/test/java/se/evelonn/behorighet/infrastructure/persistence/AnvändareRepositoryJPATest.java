package se.evelonn.behorighet.infrastructure.persistence;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import se.evelonn.behorighet.domain.model.Användare;
import se.evelonn.behorighet.domain.model.AnvändareBuilder;

public class AnvändareRepositoryJPATest extends EntityTestCase {

	private AnvändareRepositoryJPA användareRepositoryJPA;

	@Before
	public void setEntityManager() {
		användareRepositoryJPA = new AnvändareRepositoryJPA();
		användareRepositoryJPA.setEntityManager(entityManager);
	}

	// @Test
	// public void ingenAnvändareFinns() {
	// assertThat(användareRepositoryJPA.hämtaAllaAnvändare()).isEmpty();
	// }

	@Test
	public void rättAntalAnvändareFinns() {
		användareRepositoryJPA.sparaAnvändare(skapaAnvändare("första"));
		användareRepositoryJPA.sparaAnvändare(skapaAnvändare("andra"));

		// Två skapas här och två skjuts in via data laddning i persistence.xml
		assertThat(användareRepositoryJPA.hämtaAllaAnvändare()).hasSize(4);
	}

	@Test
	public void användareFinnsInte() {
		assertThat(användareRepositoryJPA.hämtaAnvändare(UUID.randomUUID())).isEmpty();
	}

	@Test
	public void användareFinns() {
		Användare användare = användareRepositoryJPA.sparaAnvändare(skapaAnvändare("användare"));

		assertThat(användareRepositoryJPA.hämtaAnvändare(användare.id())).contains(användare);
	}

	private Användare skapaAnvändare(String användarnamn) {
		return AnvändareBuilder.builder()
				.ny()
				.medAnvändarnamn(användarnamn)
				.medFörnamn("fornamn")
				.medEfternamn("efternamn")
				.medEpost("epost")
				.build();
	}
}
