/**
 * 
 */
package goeuro.test.model;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * @author lenovo
 *
 */
public class CSVFileWriter {
	private final Logger logger = Logger.getLogger(CSVFileWriter.class.getName());
	private static final String COMMA = ",";
	
	
	public void writeFile(GEObject[] objects, String outputFile) {
		Writer writer = null;
		try {
			//Creating output file to write csv format
		    writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), "utf-8"));
		    writer.write(APIRequest.ID + COMMA + APIRequest.NAME + COMMA + APIRequest.TYPE + COMMA + APIRequest.LATITUDE + COMMA + APIRequest.LONGITUDE);
		    for(int i = 0; i < objects.length; i++) {
		    	GEObject object = objects[i];
		    	writer.write("\n");
		    	writer.write(object.getId() + COMMA + object.getName() + COMMA + object.getType() + COMMA + object.getLatitude() + COMMA + object.getLongitude());
		    }
		    System.out.println("Finished generating file \"" + outputFile + "\"");
		} catch (IOException ex) {
			//Something went wrong with generating and writing to output file
			logger.severe("Error writing to output file \"" + outputFile + "\"");
			
		} finally {
		   try {
			   writer.close();
		   } catch (Exception ex) {
			   /*ignore*/
		   }
		}
	}
}
