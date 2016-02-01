package se.evelonn.behorighet.domain.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnvändareRepository {

	List<Användare> hämtaAllaAnvändare();

	Optional<Användare> hämtaAnvändare(UUID id);

	Användare uppdateraAnvändare(Användare användare);

	Användare sparaAnvändare(Användare användare);

}
