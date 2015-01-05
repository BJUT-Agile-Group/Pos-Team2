package test.couchbaseServerTest;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



import com.couchbase.client.CouchbaseClient;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<URI> uris=new LinkedList<URI>();
		uris.add(URI.create("http://127.0.0.1:8091"));
		CouchbaseClient client=null;
		try {
			client=new CouchbaseClient(uris,"default","");
		} catch (IOException e) {
			
			System.err.println("IOException connetion to couchbase:"+e.getMessage() );
			System.exit(1);
		}
		Object o=client.get("index.json");
		
		JSONObject jonobj=JSONObject.fromObject(o);
	    JSONArray josnarr=jonobj.getJSONArray("good");
		for(int i=0;i<josnarr.size();i++){
			System.out.println(josnarr.get(i).toString());
		}
		System.out.println(o);
		client.shutdown(3,TimeUnit.SECONDS);
		System.exit(0);
		

	}

}
