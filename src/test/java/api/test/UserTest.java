package api.test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTest { 
	Faker faker;
	User user;
	public Logger logger;
	@BeforeClass
	public void setupData()
	{
		faker = new Faker();
		user = new User();
		user.setId(faker.idNumber().hashCode());
		user.setUsername(faker.name().username());
		user.setFirstname(faker.name().firstName());
		user.setLastname(faker.name().lastName());
		user.setEmail(faker.internet().safeEmailAddress());
		user.setPassword(faker.internet().password(5,10));
		user.setPhone(faker.phoneNumber().cellPhone());
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testPostUser()
	{
		logger.info("Creating User");
		Response response = UserEndPoints.CreateUser(user);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	@Test(priority = 2)
	public void testGetUser()
	{
		logger.info("Reading User");
		Response response = UserEndPoints.ReadUser(this.user.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("Updating User");
		user.setFirstname(faker.name().firstName());
		user.setLastname(faker.name().lastName());
		user.setEmail(faker.internet().safeEmailAddress());
		Response response = UserEndPoints.UpdateUser(user,this.user.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		logger.info("Deleting User");
		Response response = UserEndPoints.DeleteUser(this.user.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
	}
}
