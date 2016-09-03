/**
 * 
 */
package goeuro.test.main;

import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * @author lenovo
 *
 */
@SuppressWarnings("deprecation")
public class CLParser {
	private final Logger logger = Logger.getLogger(CLParser.class.getName());
	private static CLParser clParser = null;
	private Options options = new Options();
	private String cityName = "";
	private String outputFile = "";
	
	//Strings used in command line
	private static final String CITY = "city";
	private static final String OUTPUT = "o";
	
	
	public String getCityName() {
		return cityName;
	}
	public String getOutputFile() {
		return outputFile;
	}

	private CLParser() {
		options.addOption(CITY, true, "City name");
		options.addOption(OUTPUT, true, "Output file path");
	}
	
	public static CLParser getInstance() {
		if(clParser == null)
			clParser = new CLParser();
		return clParser;
	}
	
	private boolean validateOptions(CommandLine cmd) {
		boolean isValid = true;
		if(!cmd.hasOption(CITY))
			isValid = false;
		return isValid;
	}
	
	
	public boolean parseCmd(String[] args) {
		boolean parsedOk = true;
		//Parse the command line
		CommandLineParser cliParser = new GnuParser();
		
		try {
			CommandLine cmd = cliParser.parse(options, args);
			if(!validateOptions(cmd)) {
				logger.severe("Usage:  java -jar GoEuroTest.jar -city \"<CITY_NAME>\" [-o <outputFilePath>]\n"
						+ "Example: java -jar GoEuroTest.jar -city \"Berlin\"\n"
						+ "Example: java -jar GoEuroTest.jar  -city \"Berlin\" -o \"GoEuroTest.csv\"");
				parsedOk = false;
			}
			else {
				this.cityName = cmd.getOptionValue(CITY);
				if(this.cityName.equals("")) {
					logger.severe("<CITY_NAME> is Empty. Please follow the usage example.\n"
							+ "Example: java -jar GoEuroTest.jar -city \"Berlin\"");
					parsedOk = false;
				}
				else {
					if(cmd.hasOption(OUTPUT)) {
						this.outputFile = cmd.getOptionValue(OUTPUT);
					}
					else {
						this.outputFile = "GoEuroTest.csv";
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.severe("Error parsing command line options.");
			parsedOk = false;
		}
		return parsedOk;
	}
	

}
