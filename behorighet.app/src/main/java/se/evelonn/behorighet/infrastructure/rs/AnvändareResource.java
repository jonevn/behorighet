package se.evelonn.behorighet.infrastructure.rs;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import se.evelonn.behorighet.api.AnvändareRepresentation;
import se.evelonn.behorighet.application.service.AnvändareService;
import se.evelonn.behorighet.domain.model.Användare;

@Stateless
@Path(Paths.ANVÄNDARE)
public class AnvändareResource extends BaseResource {

	@Inject
	private AnvändareService användareService;

	@GET
	public Response hämtaAllaAnvändare() {
		List<Användare> allaAnvändare = användareService.hämtaAllaAnvändare();
		return Response.ok().entity(AnvändareConverter.converter(uriInfo).konvertera(allaAnvändare)).build();
	}

	@GET
	@Path("{id}")
	public Response hämtaAnvändare(@PathParam("id") String id) {
		Användare användare = användareService.hämtaAnvändare(UUID.fromString(id));
		return Response.ok().entity(AnvändareConverter.converter(uriInfo).konvertera(användare)).build();
	}

	@PUT
	public Response uppdateraAnvändare(@Valid AnvändareRepresentation användareRepresentation) {
		Användare användare = användareService
				.uppdateraAnvändare(AnvändareConverter.converter(uriInfo).konvertera(användareRepresentation));
		return Response.ok(AnvändareConverter.converter(uriInfo).konvertera(användare)).build();
	}

	@POST
	public Response skapaAnvändare(@Valid AnvändareRepresentation användareRepresentation) {
		Användare användare = användareService
				.skapaAnvändare(AnvändareConverter.converter(uriInfo).konverteraNy(användareRepresentation));
		return Response
				.created(
						uriInfo.getBaseUriBuilder().segment(Paths.ANVÄNDARE).segment(användare.id().toString()).build())
				.build();
	}

	@DELETE
	@Path("{id}")
	public Response taBortAnvändare(@PathParam("id") String id) {
		System.out.println("Tar bort användare med id:" + id);
		användareService.taBortAnvändare(UUID.fromString(id));
		return Response.ok().build();
	}
}