package se.evelonn.behorighet.application.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;

import se.evelonn.behorighet.domain.model.Användare;
import se.evelonn.behorighet.domain.model.AnvändareRepository;
import se.evelonn.behorighet.domain.model.RollId;

public class AnvändareService {

	@Inject
	private AnvändareRepository användareRepository;

	public List<Användare> hämtaAllaAnvändare() {
		return användareRepository.hämtaAllaAnvändare();
	}

	public Användare hämtaAnvändare(UUID id) {
		Optional<Användare> användare = användareRepository.hämtaAnvändare(id);
		if (användare.isPresent()) {
			return användare.get();
		}

		throw new IllegalArgumentException("Användare med id: " + id.toString() + " finns inte");
	}

	public Användare uppdateraAnvändare(Användare användare) {
		return användareRepository.uppdateraAnvändare(användare);
	}

	public Användare skapaAnvändare(Användare användare) {
		return användareRepository.sparaAnvändare(användare);
	}

	public Användare taBortRollFrånAnvändare(UUID id, RollId rollId) {
		Användare användaren = hämtaAnvändare(id);
		användaren
				.roller(användaren.roller().stream().filter(a -> !a.id().equals(rollId)).collect(Collectors.toList()));
		return användareRepository.uppdateraAnvändare(användaren);
	}

	public Användare taBortAllaRollerFrånAnvändare(UUID id) {
		Användare användare = hämtaAnvändare(id);
		användare.roller(new ArrayList<>());
		return användareRepository.uppdateraAnvändare(användare);
	}

	public void taBortAnvändare(UUID id) {
		användareRepository.taBortAnvändare(id);

	}
}
