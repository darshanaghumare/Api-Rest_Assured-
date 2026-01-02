package API.B;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.ReusableMethods;
import Files.playload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class DynamicJson {
	
	@Test(dataProvider="booksdata")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		given().header("Content-Type","application/json").
		body(playload.Addbook(isbn,aisle)).
		when().post("Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response().asString();
		String response = null;
		JsonPath js=ReusableMethods.rawToJson(response);
		js.get("ID");
		
		
		
	}
	
	@DataProvider(name="booksdata")
	public Object[][] getData () {
		//array=collection of elements
		//multidimensional array=collection of arrays
		return new Object[][] {{"sruie","4943"},{"gedet","38347"},{"ftyui","3337"} };
		
	}
	

}
