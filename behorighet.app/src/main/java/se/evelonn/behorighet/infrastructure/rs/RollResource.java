package se.evelonn.behorighet.infrastructure.rs;

import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import se.evelonn.behorighet.api.RollRepresentation;
import se.evelonn.behorighet.application.service.RollService;
import se.evelonn.behorighet.domain.model.Roll;
import se.evelonn.behorighet.domain.model.RollId;

@Stateless
@Path(Paths.ROLL)
public class RollResource extends BaseResource {

	@Inject
	private RollService rollService;

	@GET
	public Response hämtaAllaRoller() {
		List<Roll> roller = rollService.hämtaAllaRoller();
		return Response.ok().entity(RollConverter.converter(uriInfo).konvertera(roller)).build();
	}

	@GET
	@Path("{id}")
	public Response hämtaRoll(@PathParam("id") String id) {
		Roll roll = rollService.hämtaRoll(RollId.från(UUID.fromString(id)));
		return Response.ok().entity(RollConverter.converter(uriInfo).konvertera(roll)).build();
	}

	@PUT
	public Response uppdateraRoll(RollRepresentation rollRepresentation) {
		Roll roll = rollService.uppdateraRoll(RollConverter.converter(uriInfo).konvertera(rollRepresentation));
		return Response.ok().entity(RollConverter.converter(uriInfo).konvertera(roll)).build();
	}

	@POST
	public Response skapaRoll(RollRepresentation rollRepresentation) {
		Roll roll = rollService.skapaRoll(RollConverter.converter(uriInfo).konverteraNy(rollRepresentation));

		return Response.created(uriInfo.getBaseUriBuilder().segment(Paths.ROLL).segment(roll.id().värde()).build())
				.build();
	}
}
