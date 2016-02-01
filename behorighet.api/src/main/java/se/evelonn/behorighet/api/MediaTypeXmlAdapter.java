package se.evelonn.behorighet.api;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class MediaTypeXmlAdapter extends XmlAdapter<String, List<String>> {

	@Override
	public List<String> unmarshal(String v) throws Exception {
		return Stream.of(v.split(", ")).collect(Collectors.toList());
	}

	@Override
	public String marshal(List<String> v) throws Exception {
		return v.stream().collect(Collectors.joining(", "));
	}
}