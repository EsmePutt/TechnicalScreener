
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;


/**
 *  A suite of tests designed to test whether the Flattener class returns a
 *  flattened array under a variety of valid and invalid conditions
 * 
 * @author Esme Putt
 *          
 */
public class FlattenerTests {
	
	//=============== VALID TESTS ===============

	/**
	 *  Tests an array with a single nested array within it
	 */
	@Test
	public void testNested() throws Exception {

		Object[] arbitraryArray = {1,1,1, new Object[]{1,1,1}};
		Object[] expectedArray = {1,1,1,1,1,1};

		Flattener newFlattener = new Flattener();
		arbitraryArray = newFlattener.flattener(arbitraryArray);

		Assert.assertArrayEquals(arbitraryArray, expectedArray);
	}
	
	/**
	 *  Tests an array with a single nested array within it
	 */
	@Test
	public void testNested_2() throws Exception {

		Object[] arbitraryArray = {1,2,3, new Object[]{4,5,6}};
		Object[] expectedArray = {1,2,3,4,5,6};

		Flattener newFlattener = new Flattener();
		arbitraryArray = newFlattener.flattener(arbitraryArray);

		Assert.assertArrayEquals(arbitraryArray, expectedArray);
	}

	/**
	 *  Tests that array can flatten integers following a nested array
	 */
	@Test
	public void testNestedInMiddle() throws Exception {

		Object[] arbitraryArray = {1,1,1, new Object[]{2,2,2}, 1, 1};
		Object[] expectedArray = {1,1,1,2,2,2,1,1};

		Flattener newFlattener = new Flattener();
		arbitraryArray = newFlattener.flattener(arbitraryArray);

		Assert.assertArrayEquals(arbitraryArray, expectedArray);
	}

	/**
	 *  Tests an array with more than one nested array
	 */
	@Test
	public void testTwoNested() throws Exception {

		Object[] arbitraryArray = {new Object[] {1,1,1}, new Object[]{2,2,2}};
		Object[] expectedArray = {1,1,1,2,2,2};

		Flattener newFlattener = new Flattener();
		arbitraryArray = newFlattener.flattener(arbitraryArray);

		Assert.assertArrayEquals(arbitraryArray, expectedArray);
	}

	/**
	 *  Tests an array with a nesting depth of 2
	 */
	@Test
	public void testDoubleNested() throws Exception {

		Object[] arbitraryArray = {new Object[] {new Object[] {1,1,1}}, new Object[]{2,2,2}};
		Object[] expectedArray = {1,1,1,2,2,2};

		Flattener newFlattener = new Flattener();
		arbitraryArray = newFlattener.flattener(arbitraryArray);

		Assert.assertArrayEquals(arbitraryArray, expectedArray);
	}

	/**
	 *  Tests an array with a nesting depth of 3
	 */
	@Test
	public void testTripleNested() throws Exception {

		Object[] arbitraryArray = {new Object[] {new Object[] {new Object[] {1,1,1}}}, new Object[]{1,1,1}};
		Object[] expectedArray = {1,1,1,1,1,1};

		Flattener newFlattener = new Flattener();
		arbitraryArray = newFlattener.flattener(arbitraryArray);

		Assert.assertArrayEquals(arbitraryArray, expectedArray);
	}
	
	//=============== INVALID TESTS ===============

	/**
	 *  Tests whether error is handled when an empty array is entered
	 */
	@Test
	public void testEmptyArray() throws Exception {
		boolean thrown = false;
		Object[] arbitraryArray = {};
		try {
			Flattener newFlattener = new Flattener();
			arbitraryArray = newFlattener.flattener(arbitraryArray);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	/**
	 *   Tests whether error is handled when an array containing non-Integers is entered
	 */

	@Test
	public void testInvalidArray() throws Exception {
		boolean thrown = false;
		Object[] arbitraryArray = {'a', 'b'};
		try {
			Flattener newFlattener = new Flattener();
			arbitraryArray = newFlattener.flattener(arbitraryArray);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
	
	/**
	 *   Tests whether error is handled when a null array is entered
	 */

	@Test
	public void testNullArray() throws Exception {
		boolean thrown = false;
		Object[] arbitraryArray = null;
		try {
			Flattener newFlattener = new Flattener();
			arbitraryArray = newFlattener.flattener(arbitraryArray);
		} catch (Exception e) {
			thrown = true;
		}
		assertTrue(thrown);
	}
}





