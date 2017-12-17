package testproject.projectutls;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

public class ErrorMessages {
	
	  public static String getErrormsg(String status,String mssg) {
			StringWriter strWtr = null;
			try {

				JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();
				jsonBuilder.add("status", status);
				jsonBuilder.add("mssg", mssg);
				JsonObject empObj = jsonBuilder.build();

				strWtr = new StringWriter();
				try (JsonWriter jsonWtr = Json.createWriter(strWtr)) {
					jsonWtr.writeObject(empObj);
				}
				System.out.println(strWtr.toString());

			} catch (Exception e) {
			}
			return strWtr.toString();
		}


}
