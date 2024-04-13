package Testcases;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.restassured.path.json.JsonPath;

public class JsonExtracter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(jsonExtractor());
		
	}
	
	public static List<HashMap<String, String>> jsonExtractor()
	{
		JsonPath json = new JsonPath(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\TestData\\TestData.json"));
		System.out.println(json.getInt("$.size()"));
		
		List<HashMap<String, String>> add = new ArrayList<HashMap<String,String>>();
		
		for(int i=0;i<json.getInt("$.size()");i++)
		{
			HashMap<String ,String> map = new HashMap<String, String>();
			
			map.put("email", json.getString("["+i+"].email"));
			map.put("password", json.getString("["+i+"].password"));
			map.put("productName", json.getString("["+i+"].productName"));
			
			add.add(map);
			
		}
		
		return add;
		
	}

}
