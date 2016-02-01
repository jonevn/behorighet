package se.evelonn.behorighet.infrastructure.rs;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
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
import se.evelonn.behorighet.domain.model.RollId;

@Stateless
@Path(AnvändareResource.PATH)
public class AnvändareResource extends BaseResource {

	public static final String PATH = "anvandare";

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
	public Response uppdateraAnvändare(AnvändareRepresentation användareRepresentation) {
		Användare användare = användareService
				.uppdateraAnvändare(AnvändareConverter.converter(uriInfo).konvertera(användareRepresentation));
		return Response.ok(AnvändareConverter.converter(uriInfo).konvertera(användare)).build();
	}

	@POST
	public Response skapaAnvändare(AnvändareRepresentation användareRepresentation) {
		Användare användare = användareService
				.skapaAnvändare(AnvändareConverter.converter(uriInfo).konverteraNy(användareRepresentation));
		return Response.created(
				uriInfo.getBaseUriBuilder().segment(AnvändareResource.PATH).segment(användare.id().toString()).build())
				.build();
	}

	@DELETE
	@Path("{id}/{rollId}")
	public Response taBortRollFrånAnvändare(@PathParam("id") String id, @PathParam("rollId") String rollId) {
		Användare användare = användareService.taBortRollFrånAnvändare(UUID.fromString(id),
				RollId.från(UUID.fromString(rollId)));
		return Response.ok().entity(AnvändareConverter.converter(uriInfo).konvertera(användare)).build();
	}

	@DELETE
	@Path("{id}/roller")
	public Response taBortAllaRollerFrånAnvändare(@PathParam("id") String id) {
		Användare användare = användareService.taBortAllaRollerFrånAnvändare(UUID.fromString(id));
		return Response.ok().entity(AnvändareConverter.converter(uriInfo).konvertera(användare)).build();
	}
}