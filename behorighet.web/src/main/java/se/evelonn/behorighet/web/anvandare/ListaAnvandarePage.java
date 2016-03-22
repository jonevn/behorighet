package se.evelonn.behorighet.web.anvandare;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import se.evelonn.behorighet.api.AnvändareRepresentation;
import se.evelonn.behorighet.web.base.BasePage;

public class ListaAnvandarePage extends BasePage<List<AnvändareRepresentation>> {

	private AnvändareModel currentSelected;

	public ListaAnvandarePage(PageParameters parameters) {
		super(parameters);

		WebMarkupContainer listContainer = new WebMarkupContainer("listContainer");

		ListView<AnvändareModel> listView = new ListView<AnvändareModel>("anvandare", new ListaAnvändareModel()) {

			@Override
			protected void populateItem(ListItem<AnvändareModel> item) {
				AnvändareModel användareModel = item.getModelObject();
				AnvändareRepresentation användareRepresentation = användareModel.getObject();
				item.add(new Label("anvandarnamn", användareRepresentation.getAnvandarnamn()));
				item.add(new Label("fornamn", användareRepresentation.getFornamn()));
				item.add(new Label("efternamn", användareRepresentation.getEfternamn()));
				item.add(new Label("epost", användareRepresentation.getEpost()));

				WebMarkupContainer harRoller = new WebMarkupContainer("harRoller");
				harRoller.setOutputMarkupId(true);
				harRoller.setVisible(användareModel.harRoller());
				harRoller.add(new Link<AnvändareModel>("visaRoller") {

					@Override
					public void onClick() {
						currentSelected = användareModel;
					}
				});
				item.add(harRoller);

				WebMarkupContainer kanTaBortAllaRoller = new WebMarkupContainer("kanTaBortAllaRoller");
				kanTaBortAllaRoller.setOutputMarkupId(true);
				kanTaBortAllaRoller.setVisible(användareModel.kanTaBortAllaRoller());
				kanTaBortAllaRoller.add(new Link<AnvändareModel>("taBortAllaRoller") {

					@Override
					public void onClick() {
						användareModel.taBortAllaRoller();
					}
				});
				item.add(kanTaBortAllaRoller);

				WebMarkupContainer kanTaBortAnvändare = new WebMarkupContainer("kanTaBortAnvandare");
				kanTaBortAnvändare.setOutputMarkupId(true);
				kanTaBortAnvändare.setVisible(användareModel.kanTaBort());
				kanTaBortAnvändare.add(new AjaxLink<AnvändareModel>("taBortAnvandare") {

					@Override
					public void onClick(AjaxRequestTarget target) {
						användareModel.taBortAnvändare();
						target.add(listContainer);
					}
				});
				item.add(kanTaBortAnvändare);
			}
		};

		listContainer.add(listView);
		listContainer.setOutputMarkupId(true);

		add(listContainer);
	}
}
