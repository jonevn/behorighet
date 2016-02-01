package se.evelonn.behorighet.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "rattighet")
public class RättighetRepresentation {

	@XmlElement(name = "namn")
	private String namn;

	public String getNamn() {
		return namn;
	}

	public void setNamn(String namn) {
		this.namn = namn;
	}
}