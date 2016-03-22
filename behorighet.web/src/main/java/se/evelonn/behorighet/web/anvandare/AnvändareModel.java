package se.evelonn.behorighet.web.anvandare;

import java.util.Optional;

import javax.ws.rs.HttpMethod;

import org.apache.wicket.model.LoadableDetachableModel;

import se.evelonn.behorighet.api.AnvändareRepresentation;
import se.evelonn.behorighet.api.LinkClient;
import se.evelonn.behorighet.api.LinkRepresentation;

public class AnvändareModel extends LoadableDetachableModel<AnvändareRepresentation> {

	private AnvändareRepresentation användareRepresentation;

	public AnvändareModel(AnvändareRepresentation användareRepresentation) {
		this.användareRepresentation = användareRepresentation;
	}

	@Override
	protected AnvändareRepresentation load() {
		return användareRepresentation;
	}

	public boolean kanTaBort() {
		return användareRepresentation.harLänk("taBort", HttpMethod.DELETE);
	}

	public boolean harRoller() {
		return användareRepresentation.harLänk("roller", HttpMethod.GET);
	}

	public boolean kanTaBortAllaRoller() {
		return användareRepresentation.harLänk("roller", HttpMethod.DELETE);
	}

	public void taBortAllaRoller() {
		Optional<LinkRepresentation> taBortRollerLänk = användareRepresentation.hämtaFörstaLänk("roller",
				HttpMethod.DELETE);
		if (taBortRollerLänk.isPresent()) {
			LinkClient.follow(taBortRollerLänk.get());
		}
	}

	public void taBortAnvändare() {
		Optional<LinkRepresentation> taBortAnvändareLänk = användareRepresentation.hämtaFörstaLänk("taBort",
				HttpMethod.DELETE);
		if (taBortAnvändareLänk.isPresent()) {
			LinkClient.follow(taBortAnvändareLänk.get());
		}
	}
}
