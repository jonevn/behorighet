package se.evelonn.behorighet.infrastructure.rs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.io.StringWriter;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import se.evelonn.behorighet.api.AnvändareRepresentation;
import se.evelonn.behorighet.application.service.AnvändareService;

public class AnvändareResourceTest {

	private Dispatcher dispatcher;

	@Mock
	private AnvändareService användareService;

	@InjectMocks
	private AnvändareResource användareResource;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);

		dispatcher = MockDispatcherFactory.createDispatcher();
		dispatcher.getRegistry().addSingletonResource(användareResource);

		dispatcher.getDefaultContextObjects().put(UriInfo.class, mock(UriInfo.class));
	}

	@Test
	public void shouldReturn400WhenInvalidAnvändare() throws Exception {
		MockHttpRequest request = MockHttpRequest.post("/anvandare");
		AnvändareRepresentation användareRepresentation = new AnvändareRepresentation();

		användareRepresentation.setAnvandarnamn("John");
		användareRepresentation.setEfternamn("Doe");
		användareRepresentation.setEpost("myemail");
		användareRepresentation.setFornamn("John");

		request.contentType(MediaType.APPLICATION_JSON);
		request.content(convertToJson(användareRepresentation).getBytes());
		MockHttpResponse response = new MockHttpResponse();

		dispatcher.invoke(request, response);

		System.out.println(response.getContentAsString());

		assertThat(response.getStatus()).isEqualTo(HttpServletResponse.SC_BAD_REQUEST);
		// Assert.assertEquals("basic", response.getContentAsString());
	}

	private String convertToJson(AnvändareRepresentation användareRepresentation) throws Exception {

		ObjectMapper objectMapper = new ObjectMapper();

		objectMapper.getDeserializationConfig().withAnnotationIntrospector(new JaxbAnnotationIntrospector());

		StringWriter stringWriter = new StringWriter();
		objectMapper.writeValue(stringWriter, användareRepresentation);

		return stringWriter.toString();
	}
}
