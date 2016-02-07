package se.evelonn.behorighet.infrastructure.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.core.UriInfo;

import se.evelonn.behorighet.api.RättighetRepresentation;
import se.evelonn.behorighet.domain.model.Rättighet;

public class RättighetConverter {

	public interface Converter {

		public List<RättighetRepresentation> konvertera(List<Rättighet> rättigheter);

		public RättighetRepresentation konvertera(Rättighet rättighet);
	}

	public static Converter converter(UriInfo uriInfo) {
		return new InternalRättighetConverter(uriInfo);
	}

	public static class InternalRättighetConverter implements Converter {

		private UriInfo uriInfo;

		public InternalRättighetConverter(UriInfo uriInfo) {
			this.uriInfo = uriInfo;
		}

		@Override
		public List<RättighetRepresentation> konvertera(List<Rättighet> rättigheter) {
			return rättigheter.stream().map(r -> konvertera(r)).collect(Collectors.toList());
		}

		@Override
		public RättighetRepresentation konvertera(Rättighet rättighet) {
			RättighetRepresentation rättighetRepresentation = new RättighetRepresentation();
			rättighetRepresentation.setNamn(rättighet.namn());
			rättighetRepresentation.setBeskrivning(rättighet.beskrivning());
			return rättighetRepresentation;
		}

	}
}
