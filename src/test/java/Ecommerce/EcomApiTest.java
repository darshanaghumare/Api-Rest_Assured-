 package Ecommerce;

 import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
 import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
 import static org.hamcrest.Matchers.*;
 import static org.testng.Assert.assertEquals;

import java.io.File;

public class EcomApiTest {
	
	public static void main(String[]args) {
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
		setContentType(ContentType.JSON).build();
		
		LoginRequest lr=new LoginRequest();
		LoginResponse ls=new LoginResponse();
		lr.setUserEmail("rahulshetty@gmail.com");
		lr.setPassWord("IamKing@000");
		RequestSpecification reqLogin=given().log().all().spec(req).body(lr);
		
		LoginResponse loginResponse=reqLogin.when().post("/api/ecom/auth/login")		
		.then().log().all().extract().response().as(LoginResponse.class);
		
		System.out.println(loginResponse.getToken());
		String token=loginResponse.getToken();
		System.out.println(loginResponse.getUserID());
		String userID=loginResponse.getUserID();
		
	
		//add product
		
		
		RequestSpecification addproductBasereq=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
				setContentType(ContentType.JSON).build();
		
		RequestSpecification addproduct=given().log().all().spec(addproductBasereq).param("productName", "Laptop")
		.param("productAddBy", "userID").param("productCategory", "fashion")
		.multiPart("productImage",new File("//users//rahulshetty//documnets//laptop.png"));
	
	}

}
