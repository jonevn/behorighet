package se.evelonn.behorighet.infrastructure.persistence;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import se.evelonn.behorighet.domain.model.Användare;
import se.evelonn.behorighet.domain.model.Roll;
import se.evelonn.behorighet.domain.model.Rättighet;

@Singleton
@Startup
public class Populator {

	@PersistenceContext(name = "se.evelonn.behorighet")
	private EntityManager entityManager;

	@PostConstruct
	public void populate() {
		Rättighet rättighet = entityManager.merge(Rättighet.skapa("skapa person"));

		Roll roll = entityManager.merge(Roll.skapaNy("Personhanterare").läggTillRättighet(rättighet));

		entityManager.merge(Användare.skapa("johan").läggTillRoll(roll));
	}
}
