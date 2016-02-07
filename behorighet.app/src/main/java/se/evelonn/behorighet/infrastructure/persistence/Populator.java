package se.evelonn.behorighet.infrastructure.persistence;

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

		entityManager.merge(AnvändareBuilder.builder()
				.ny()
				.medAnvändarnamn("jonevn02")
				.medFörnamn("Johan")
				.medEfternamn("Evelönn")
				.medEpost("johanevelonn@gmail.com")
				.build()
				.läggTillRoll(roll));

		entityManager.merge(AnvändareBuilder.builder()
				.ny()
				.medAnvändarnamn("emaaon02")
				.medFörnamn("Emma")
				.medEfternamn("Evelönn")
				.medEpost("emmaevelonn@gmail.com")
				.build()
				.läggTillRoll(roll2));

		entityManager.merge(AnvändareBuilder.builder()
				.ny()
				.medAnvändarnamn("hugevn11")
				.medFörnamn("Hugo")
				.medEfternamn("Evelönn")
				.medEpost("hugoevelonn@gmail.com")
				.build());

		entityManager.merge(AnvändareBuilder.builder()
				.ny()
				.medAnvändarnamn("hanevn15")
				.medFörnamn("Hanna")
				.medEfternamn("Evelönn")
				.medEpost("hannaevelonn@gmail.com")
				.build());
	}
}
