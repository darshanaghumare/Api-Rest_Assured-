package Autorization;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import Pojo.Api;
import Pojo.GetCourse;
import Pojo.WebAutomation;
import groovy.transform.stc.POJO;

public class Oauth {
	
	public static void main(String[]args) {
		
		String [] courseTitles= {"seleniumwebdriver java","cypress","protractor"};
		
		String response =given()
		.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
	    .formParams("grant_type","client_credentials")
	    .formParams("scope", "trust")
	    .when().log().all()
		.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").asString();
		
		System.out.println(response);
		JsonPath js=new JsonPath(response);
		String accesstoken=js.getString("access_token");
		//System.out.println(accesstoken);
		
		GetCourse gc=given()
				.queryParams("access_token", accesstoken)
				.when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		//System.out.println(gc);
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getUrl());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		
		
		List<Api> apicourses=gc.getCourses().getApi();
		for(int i=0;i<apicourses.size();i++)
		{
			if (apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
					{
				System.out.println(apicourses.get(i).getPrice());
					}
		}
		
		ArrayList<String> a=new ArrayList<String> ();
	
		List<WebAutomation> w=gc.getCourses().getWebAutomation();
		
		for(int j=0;j<w.size(); j++)
		{
			a.add(w.get(j).getCourseTitle());
		}
		
		List<String> expectedList=Arrays.asList(courseTitles);
		Assert.assertTrue(a.equals(expectedList));
	}
	

}
