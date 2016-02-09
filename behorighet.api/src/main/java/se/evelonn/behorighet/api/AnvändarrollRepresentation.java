package se.evelonn.behorighet.api;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "anvandarroll")
@XmlType(name = "anvandarroll", namespace = BaseRepresentation.NAMESPACE)
public class AnvändarrollRepresentation extends BaseRepresentation {

	@XmlElement(name = "anvandareId")
	private String anvandareId;

	@XmlElement(name = "rollId")
	private String rollId;

	@XmlElement(name = "rollNamn")
	private String rollNamn;

	@XmlElement(name = "beskrivning")
	private String beskrivning;

	public String getAnvandareId() {
		return anvandareId;
	}

	public void setAnvandareId(String anvandareId) {
		this.anvandareId = anvandareId;
	}

	public String getRollId() {
		return rollId;
	}

	public void setRollId(String rollId) {
		this.rollId = rollId;
	}

	public String getRollNamn() {
		return rollNamn;
	}

	public void setRollNamn(String rollNamn) {
		this.rollNamn = rollNamn;
	}

	public String getBeskrivning() {
		return beskrivning;
	}

	public void setBeskrivning(String beskrivning) {
		this.beskrivning = beskrivning;
	}
}