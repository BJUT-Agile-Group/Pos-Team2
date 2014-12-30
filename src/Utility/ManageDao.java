package Utility;
import domains.Item;
import org.json.simple.JSONArray;

import java.io.*;
import java.util.ArrayList;
/**
 * Created by KEN on 2014/12/30.
 */
public class ManageDao {
    public ArrayList<Item> getData(){
        ArrayList<Item> list=new ArrayList<Item>();

        StringBuffer stringBuffer=new StringBuffer();
        String line=null;
        try{
            BufferedReader br = new BufferedReader(new FileReader(new File("good.json")));
            while( (line = br.readLine())!= null ){
                stringBuffer.append(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String json=stringBuffer.toString();
        System.out.println(json);



        return list;
    };

}
