package se.evelonn.behorighet.infrastructure.persistence;

import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import se.evelonn.behorighet.domain.model.AnvändareBuilder;
import se.evelonn.behorighet.domain.model.Roll;
import se.evelonn.behorighet.domain.model.Rättighet;

@Singleton
@Startup
public class Populator {

	@PersistenceContext(name = "se.evelonn.behorighet")
	private EntityManager entityManager;

	@PostConstruct
	public void populate() {
		Rättighet rättighet = entityManager
				.merge(Rättighet.skapa("skapa person", "Ger rätt att skapa nya personer i systemet"));
		Rättighet rättighet2 = entityManager
				.merge(Rättighet.skapa("administrera person", "Ger rätt att administrera personer i systemet"));

		Roll roll = entityManager.merge(
				Roll.skapaNy("Personhanterare", "Roll som tillåter personhantering").läggTillRättighet(rättighet));
		Roll roll2 = entityManager.merge(
				Roll.skapaNy("Administrerare", "Roll som tillåter administrering").läggTillRättighet(rättighet2));

		IntStream.range(0, 10).forEach(
				i -> entityManager.merge(Roll.skapaNy("Administrerare" + i, "Roll som tillåter administrering")));

		entityManager.merge(AnvändareBuilder.builder()
				.ny()
				.medAnvändarnamn("johndoe")
				.medFörnamn("John")
				.medEfternamn("Doe")
				.medEpost("john.doe@someemail.com")
				.build()
				.läggTillRoll(roll));
	}
}
