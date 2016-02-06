package se.evelonn.behorighet.infrastructure.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import se.evelonn.behorighet.api.LinkRepresentation;
import se.evelonn.behorighet.api.RollRepresentation;
import se.evelonn.behorighet.domain.model.Roll;
import se.evelonn.behorighet.domain.model.RollId;

public class RollConverter {

	public interface Converter {
		RollRepresentation konvertera(Roll roll);

		List<RollRepresentation> konvertera(List<Roll> roller);

		Roll konvertera(RollRepresentation rollRepresentation);

		Roll konverteraNy(RollRepresentation rollRepresentation);
	}

	public static Converter converter(UriInfo uriInfo) {
		return new InternalRollConverter(uriInfo);
	}

	static class InternalRollConverter implements Converter {

		private UriInfo uriInfo;

		public InternalRollConverter(UriInfo uriInfo) {
			this.uriInfo = uriInfo;
		}

		@Override
		public List<RollRepresentation> konvertera(List<Roll> roller) {
			return roller.stream().map(r -> konvertera(r)).collect(Collectors.toList());
		}

		@Override
		public RollRepresentation konvertera(Roll roll) {
			RollRepresentation rollRepresentation = new RollRepresentation();
			rollRepresentation.setId(roll.id().värde());
			rollRepresentation.setNamn(roll.namn());
			rollRepresentation.setBeskrivning(roll.beskrivning());

			rollRepresentation.getLinks()
					.addAll(roll.rättigheter()
							.stream()
							.map(r -> LinkRepresentation.builder()
									.medURI(uriInfo.getBaseUriBuilder()
											.segment("rattighet")
											.segment(r.id().toString())
											.build()
											.toString())
									.medRelation("rättighet")
									.medHttpMethod(HttpMethod.GET)
									.medMediaType(MediaType.APPLICATION_JSON)
									.build())
							.collect(Collectors.toList()));

			return rollRepresentation;
		}

		@Override
		public Roll konvertera(RollRepresentation rollRepresentation) {
			return Roll.från(RollId.från(rollRepresentation.getId()), rollRepresentation.getNamn(),
					rollRepresentation.getBeskrivning());
		}

		@Override
		public Roll konverteraNy(RollRepresentation rollRepresentation) {
			return Roll.skapaNy(rollRepresentation.getNamn(), rollRepresentation.getBeskrivning());
		}
	}
}