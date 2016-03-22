package se.evelonn.behorighet.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "base")
@XmlRootElement(name = "base")
public abstract class BaseRepresentation implements Serializable {

	public static final String NAMESPACE = "http://evelonn.se/behorighet";

	public static final String DAP_NAMESPACE = "http://evelonn.se/dap";

	@XmlElement(name = "links", namespace = DAP_NAMESPACE)
	private List<LinkRepresentation> links = new ArrayList<>();

	public List<LinkRepresentation> getLinks() {
		return links;
	}

	public void setLinks(List<LinkRepresentation> links) {
		this.links = links;
	}

	public boolean harLänk(String relation, String httpMethod) {
		return this.links.stream()
				.anyMatch(l -> l.getRelation().equals(relation) && l.getHttpMethod().equals(httpMethod));
	}

	public Optional<LinkRepresentation> hämtaFörstaLänk(String relation, String httpMethod) {
		return this.links.stream()
				.filter(l -> l.getRelation().equals(relation) && l.getHttpMethod().equals(httpMethod))
				.findFirst();
	}
}