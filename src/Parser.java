import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/**
 *  A simple program designed to take a JSON file of staff members and calculate which ones are within
 *  a certain distance of the Story Park offices.
 * 
 * @author Esme Putt
 *          
 */
public class Parser {
	
	
	int DISTANCE = 2000;
	float EARTH_RADIUS = 6378137;
	double OFFICE_LATITUDE = -41.2920728;
	double OFFICE_LONGITUDE = 174.7748162;
	String fileName = "StoryParkEmployees.json";
	
	public Parser (double officeLatitude, double officeLongitude, String filename) {
		this.OFFICE_LATITUDE = officeLatitude;
		this.OFFICE_LONGITUDE = officeLongitude;
		this.fileName = filename;
	}


	/**
	 *  Parses the JSON file and constructs a map of the employees within 2km of the Storypark offices
	 * 
	 * @return employees
	 * 			A map containing the names and distance from the Storypark offices for all
	 * 			employees living within 2km
	 *          
	 */
	public Map<String, Double> parseEmployees() throws ParseException {

		Map<String, Double> employees = new TreeMap<String, Double>();

		try {
			
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(new FileReader(fileName));
			JSONArray newArray = (JSONArray) obj.get("staff");

			for (Object employee : newArray) {
				JSONObject currentEmployee = (JSONObject) employee;

				long id =  (long) currentEmployee.get("id");
				String name = (String) currentEmployee.get("name");
				String role = (String) currentEmployee.get("role");

				JSONObject location = (JSONObject) currentEmployee.get("location");
				double longitude = (double) location.get("longitude");
				double latitude = (double) location.get("latitude");

				double distance = calculateDistance(longitude, latitude);

				if (distance < DISTANCE) {
					employees.put(name, distance);
				}

			}

		} catch (IOException e) {
			throw new RuntimeException("file reading failed.");
		}
		
		return employees;
	}
	
	/**
	 *  Calculates the distance between the employee's house and the Storypark offices
	 * 
	 * @param longitude
	 * 			The longitudinal value of the employee's house location
	 * @param latitude
	 * 			The latitudinal value of the employee's house location
	 * 
	 * @return distance
	 * 			The distance between the employee's house and the Storypark offices
	 *          
	 */
	public double calculateDistance (double longitude, double latitude) {
		
		double angle = Math.acos((Math.sin(latitude) * Math.sin(OFFICE_LATITUDE)) 
				+ (Math.cos(latitude) * Math.cos(OFFICE_LATITUDE)
						* Math.cos(Math.abs(longitude - OFFICE_LONGITUDE))));
		double radians = Math.toRadians(angle);
		double distance = radians * EARTH_RADIUS;
		return distance;
		
	}
	
	/**
	 * Prints the names and distances of all employees within 2km of the Storypark offices
	 * 
	 * @param employees
	 * 			A map of names and distances of the employees within 2km of the Storypark offices
	 *          
	 */
	public void printEmployees(Map<String, Double> employees) {
		
		System.out.println("Employees within 2km of Storypark: \n");
		
		for (String employee : employees.keySet()) {
			System.out.println(employee + ": " + Math.floor(employees.get(employee)) /1000 + " km from Storypark");
		}
		
	}
	
	

}