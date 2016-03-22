package se.evelonn.behorighet.web.components;

import org.apache.wicket.markup.html.WebMarkupContainer;

public class VisibilityWebMarkupContainer extends WebMarkupContainer {

	public VisibilityWebMarkupContainer(String id, boolean visible) {
		super(id);
		this.setVisible(visible);
		this.setOutputMarkupId(true);
	}

}
