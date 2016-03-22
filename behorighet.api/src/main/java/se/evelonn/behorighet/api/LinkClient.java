package se.evelonn.behorighet.api;

import javax.ws.rs.HttpMethod;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class LinkClient {

	public static <T> T follow(LinkRepresentation link, Class<T> returnClass) {

		ResteasyWebTarget target = target(link);

		switch (link.getHttpMethod()) {
		case HttpMethod.GET:
			Response response = target.request().get();
			T entity = response.readEntity(returnClass);
			response.close();
			return entity;
		default:
			throw new IllegalArgumentException("Could not execute http method: " + link.getHttpMethod());
		}
	}

	public static void follow(LinkRepresentation link) {
		ResteasyWebTarget target = target(link);

		switch (link.getHttpMethod()) {
		case HttpMethod.DELETE:
			target.request().delete();
			break;
		default:
			throw new IllegalArgumentException("Could not execute http method: " + link.getHttpMethod());
		}
	}

	private static ResteasyWebTarget target(LinkRepresentation link) {
		ResteasyWebTarget target = new ResteasyClientBuilder().build().target(link.getUri());
		link.getMediaTypes().stream().forEach(m -> target.request().accept(m));
		return target;
	}

}
