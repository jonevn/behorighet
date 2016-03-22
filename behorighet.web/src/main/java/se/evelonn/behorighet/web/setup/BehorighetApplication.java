package se.evelonn.behorighet.web.setup;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import de.agilecoders.wicket.core.Bootstrap;
import se.evelonn.behorighet.web.base.StartPage;

public class BehorighetApplication extends WebApplication {

	@Override
	protected void init() {
		super.init();

		Bootstrap.install(this);
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return StartPage.class;
	}

}
