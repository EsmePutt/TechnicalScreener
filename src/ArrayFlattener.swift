import XCTest

//
//A simple program designed to flatten an arbitrarily nested array
//
//
//Author: Esme Putt
//

class Flattener {

    var flattenedArray: [Any] = []

    //Set up the errors for the cases where an invalid array is passed
    enum FlattenerErrors : Error {
        case incorrectFormat
        case emptyArray
    }

    func flatten( arbitraryArray: [Any]) throws ->[Any] {

        //Ensure the array isn't empty
        if arbitraryArray.count < 1 {
            throw FlattenerErrors.emptyArray
        }

        //Access each element in the array
        for nextElement: Any in arbitraryArray {
            try recurseInside(nextElement: nextElement)
        }

        return flattenedArray

    }

    //Helper method to access nested values
    func recurseInside(nextElement : Any) throws  {

        //If the inside of a nested array is reached, add the integer to the array
        if nextElement is Int {
            flattenedArray.append(nextElement)
        }
        else if let array = nextElement as? [Any] {
            //Recursively call each element of the nested array
            for insideElement: Any in array {
                try recurseInside(nextElement: insideElement)
            }

        }
        //If the array contains an element that isn't an Integer or a nested array
        else {
            throw FlattenerErrors.incorrectFormat
        }

    }

}


//A suite of tests designed to test the Flattener program under a number of valid and invalid conditions
class FlattenerTests: XCTestCase {


    //A helper method allowing two arrays to be compared
    func arrayEquals(arrayOne: [Any], arrayTwo: [Any]) -> Bool {

        if (arrayOne.count != arrayTwo.count) {
            return false
        }


        for i in 0 ..< arrayOne.count {
            if arrayOne[i] as? Int != arrayTwo[i] as? Int {
                return false
            }
        }

        return true
    }


    //============== Valid tests ==============

    func testValid_1() throws {
        let arbitraryArray: [Any] = [1,2,3,4]
        let targetArray: [Any] = [1,2,3,4]
        let flattener: Flattener = Flattener()
        let flattenedArray = try flattener.flatten(arbitraryArray: arbitraryArray)
        XCTAssertTrue(arrayEquals(arrayOne: flattenedArray, arrayTwo: targetArray))
    }


    func testValid_2() throws {
        let arbitraryArray: [Any] = [[1],[2],[3],[4]]
        let targetArray: [Any] = [1,2,3,4]
        let flattener: Flattener = Flattener()
        let flattenedArray = try flattener.flatten(arbitraryArray: arbitraryArray)
        XCTAssertTrue(arrayEquals(arrayOne: flattenedArray, arrayTwo: targetArray))
    }

    func testValid_3() throws {
        let arbitraryArray: [Any] = [[1],2,[3],4]
        let targetArray: [Any] = [1,2,3,4]
        let flattener: Flattener = Flattener()
        let flattenedArray = try flattener.flatten(arbitraryArray: arbitraryArray)
        XCTAssertTrue(arrayEquals(arrayOne: flattenedArray, arrayTwo: targetArray))
    }

    func testNested_1() throws {
        let arbitraryArray: [Any] = [[1,[2]],[3],[4]]
        let targetArray: [Any] = [1,2,3,4]
        let flattener: Flattener = Flattener()
        let flattenedArray = try flattener.flatten(arbitraryArray: arbitraryArray)
        XCTAssertTrue(arrayEquals(arrayOne: flattenedArray, arrayTwo: targetArray))
    }

    func testNested_2() throws {
        let arbitraryArray: [Any] = [[1,[2]],[[[3]]],[4]]
        let targetArray: [Any] = [1,2,3,4]
        let flattener: Flattener = Flattener()
        let flattenedArray = try flattener.flatten(arbitraryArray: arbitraryArray)
        XCTAssertTrue(arrayEquals(arrayOne: flattenedArray, arrayTwo: targetArray))
    }

    func testNested_3() throws {
        let arbitraryArray: [Any] = [1,[2,[3,[4]]]]
        let targetArray: [Any] = [1,2,3,4]
        let flattener: Flattener = Flattener()
        let flattenedArray = try flattener.flatten(arbitraryArray: arbitraryArray)
        XCTAssertTrue(arrayEquals(arrayOne: flattenedArray, arrayTwo: targetArray))
    }

    //============== Invalid tests ==============

    func testEmptyArray() throws {
        var thrown: Bool = false
        do {
            let arbitraryArray: [Any] = []
            let flattener: Flattener = Flattener()
            try flattener.flatten(arbitraryArray: arbitraryArray)
        } catch {
            thrown = true
        }
        XCTAssertTrue(thrown)
    }

    func testInvalidArray() throws {
        var thrown: Bool = false
        do {
            let arbitraryArray: [Any] = ["a","b","c"]
            let flattener: Flattener = Flattener()
            try flattener.flatten(arbitraryArray: arbitraryArray)
        } catch {
            thrown = true
        }
        XCTAssertTrue(thrown)
    }


}


var test = FlattenerTests.defaultTestSuite
test.run()
