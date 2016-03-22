package se.evelonn.behorighet.web.base;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.GenericWebPage;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.Navbar;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarButton;
import de.agilecoders.wicket.core.markup.html.bootstrap.navbar.NavbarComponents;
import se.evelonn.behorighet.web.anvandare.ListaAnvandarePage;
import se.evelonn.behorighet.web.roll.ListaRollerPage;

public class BasePage<T> extends GenericWebPage<T> {

	public BasePage(final PageParameters parameters) {
		super(parameters);

		add(createNavbar());
	}

	private Component createNavbar() {
		Navbar navbar = new Navbar("menu");
		navbar.setPosition(Navbar.Position.TOP);
		navbar.setBrandName(Model.of("Behörighetssystemet"));

		navbar.addComponents(NavbarComponents.transform(Navbar.ComponentPosition.LEFT,
				new NavbarButton<Void>(ListaAnvandarePage.class, Model.of("Lista användare")),
				new NavbarButton<Void>(ListaRollerPage.class, Model.of("Lista roller"))));

		return navbar;
	}
}
