package se.evelonn.behorighet.domain.model;

import java.util.List;

public interface RättighetRepository {

	List<Rättighet> hämtaAllaRättigheter();

}
