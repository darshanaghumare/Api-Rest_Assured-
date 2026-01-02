package API.B;

import Files.playload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

			
		JsonPath js=new JsonPath(playload.courseparse());
		
		//Print No of courses returned by API
	int count=js.getInt("courses.size()");
	System.out.println(count);
	
	//print purchase amount
	int totalAmount= js.getInt("dashboard.purchaseAmount");
	System.out.println(totalAmount);
	
	//print title of the 1st course
	
	String title=js.get("courses[0].title");
	System.out.println(title);
	
	//print all courses titles and their respctive prices
	
	for (int i = 0;i<count;i++)
	{
		String courseTitle=(js.get("courses["+i+"].title"));
		System.out.println(courseTitle);
	 System.out.println(js.get("courses["+i+"].price").toString());
		
	}
	
	//print no of copies sold by RPA course
	
	for (int i = 0;i<count;i++)
	{
		String courseTitle=(js.get("courses["+i+"].title"));
		if(courseTitle.equalsIgnoreCase("RPA"))
				{
			int copies=js.get("courses["+i+"].copies");
			System.out.println(copies);
				}
	
	
}
	}}
		
