package com.task.taskmanager;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.task.taskmanager.models.entity.Categories;
import com.task.taskmanager.models.entity.Label;
import com.task.taskmanager.models.entity.Task;
import com.task.taskmanager.models.entity.User;

public class Test {

	public static void addUser( String userid, String[] labelid, String[] taskid,String[] dates) throws JsonProcessingException {

		User emp = new User();

		emp.setEmail("test@gmail.com");
		emp.setId(userid);
		emp.setPassword("password");
		emp.setUsername("username");
		List<Task> listTasks = new ArrayList<Task>();
		int i=0;
		for(String lab:labelid) {
			Task task = new Task();
			task.setContent("content"+i);
			task.setDeadline(dates[i]);
			task.setDescription("value"+i);
			task.setId(taskid[i]);
			task.setName("value"+i);
			task.setUser(emp);
			task.setUsero(emp.getId());
			Label label = new Label();
			label.setId(lab);
			label.setCategory(Categories.Critical);
			label.setTask(task);
			task.setLabel(label);
			label.setTaskid(task.getId());
			listTasks.add(task);
			i++;
		}
		emp.setListTasks(listTasks);


		
		ObjectMapper mapper = new ObjectMapper();
		

		//Object to JSON in file
	//	mapper.writeValue(new File("c:\\file.json"), obj);

		//Object to JSON in String
		String jsonInString = mapper.writeValueAsString(emp);
		System.out.println(jsonInString);
		
//		javax.ws.rs.client.Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));
//
//		WebTarget webTarget = client.target("http://localhost:8082/taskmanager/metadata").path("save");
//
//		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//
//		Response response = invocationBuilder.post(Entity.entity(emp, MediaType.APPLICATION_JSON));
//		System.out.println(response);
		
javax.ws.rs.client.Client client = ClientBuilder.newClient(new ClientConfig().register(LoggingFilter.class));

	WebTarget webTarget = client.target("http://localhost:8082/taskmanager/metadata").path("save");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(jsonInString, MediaType.APPLICATION_JSON));
		System.out.println(response);
	}

	public static void main(String[] args) throws JsonProcessingException {
		String[] arrlabel = {"1","2","4","3"};
		String[] task={"1","2","4","3"};
		String[] dates = {"17-01-2019","18-04-2019","19-04-2019","21-01-2019"};
		addUser("1",arrlabel,task,dates);
	}

}
