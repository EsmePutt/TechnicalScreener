import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.junit.Test;


/**
 *  A suite of tests designed to test that the Parser program can accurately calculate and store
 *  a Map of employees within 2km of a given location. Also tests that empty and incorrectly formatted
 *  files are handled properly.
 * 
 * @author Esme Putt
 *          
 */
public class ParserTests {
	
	/**
	 *  Tests that the Storypark JSON file is parsed correctly
	 */
	@Test
	public void testValid() throws Exception {

		Parser newParser = new Parser (-41.2920728, 174.7748162, "StoryParkEmployees.json");
		Map<String, Double> employees = newParser.parseEmployees();
		newParser.printEmployees(employees);
		assertTrue(employees.keySet().size() == 7);
				
	}
	
	/**
	 *  Tests that no employees are returned when the Storypark offices are moved to Auckland
	 */
	@Test
	public void testDifferentLocation() throws Exception {
		
		Parser newParser = new Parser (-36.843397, 174.766532, "StoryParkEmployees.json");
		Map<String, Double> employees = newParser.parseEmployees();
		
		assertTrue(employees.keySet().size() == 0);
				
	}
	
	/**
	 *  Tests that an empty file is handled appropriately
	 */
	@Test
	public void testEmptyFile() throws Exception {
		boolean thrown = false;
		try {
			Parser newParser = new Parser (-41.2920728, 174.7748162, "Empty.json");
			newParser.parseEmployees();
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
				
	}
	
	/**
	 *  Tests that a null file is handled appropriately
	 */
	@Test
	public void testNullFile() throws Exception {

		boolean thrown = false;
		try {
			Parser newParser = new Parser (-41.2920728, 174.7748162, null);
			newParser.parseEmployees();
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
				
	}
	
	/**
	 *  Tests that an invalid file is handled appropriately
	 */
	@Test
	public void testInvalidFile() throws Exception {

		boolean thrown = false;
		try {
			Parser newParser = new Parser (-41.2920728, 174.7748162, "InvalidFormat.json");
			newParser.parseEmployees();
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
				
	}
	
	
	
}