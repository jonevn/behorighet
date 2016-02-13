package se.evelonn.behorighet.infrastructure.rs;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import se.evelonn.behorighet.application.service.AnvändareService;
import se.evelonn.behorighet.application.service.RollService;
import se.evelonn.behorighet.domain.model.Användare;
import se.evelonn.behorighet.domain.model.Roll;
import se.evelonn.behorighet.domain.model.RollId;

@Stateless
@Path(Paths.ANVÄNDARROLL)
public class AnvändarrollResource extends BaseResource {

	@Inject
	private AnvändareService användareService;

	@Inject
	private RollService rollService;

	@GET
	@Path("{id}")
	public Response hämtaAnvändarrollerFörAnvändareMedId(@PathParam("id") String id) {
		Användare användare = användareService.hämtaAnvändare(UUID.fromString(id));
		return Response.ok(AnvändarrollConverter.converter(uriInfo).konvertera(användare)).build();
	}

	@GET
	@Path("{id}/tillgangliga")
	public Response hämtaRollerTillgängligaAttTilldelaEnAnvändare(@PathParam("id") String id) {
		Användare användare = användareService.hämtaAnvändare(UUID.fromString(id));
		List<Roll> roller = rollService.hämtaAllaRoller();
		return Response
				.ok(AnvändarrollConverter.converter(uriInfo).konvertera(användare.id(),
						roller.stream().filter(r -> !användare.roller().contains(r)).collect(Collectors.toList())))
				.build();
	}

	@PUT
	@Path("{anvandarId}/{rollId}")
	public Response läggTillRollFörAnvändare(@PathParam("rollId") String rollId,
			@PathParam("anvandarId") String användarId) {
		Användare användare = användareService.hämtaAnvändare(UUID.fromString(användarId));
		användare.läggTillRoll(rollService.hämtaRoll(RollId.från(rollId)));
		// TODO: Notera! Eftersom vi läst upp användare och lägger till roll, så
		// kommer användaren att sparas automatiskt när transaktionen stängs.
		// Detta är inte helt självklart, eftersom att vi inte aktivt sparar
		return Response.ok(AnvändareConverter.converter(uriInfo).konvertera(användare)).build();
	}

	@DELETE
	@Path("{id}")
	public Response taBortAllaRollerFrånAnvändare(@PathParam("id") String id) {
		Användare användare = användareService.taBortAllaRollerFrånAnvändare(UUID.fromString(id));
		return Response.ok().entity(AnvändareConverter.converter(uriInfo).konvertera(användare)).build();
	}

	@DELETE
	@Path("{anvandarId}/{rollId}")
	public Response taBortRollFrånAnvändare(@PathParam("anvandarId") String användarId,
			@PathParam("rollId") String rollId) {
		användareService.taBortRollFrånAnvändare(UUID.fromString(användarId), RollId.från(UUID.fromString(rollId)));
		return Response.ok().build();
	}
}