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
        //默认不打折
       Item item=null;
        double discount;

        try {
            InputStreamReader insReader = new InputStreamReader(
                    new FileInputStream("F:\\good1.json"), "UTF-8");
            BufferedReader br = new BufferedReader(insReader);
            while( (line = br.readLine())!= null ){
                stringBuffer.append(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将Json文件数据形成JSONObject对象

        ArrayList<Item> shoocar=new ArrayList<Item>();
      //  JSONObject jsonObj=JSONObject.fromObject(stringBuffer.toString());
        stringBuffer.deleteCharAt(0);
        JSONArray jsonArr=JSONArray.fromObject(stringBuffer.toString());


        for(int i=0;i<jsonArr.size();i++){
            String barcode=jsonArr.getJSONObject(i).getString("barcode");
            String name=jsonArr.getJSONObject(i).getString("name");
            String unit=jsonArr.getJSONObject(i).getString("unit");
            double price=jsonArr.getJSONObject(i).getDouble("price");
            //是否存在打折情况
            if(jsonArr.getJSONObject(i).containsKey("discount")){
                discount=jsonArr.getJSONObject(i).getDouble("discount");
                if(discount>0&&discount<1) {
                    item = new Item(barcode, name, unit, price, discount);
                }
                else{
                    System.out.println("打折信息错误！");
                    System.exit(0);

                }
            }
           else {
                item = new Item(barcode, name, unit, price);
            }
            shoocar.add(item);
        }
        return shoocar;
    };

}
