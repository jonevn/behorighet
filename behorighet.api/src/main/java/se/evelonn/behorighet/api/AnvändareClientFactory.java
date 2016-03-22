package se.evelonn.behorighet.api;

import javax.ws.rs.client.WebTarget;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public class AnvändareClientFactory {

	public static AnvändareClient getClient(String basePath) {

		ResteasyClient client = new ResteasyClientBuilder().build();
		WebTarget target = client.target(basePath);
		ResteasyWebTarget rtarget = (ResteasyWebTarget) target;

		return rtarget.proxy(AnvändareClient.class);
	}

}
