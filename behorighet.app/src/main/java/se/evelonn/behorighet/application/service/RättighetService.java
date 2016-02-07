package se.evelonn.behorighet.application.service;

import java.util.List;

import javax.inject.Inject;

import se.evelonn.behorighet.domain.model.Rättighet;
import se.evelonn.behorighet.domain.model.RättighetRepository;

public class RättighetService {

	@Inject
	private RättighetRepository rättighetRepository;

	public List<Rättighet> hämtaAllaRättigheter() {
		return rättighetRepository.hämtaAllaRättigheter();
	}

}
