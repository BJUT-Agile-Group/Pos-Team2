package test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class Main {
	public  static void main(String[] args) throws ParseException{

		StringBuffer stringBuffer = new StringBuffer();
		String line = null ;
		try {
		BufferedReader br = new BufferedReader(new FileReader(new File("good.json")));
		while( (line = br.readLine())!= null ){
		stringBuffer.append(line);
		} 
		} catch (FileNotFoundException e) {
		       e.printStackTrace();
		} catch (IOException e) {
		e.printStackTrace();
		}

		//System.out.println(stringBuffer.toString());
		stringBuffer.deleteCharAt(0);
		List<Item> shoocar=new ArrayList<Item>();
		JSONObject jsonObj=JSONObject.fromObject(stringBuffer.toString());
		JSONArray jsonArr=jsonObj.getJSONArray("good");
		
		
		for(int i=0;i<jsonArr.size();i++){
			System.out.println(jsonArr.getJSONObject(i).toString());

			String barcode=jsonArr.getJSONObject(i).getString("barcode");
			String name=jsonArr.getJSONObject(i).getString("name");
			String unit=jsonArr.getJSONObject(i).getString("unit");
			double price=jsonArr.getJSONObject(i).getDouble("price");
			Item item=new Item(barcode,name,unit,price);
			shoocar.add(item);
		}
		
		for(int i=0;i<shoocar.size();i++){
			//System.out.println(shoocar.get(i).tostring());
		}
		
		}
		
	
}
