package se.evelonn.behorighet.domain.model;

import java.util.List;
import java.util.Optional;

public interface RollRepository {

	List<Roll> hämtaAllaRoller();

	Optional<Roll> hämtaRoll(RollId id);

	Roll uppdateraRoll(Roll roll);

	Roll sparaRoll(Roll roll);
}
