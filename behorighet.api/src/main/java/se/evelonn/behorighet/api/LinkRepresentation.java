package se.evelonn.behorighet.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = LinkRepresentation.NAME, namespace = BaseRepresentation.DAP_NAMESPACE)
@XmlRootElement(name = LinkRepresentation.NAME)
public class LinkRepresentation implements Serializable {

	protected static final String NAME = "link";

	@XmlAttribute(name = "uri")
	private String uri;

	@XmlAttribute(name = "httpMethod")
	private String httpMethod;

	@XmlAttribute(name = "mediaTypes")
	@XmlJavaTypeAdapter(MediaTypeXmlAdapter.class)
	private List<String> mediaTypes = new ArrayList<>();

	@XmlAttribute(name = "relation")
	private String relation;

	protected LinkRepresentation() {
		// JAXB
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public List<String> getMediaTypes() {
		return mediaTypes;
	}

	public void setMediaTypes(List<String> mediaTypes) {
		this.mediaTypes = mediaTypes;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	private LinkRepresentation(LinkBuilder linkBuilder) {
		this.httpMethod = linkBuilder.httpMethod;
		this.relation = linkBuilder.relation;
		this.uri = linkBuilder.url;
		if (linkBuilder.mediaTypes.isEmpty()) {
			this.mediaTypes.add("application/json");
		} else {
			this.mediaTypes = linkBuilder.mediaTypes;
		}
	}

	public static URLBuilder builder() {
		return new LinkBuilder();
	}

	public interface URLBuilder {
		public RelationBuilder medURI(String url);
	}

	public interface RelationBuilder {
		public Build medRelation(String relation);
	}

	public interface Build {
		public Build medHttpMethod(String httpMethod);

		public Build medMediaType(String mediaType);

		public LinkRepresentation build();
	}

	@XmlTransient
	static class LinkBuilder implements URLBuilder, RelationBuilder, Build {

		private String httpMethod = "GET";
		private List<String> mediaTypes = new ArrayList<>();
		private String relation;
		private String url;

		@Override
		public Build medHttpMethod(String httpMethod) {
			this.httpMethod = httpMethod;
			return this;
		}

		@Override
		public Build medMediaType(String mediaType) {
			this.mediaTypes.add(mediaType);
			return this;
		}

		@Override
		public LinkRepresentation build() {
			return new LinkRepresentation(this);
		}

		@Override
		public Build medRelation(String relation) {
			this.relation = relation;
			return this;
		}

		@Override
		public RelationBuilder medURI(String url) {
			this.url = url;
			return this;
		}
	}
}