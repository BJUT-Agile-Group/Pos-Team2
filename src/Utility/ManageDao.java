package Utility;
import domains.Item;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * Created by KEN on 2014/12/30.
 */
public class ManageDao {
    public ArrayList<Item> getData(){
        //从JSon文件读取数据
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
        //将Json文件数据形成JSONObject对象

        ArrayList<Item> shoocar=new ArrayList<Item>();
        JSONObject jsonObj=JSONObject.fromObject(stringBuffer.toString());
        JSONArray jsonArr=jsonObj.getJSONArray("good");


        for(int i=0;i<jsonArr.size();i++){
            String barcode=jsonArr.getJSONObject(i).getString("barcode");
            String name=jsonArr.getJSONObject(i).getString("name");
            String unit=jsonArr.getJSONObject(i).getString("unit");
            double price=jsonArr.getJSONObject(i).getDouble("price");
            double discount=jsonArr.getJSONObject(i).getDouble("discount");
            Item item=new Item(barcode,name,unit,price,discount);
            shoocar.add(item);
        }


        return shoocar;
    };

}
