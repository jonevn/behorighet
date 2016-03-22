package se.evelonn.behorighet.web.anvandare;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.wicket.model.LoadableDetachableModel;

import se.evelonn.behorighet.api.AnvändareClientFactory;

public class ListaAnvändareModel extends LoadableDetachableModel<List<AnvändareModel>> {

	@Override
	protected List<AnvändareModel> load() {
		return AnvändareClientFactory.getClient("http://localhost:8080/behorighet")
				.hämtaAllaAnvändare()
				.stream()
				.map(a -> new AnvändareModel(a))
				.collect(Collectors.toList());
	}

}
