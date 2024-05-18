package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class UserEndPoints {
	
	//Reading URL or Endpoints from properties file
	static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}

	public static Response CreateUser(User payload)
	{
		String post_url = getURL().getString("post_url"); //calling getURL method which will retun Endpoint
		System.out.print(post_url);
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
			.when()
				.post(post_url);
		
		return response;
				
	}
	
	public static Response ReadUser(String username)
	{
		Response response = given()
				.pathParam("username", username)
			.when()
				.get(Routes.get_url);
		
		return response;
				
	}
	
	public static Response UpdateUser(User payload, String username)
	{
		Response response = given()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.pathParam("username", username)
			.when()
				.put(Routes.update_url);
		
		return response;
				
	}
	public static Response DeleteUser(String username)
	{
		Response response = given()
				.pathParam("username", username)
			.when()
				.delete(Routes.delete_url);
		
		return response;
				
	}
}
