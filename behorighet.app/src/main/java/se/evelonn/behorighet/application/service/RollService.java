package se.evelonn.behorighet.application.service;

import java.util.List;
import java.util.Optional;

import javax.inject.Inject;

import se.evelonn.behorighet.domain.model.Roll;
import se.evelonn.behorighet.domain.model.RollId;
import se.evelonn.behorighet.domain.model.RollRepository;

public class RollService {

	@Inject
	private RollRepository rollRepository;

	public List<Roll> hämtaAllaRoller() {
		return rollRepository.hämtaAllaRoller();
	}

	public Roll hämtaRoll(RollId id) {
		Optional<Roll> roll = rollRepository.hämtaRoll(id);
		if (roll.isPresent()) {
			return roll.get();
		}
		throw new IllegalArgumentException("Roll med id: " + id.toString() + " finns inte");
	}

	public Roll uppdateraRoll(Roll roll) {
		return rollRepository.uppdateraRoll(roll);
	}

	public Roll skapaRoll(Roll roll) {
		return rollRepository.sparaRoll(roll);
	}

}
