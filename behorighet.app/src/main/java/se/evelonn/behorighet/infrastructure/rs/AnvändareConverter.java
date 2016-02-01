package se.evelonn.behorighet.infrastructure.rs;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import se.evelonn.behorighet.api.AnvändareRepresentation;
import se.evelonn.behorighet.api.LinkRepresentation;
import se.evelonn.behorighet.domain.model.Användare;

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
			användareRepresentation.setAnvändarnamn(användare.namn());

			användareRepresentation.getLinks().add(LinkRepresentation.builder()
					.medURI(uriInfo.getBaseUriBuilder()
							.segment(AnvändareResource.PATH)
							.segment(användare.id().toString())
							.build()
							.toString())
					.medRelation("taBort")
					.medHttpMethod(HttpMethod.DELETE)
					.medMediaType(MediaType.APPLICATION_JSON)
					.build());

			användareRepresentation.getLinks()
					.addAll(användare.roller()
							.stream()
							.map(a -> LinkRepresentation.builder()
									.medURI(uriInfo.getBaseUriBuilder()
											.segment(RollResource.PATH)
											.segment(a.id().värde())
											.build()
											.toString())
									.medRelation("roll")
									.medHttpMethod(HttpMethod.GET)
									.medMediaType(MediaType.APPLICATION_JSON)
									.build())
							.collect(Collectors.toList()));

			användareRepresentation.getLinks().addAll(användare.roller()
					.stream()
					.map(r -> LinkRepresentation.builder()
							.medURI(uriInfo.getBaseUriBuilder()
									.segment(AnvändareResource.PATH)
									.segment(användare.id().toString())
									.segment(r.id().värde())
									.build()
									.toString())
							.medRelation("tabortroll")
							.medHttpMethod(HttpMethod.DELETE)
							.medMediaType(MediaType.APPLICATION_JSON)
							.build())
					.collect(Collectors.toList()));

			användareRepresentation.getLinks()
					.add(LinkRepresentation.builder()
							.medURI(uriInfo.getBaseUriBuilder()
									.segment(användare.id().toString())
									.segment("roller")
									.build()
									.toString())
							.medRelation("tabortallaroller")
							.medHttpMethod(HttpMethod.DELETE)
							.medMediaType(MediaType.APPLICATION_JSON)
							.build());

			return användareRepresentation;
		}

		@Override
		public List<AnvändareRepresentation> konvertera(List<Användare> användarlista) {
			return användarlista.stream().map(a -> konvertera(a)).collect(Collectors.toList());
		}

		@Override
		public Användare konvertera(AnvändareRepresentation användareRepresentation) {
			return Användare.från(användareRepresentation.getId(), användareRepresentation.getAnvändarnamn());
		}

		@Override
		public Användare konverteraNy(AnvändareRepresentation användareRepresentation) {
			return Användare.skapa(användareRepresentation.getAnvändarnamn());
		}
	}
}