package se.evelonn.behorighet.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import se.evelonn.behorighet.domain.model.Roll;
import se.evelonn.behorighet.domain.model.RollId;
import se.evelonn.behorighet.domain.model.RollRepository;

public class RollRepositoryJPA extends BaseRepositoryJPA implements RollRepository {

	@Override
	public List<Roll> hämtaAllaRoller() {
		return entityManager.createNamedQuery(Roll.HÄMTA_ALLA_ROLLER_QUERY, Roll.class).getResultList();
	}

	@Override
	public Optional<Roll> hämtaRoll(RollId id) {
		return Optional.ofNullable(entityManager.find(Roll.class, id));
	}

	@Override
	public Roll uppdateraRoll(Roll roll) {
		Roll dbRoll = entityManager.find(Roll.class, roll.id());
		if (dbRoll == null) {
			throw new IllegalArgumentException("Roll med id: " + roll.id().värde() + " finns inte");
		}
		return entityManager.merge(dbRoll.kopieraVärdenFrån(roll));
	}

	@Override
	public Roll sparaRoll(Roll roll) {
		return entityManager.merge(roll);
	}
}