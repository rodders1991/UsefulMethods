/*
 * Simple example that gets the lngLat based on a location query
 * 
 */

package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONRequest {

	
	
	public static void main(String[] args) throws IOException {
		
		// args[0] should be your api Key
		float[] res = latLng("Ruislip", args[0]); 
		
		System.out.println("Lat: " + res[0]);
		System.out.println("Lng: " + res[1]);
		
		
		
	}
	
	public static float[] latLng(String name, String APIKey) throws IOException
	{
		
		float[] result = new float[2];
		
		String inName = name.replace(" ", "+");
		
		URL url = null;
		try
		{
		url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+inName+"&key="+APIKey);
		
		} catch(MalformedURLException e)
		{
			System.out.println("URL ERROR: " + e);
			return result;
		}
		
		   InputStream is = url.openStream();
		   
		    try {
	
		    BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      
		      JSONArray loc = (JSONArray) json.get("results"); // If it's surrounded by [] cast it to JSONArray
		      
		      
		      if(json.get("status").equals("OK")) { 
		    	  
		    	   JSONObject locOb = (JSONObject)loc.get(0);
				      locOb = (JSONObject) locOb.get("geometry"); // If it's surrounded by {} cast it to JSONObject
				      locOb = (JSONObject) locOb.get("location");
				      
				      Object lat = locOb.get("lat"); //Once the final value is obtained convert it use it as an object
				      Object lng = locOb.get("lng");
				      
				      
				     result[0] = Float.parseFloat(lat.toString());
				     result[1] = Float.parseFloat(lng.toString());
				      
				      return result;
				      
		    	  }
		      else{
		      
		    result[0] = 999f;
		    return result; 
		    
		      }
		    } finally {
		      is.close();
	
		    }
		
	}
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
}
