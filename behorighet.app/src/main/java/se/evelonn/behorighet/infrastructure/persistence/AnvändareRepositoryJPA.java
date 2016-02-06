package se.evelonn.behorighet.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import se.evelonn.behorighet.domain.model.Användare;
import se.evelonn.behorighet.domain.model.AnvändareRepository;

public class AnvändareRepositoryJPA extends BaseRepositoryJPA implements AnvändareRepository {

	@Override
	public List<Användare> hämtaAllaAnvändare() {
		return entityManager.createNamedQuery("hämtaAllaAnvändare", Användare.class).getResultList();
	}

	@Override
	public Optional<Användare> hämtaAnvändare(UUID id) {
		return Optional.ofNullable(entityManager.find(Användare.class, id));
	}

	@Override
	public Användare uppdateraAnvändare(Användare användare) {
		Användare dbAnvändare = entityManager.find(Användare.class, användare.id());
		if (dbAnvändare == null) {
			throw new IllegalArgumentException(
					"Användare med id: " + användare.id().toString() + " finns ej, uppdatering kan ej ske.");
		}
		return entityManager.merge(dbAnvändare.kopieraVärdenFrån(användare));
	}

	@Override
	public Användare sparaAnvändare(Användare användare) {
		användare.id(UUID.randomUUID());
		return entityManager.merge(användare);
	}

	@Override
	public void taBortAnvändare(UUID id) {
		entityManager.remove(entityManager.find(Användare.class, id));
	}
}
