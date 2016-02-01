package se.evelonn.behorighet.api;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "anvandare")
@XmlType(name = "anvandare", namespace = BaseRepresentation.NAMESPACE)
public class AnvändareRepresentation extends BaseRepresentation {

	@XmlElement(name = "id")
	@XmlJavaTypeAdapter(UUIDJavaAdapter.class)
	private UUID id;

	@XmlElement(name = "anvandarnamn")
	private String användarnamn;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getAnvändarnamn() {
		return användarnamn;
	}

	public void setAnvändarnamn(String användarnamn) {
		this.användarnamn = användarnamn;
	}
}