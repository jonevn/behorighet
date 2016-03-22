package se.evelonn.behorighet.web.setup;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.apache.wicket.protocol.http.WicketFilter;

@WebFilter(value = "/*", initParams = {
		@WebInitParam(name = "applicationClassName", value = "se.evelonn.behorighet.web.setup.BehorighetApplication"),
		@WebInitParam(name = "filterMappingUrlPattern", value = "/*") })
public class BehorighetFilter extends WicketFilter {

}
