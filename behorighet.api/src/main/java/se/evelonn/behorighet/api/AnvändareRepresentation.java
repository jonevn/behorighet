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
	private String anvandarnamn;

	@XmlElement(name = "fornamn")
	private String fornamn;

	@XmlElement(name = "efternamn")
	private String efternamn;

	@XmlElement(name = "epost")
	private String epost;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getAnvandarnamn() {
		return anvandarnamn;
	}

	public void setAnvandarnamn(String anvandarnamn) {
		this.anvandarnamn = anvandarnamn;
	}

	public String getFornamn() {
		return fornamn;
	}

	public void setFornamn(String fornamn) {
		this.fornamn = fornamn;
	}

	public String getEfternamn() {
		return efternamn;
	}

	public void setEfternamn(String efternamn) {
		this.efternamn = efternamn;
	}

	public String getEpost() {
		return epost;
	}

	public void setEpost(String epost) {
		this.epost = epost;
	}
}