package se.evelonn.behorighet.infrastructure.rs;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import se.evelonn.behorighet.application.service.RättighetService;

@Stateless
@Path(Paths.RÄTTIGHET)
public class RättighetResource extends BaseResource {

	@Inject
	private RättighetService rättighetService;

	@GET
	public Response hämtaAllaRättigheter() {
		return Response.ok(RättighetConverter.converter(uriInfo).konvertera(rättighetService.hämtaAllaRättigheter()))
				.build();
	}

}
