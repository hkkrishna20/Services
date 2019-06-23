import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.Charsets;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.encomm.models.content.entityTypeP.ResponseObject;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;

public class TestMain {
	public static void main(String[] args) throws IOException, JAXBException, URISyntaxException {
		// TODO Auto-generated method stub
		
		String urlSave = "http://localhost:2018/IntegrationRest/encomm/save";
		
		byte[] encoded = Files.readAllBytes(Paths.get("C:\\Users\\Public\\POC files\\text.txt"));
		String content = new String(encoded, Charsets.UTF_8);
		postClient(urlSave, content);
		
/*		String url = "http://localhost:2018/IntegrationRest/encomm/getAll";
		getClient(url);
		String urlsend = "http://localhost:2018/IntegrationRest/sendMail";

		String url1 = "http://localhost:2018/IntegrationRest/encomm/update/954479618";
		encoded = Files.readAllBytes(Paths.get("C:\\Users\\Public\\POC files\\text.txt"));
		 content = new String(encoded, Charsets.UTF_8);
		updateClient(url1, content);
*/	}

	public static String convertObjectToXML(Object object) {
		try {
			StringWriter stringWriter = new StringWriter();
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(object, stringWriter);
			return stringWriter.toString();
		} catch (JAXBException e) {
			System.err.println(String.format("Exception while marshalling: %s", e.getMessage()));
		}
		return null;
	}

	protected static <T> T convertXMLToObject(Class<T> clazz, String xml) throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(clazz);
		Unmarshaller unmarshaller = jc.createUnmarshaller();
		return unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), clazz).getValue();
	}

	public <T> T unMarshal(String content, Class<T> clasz) {
		try {
			JAXBContext jc = JAXBContext.newInstance(clasz);
			Unmarshaller u = jc.createUnmarshaller();
			return u.unmarshal(new StreamSource(new StringReader(content)), clasz).getValue();
		} catch (JAXBException e) {
		}
		return null;
	}

	public static String marshal(Object object) throws JAXBException {
		StringWriter stringWriter = new StringWriter();
		JAXBContext jc = JAXBContext.newInstance(object.getClass());
		Marshaller m = jc.createMarshaller();
		m.marshal(object, stringWriter);
		System.out.println(stringWriter.toString());
		return stringWriter.toString();
	}

	public static String marshalToJson(Object o) throws JAXBException {

		ObjectMapper mapper = new ObjectMapper();
		String jsonVal = "";
		try {
			jsonVal = mapper.writeValueAsString(o);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonVal;
	}

	static void postClient(String url, String object) {

		System.out.println("-----------"+object.trim());
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type("application/xml").post(ClientResponse.class, object);
		if (resp.getStatus() != 200 || resp.getStatus() != 201) {
			System.err.println("" + resp);
		}
		String output = resp.getEntity(String.class);
		System.out.println(output);
	}
	static void updateClient(String url, String object) {

		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type("application/xml").put(ClientResponse.class, object);
		if (resp.getStatus() != 200 || resp.getStatus() != 201) {
			System.err.println("" + resp);
		}
		String output = resp.getEntity(String.class);
		System.out.println(output);
	}

	static String getClient(String url) throws JAXBException {
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/xml").get(ClientResponse.class);
		if (resp.getStatus() != 200 || resp.getStatus() != 201) {
			System.err.println("" + resp);
		}
		List<ResponseObject> output = resp.getEntity(new GenericType<List<ResponseObject>>() {
		});
		String o = "";
		for (int i = 0; i < output.size(); i++) {
			ResponseObject response = output.get(i);
			o = o + convertObjectToXML(response);
		}
		System.out.println(o);
		return o;
		// Object tests = unmarshaller.unmarshal((new StringReader(output)));

	}

}
