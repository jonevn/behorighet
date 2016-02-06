package se.evelonn.behorighet.infrastructure.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.UriInfo;

import se.evelonn.behorighet.api.AnvändareRepresentation;
import se.evelonn.behorighet.api.LinkRepresentation;
import se.evelonn.behorighet.domain.model.Användare;
import se.evelonn.behorighet.domain.model.AnvändareBuilder;

public class AnvändareConverter {

	public interface Converter {

		public AnvändareRepresentation konvertera(Användare användare);

		public List<AnvändareRepresentation> konvertera(List<Användare> användarlista);

		public Användare konvertera(AnvändareRepresentation användareRepresentation);

		public Användare konverteraNy(AnvändareRepresentation användareRepresentation);
	}

	public static Converter converter(UriInfo uriInfo) {
		return new InternalAnvändareConverter(uriInfo);
	}

	static class InternalAnvändareConverter implements Converter {

		private UriInfo uriInfo;

		public InternalAnvändareConverter(UriInfo uriInfo) {
			this.uriInfo = uriInfo;
		}

		@Override
		public AnvändareRepresentation konvertera(Användare användare) {
			AnvändareRepresentation användareRepresentation = new AnvändareRepresentation();
			användareRepresentation.setId(användare.id());
			användareRepresentation.setAnvandarnamn(användare.användarnamn());
			användareRepresentation.setFornamn(användare.förnamn());
			användareRepresentation.setEfternamn(användare.efternamn());
			användareRepresentation.setEpost(användare.epost());

			användareRepresentation.getLinks()
					.add(LinkRepresentation.builder()
							.medURI(uriInfo.getBaseUriBuilder()
									.segment(Paths.ANVÄNDARE)
									.segment(användare.id().toString())
									.build()
									.toString())
							.medRelation("taBort")
							.medHttpMethod(HttpMethod.DELETE)
							.build());

			if (!användare.roller().isEmpty()) {
				användareRepresentation.getLinks().add(LinkRepresentation.builder()
						.medURI(uriInfo.getBaseUriBuilder()
								.segment(Paths.ANVÄNDARROLL)
								.segment(användare.id().toString())
								.build()
								.toString())
						.medRelation("roller")
						.build());

				användareRepresentation.getLinks().add(LinkRepresentation.builder()
						.medURI(uriInfo.getBaseUriBuilder()
								.segment(Paths.ANVÄNDARROLL)
								.segment(användare.id().toString())
								.build()
								.toString())
						.medRelation("roller")
						.medHttpMethod(HttpMethod.DELETE)
						.build());
			}

			return användareRepresentation;
		}

		@Override
		public List<AnvändareRepresentation> konvertera(List<Användare> användarlista) {
			return användarlista.stream().map(a -> konvertera(a)).collect(Collectors.toList());
		}

		@Override
		public Användare konvertera(AnvändareRepresentation användareRepresentation) {
			return AnvändareBuilder.builder()
					.medId(användareRepresentation.getId())
					.medAnvändarnamn(användareRepresentation.getAnvandarnamn())
					.medFörnamn(användareRepresentation.getFornamn())
					.medEfternamn(användareRepresentation.getEfternamn())
					.medEpost(användareRepresentation.getEpost())
					.build();
		}

		@Override
		public Användare konverteraNy(AnvändareRepresentation användareRepresentation) {
			return AnvändareBuilder.builder()
					.ny()
					.medAnvändarnamn(användareRepresentation.getAnvandarnamn())
					.medFörnamn(användareRepresentation.getFornamn())
					.medEfternamn(användareRepresentation.getEfternamn())
					.medEpost(användareRepresentation.getEpost())
					.build();
		}
	}
}