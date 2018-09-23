import java.util.ArrayList;
import java.util.List;


/**
 * A simple program designed to take any arbitrarily nested array of Integers, and
 * flatten it into a single array
 * 
 * @author Esme Putt
 */
public class Flattener {

	List<Integer> flattenedArrayList = new ArrayList<Integer>();

	/**
	 * Controls main program flow by calling the helper method on each array element, and
	 * returning the final flattened array
	 * 
	 * @param arbitraryArray
	 *            Any array of arbitrarily nested Integers
	 * @return flattenedArray
	 * 			  A flattened version of arbitraryArry
	 */
	public Object[] flattener (Object[] arbitraryArray) throws Exception {

		if (arbitraryArray.length == 0) {
			throw new Exception("Array is empty");
		}
		
		for (Object nextElement : arbitraryArray) {
			this.flatten(nextElement);
		}

		//Converts the ArrayList back into a final flattened array
		Integer[] flattenedArray = new Integer[flattenedArrayList.size()];
		for (int i = 0; i < flattenedArrayList.size(); i++ ) {
			flattenedArray[i] = flattenedArrayList.get(i);
		}

		return flattenedArray;

	}

	/**
	 * A helper method which recursively adds each Integer object to the flattened array
	 * 
	 * @param nextElement
	 * 			The next element to be processed into the flattened array
	 */
	public void flatten(Object nextElement) throws Exception {

		if (nextElement instanceof Integer) {
			flattenedArrayList.add((Integer) nextElement);
		} else if (nextElement instanceof Object[]) {
			for (Object nextNested : (Object[]) nextElement) {
				this.flatten(nextNested);
			}
		} else {
			throw new Exception("Invalid array entry");
		}

	}


}