package se.evelonn.behorighet.infrastructure.rs;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Produces({ MediaType.APPLICATION_JSON })
public abstract class BaseResource {

	@Context
	protected UriInfo uriInfo;
}
