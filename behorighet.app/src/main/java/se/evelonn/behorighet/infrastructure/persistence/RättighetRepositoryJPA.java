package se.evelonn.behorighet.infrastructure.persistence;

import java.util.List;

import se.evelonn.behorighet.domain.model.Rättighet;
import se.evelonn.behorighet.domain.model.RättighetRepository;

public class RättighetRepositoryJPA extends BaseRepositoryJPA implements RättighetRepository {

	@Override
	public List<Rättighet> hämtaAllaRättigheter() {
		return entityManager.createNamedQuery(Rättighet.HÄMTA_ALLA_RÄTTIGHETER_QUERY, Rättighet.class).getResultList();
	}

}
