package se.evelonn.behorighet.infrastructure.rs;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import se.evelonn.behorighet.api.AnvändarrollRepresentation;
import se.evelonn.behorighet.api.LinkRepresentation;
import se.evelonn.behorighet.domain.model.Användare;
import se.evelonn.behorighet.domain.model.Roll;

public class AnvändarrollConverter {

	public interface Converter {

		public List<AnvändarrollRepresentation> konvertera(Användare användare);

	}

	public static Converter converter(UriInfo uriInfo) {
		return new InternalAnvändarrollConverter(uriInfo);
	}

	public static class InternalAnvändarrollConverter implements Converter {

		private UriInfo uriInfo;

		public InternalAnvändarrollConverter(UriInfo uriInfo) {
			this.uriInfo = uriInfo;
		}

		private AnvändarrollRepresentation konvertera(UUID användarId, Roll roll) {
			AnvändarrollRepresentation användarrollRepresentation = new AnvändarrollRepresentation();
			användarrollRepresentation.setAnvandareId(användarId.toString());
			användarrollRepresentation.setRollId(roll.id().värde());
			användarrollRepresentation.setRollNamn(roll.namn());

			användarrollRepresentation.getLinks()
					.add(LinkRepresentation.builder()
							.medURI(uriInfo.getBaseUriBuilder()
									.segment(Paths.ANVÄNDARROLL)
									.segment(användarId.toString())
									.segment(roll.id().värde())
									.build()
									.toString())
							.medRelation("tabort")
							.medHttpMethod(HttpMethod.DELETE)
							.medMediaType(MediaType.APPLICATION_JSON)
							.build());

			return användarrollRepresentation;
		}

		@Override
		public List<AnvändarrollRepresentation> konvertera(Användare användare) {
			return användare.roller().stream().map(r -> konvertera(användare.id(), r)).collect(Collectors.toList());
		}

	}
}
