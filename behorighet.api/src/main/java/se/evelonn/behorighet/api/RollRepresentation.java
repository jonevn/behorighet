package se.evelonn.behorighet.api;

import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(name = "roll")
@XmlRootElement(name = "roll")
public class RollRepresentation extends BaseRepresentation {

	@XmlElement(name = "id")
	@XmlJavaTypeAdapter(UUIDJavaAdapter.class)
	private UUID id;

	@XmlElement(name = "namn")
	private String namn;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNamn() {
		return namn;
	}

	public void setNamn(String namn) {
		this.namn = namn;
	}
}