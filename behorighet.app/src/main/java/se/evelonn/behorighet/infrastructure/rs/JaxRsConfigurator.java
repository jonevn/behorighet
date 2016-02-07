package se.evelonn.behorighet.infrastructure.rs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.jboss.resteasy.plugins.interceptors.CorsFilter;

@ApplicationPath("")
public class JaxRsConfigurator extends Application {

	private Set<Object> singletons;

	@Override
	public Set<Class<?>> getClasses() {
		return new HashSet<>(Arrays.asList(AnvändareResource.class, AnvändarrollResource.class, RollResource.class,
				RättighetResource.class));
	}

	@Override
	public Set<Object> getSingletons() {
		if (singletons == null) {
			CorsFilter corsFilter = new CorsFilter();
			corsFilter.getAllowedOrigins().add("*");
			singletons = new HashSet<>();
			singletons.add(corsFilter);
		}
		return singletons;
	}

}
