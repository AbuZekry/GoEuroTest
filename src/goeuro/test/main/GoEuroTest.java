/**
 * 
 */
package goeuro.test.main;

import goeuro.test.model.APIRequest;
import goeuro.test.model.CSVFileWriter;
import goeuro.test.model.GEObject;

/**
 * @author lenovo
 *
 */
public class GoEuroTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CLParser parser = CLParser.getInstance();
		if(parser.parseCmd(args)) {
			APIRequest request = new APIRequest(parser.getCityName());
			GEObject[] objects = request.submitRequest();
			if(objects != null) {
				CSVFileWriter csvFile = new CSVFileWriter();
				csvFile.writeFile(objects, parser.getOutputFile());
			}
		}

	}

}
