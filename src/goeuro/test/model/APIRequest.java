/**
 * 
 */
package goeuro.test.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import goeuro.test.main.CLParser;

/**
 * @author lenovo
 *
 */
@SuppressWarnings({ "deprecation", "unused" })
public class APIRequest {
	private String request = "http://api.goeuro.com/api/v2/position/suggest/en/";
	private String city = "";
	private final Logger logger = Logger.getLogger(APIRequest.class.getName());
	
	public static final String ID = "_id";
	public static final String NAME = "name";
	public static final String TYPE = "type";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	private static final String GEOPOSITION = "geo_position";
	
	public APIRequest(String cityName) {
		this.city = cityName;
	}
	
	
	
	@SuppressWarnings({ "resource" })
	public GEObject[] submitRequest() {
		//Build the request to use the API
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(this.request + this.city);
		getRequest.addHeader("accept", "application/json");

		HttpResponse response;
		try {
			//Execute the request
			response = httpClient.execute(getRequest);
			if (response.getStatusLine().getStatusCode() != 200) {
				logger.severe("Failed with HTTP Code: " + response.getStatusLine().getStatusCode());
				System.exit(-1);
			}
			else {
				//Parse the response as JSONArray
				String jsonResponse = EntityUtils.toString(response.getEntity());
				httpClient.getConnectionManager().shutdown();
				JSONArray array = new JSONArray(jsonResponse);
				if(array.length() == 0) {
					logger.severe("We couldn't find any matches for CITY_NAME = \"" + this.city + "\"");
					System.exit(-1);
				}
				else {
					//Fill the array to be written to CSV
					GEObject[] results = new GEObject[array.length()];
					GEObjectBuilder builder = new GEObjectBuilder();
					for(int i = 0; i < array.length(); i++) {
						JSONObject object = array.getJSONObject(i);
						builder.buildId(object.getString(ID));
						builder.buildName(object.getString(NAME));
						builder.buildType(object.getString(TYPE));
						builder.buildLatitude(object.getJSONObject(GEOPOSITION).getString(LATITUDE));
						builder.buildLongitude(object.getJSONObject(GEOPOSITION).getString(LONGITUDE));
						results[i] = builder.buildObject();
						builder.eraseData();
					}
					return results;
				}
			}
		} catch(IOException e) {
			// TODO Auto-generated catch block
			logger.severe("Problem with the request : \"" + this.request + this.city + "\"");
			System.exit(-1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			logger.severe("Couldn't parse the response of the request as JSONObjects.");
			System.exit(-1);
		}
		return null;
	}

}
