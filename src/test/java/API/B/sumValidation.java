package API.B;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.playload;
import io.restassured.path.json.JsonPath;

public class sumValidation {
	
	@Test
	public void sumofCourses() {
		
		int sum=0;
		JsonPath js=new JsonPath(playload.courseparse());
		
		int count=js.getInt("courses.size()");
		
		for (int i = 0;i<count;i++)
		{
			
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			int amount=price*copies;
			System.out.println(amount);
			sum=sum+amount;				
		}
		System.out.println(sum);
		int purchaseAmount =js.getInt("dashboard.purchaseAmount");
		Assert.assertEquals(sum, purchaseAmount);
	}
	
	

}
