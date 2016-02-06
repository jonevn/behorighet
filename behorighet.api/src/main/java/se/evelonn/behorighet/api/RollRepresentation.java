package se.evelonn.behorighet.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "roll")
@XmlRootElement(name = "roll")
public class RollRepresentation extends BaseRepresentation {

	@XmlElement(name = "id")
	// @XmlJavaTypeAdapter(UUIDJavaAdapter.class)
	private String id;

	@XmlElement(name = "namn")
	private String namn;

	@XmlElement(name = "beskrivning")
	private String beskrivning;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNamn() {
		return namn;
	}

	public void setNamn(String namn) {
		this.namn = namn;
	}

	public String getBeskrivning() {
		return beskrivning;
	}

	public void setBeskrivning(String beskrivning) {
		this.beskrivning = beskrivning;
	}
}