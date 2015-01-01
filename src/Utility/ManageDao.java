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

    ArrayList<String> indexList = new ArrayList<String>();

    public ArrayList<Item> getData() {
        //从JSon文件读取数据
        StringBuffer stringBuffer = new StringBuffer();

        String GoodPath = "F:\\good1.json";

        //输出数据流stringBuffer
        stringBuffer = getDataStream(GoodPath);
        stringBuffer.deleteCharAt(0);

        if (stringBuffer.charAt(0) == '[') {
            return getDataFromObject(stringBuffer);
        } else {

            return getDataFromArray(stringBuffer);
        }
    };
    //获取json对象从json数组里面
    public ArrayList<Item> getDataFromArray(StringBuffer stringBuffer){
        ArrayList<Item> shoocar=new ArrayList<Item>();
        Item item=null;
        double discount;
        //获取索引文件
        String List="F:\\list.json";
        StringBuffer  stringIndex=getDataStream(List);
        //stringIndex.deleteCharAt(0);

        JSONArray jsonArr=JSONArray.fromObject(stringIndex.toString());

        for(int i=0;i<jsonArr.size();i++){
            indexList.add(i,jsonArr.getString(i));
        }


        //通过索引文件找到项
        JSONObject jsonobj=JSONObject.fromObject(stringBuffer.toString());
        for(int i=0;i<indexList.size();i++) {
            JSONObject jsonarr = jsonobj.getJSONObject(indexList.get(i));
            String name=jsonarr.getString("name");
            String unit=jsonarr.getString("unit");
            double price=jsonarr.getDouble("price");
            //是否存在打折情况
            if(jsonarr.containsKey("discount")){
                discount=jsonarr.getDouble("discount");
                if(discount>0&&discount<1) {
                    item = new Item(indexList.get(i), name, unit, price, discount);
                }
                else{
                    System.out.println("打折信息错误！");
                    System.exit(0);

                }
            }
            else {
                item = new Item(indexList.get(i), name, unit, price);
            }
            shoocar.add(item);

        }
        return shoocar;
    }

//
//   json解析数据到数组里面
//
    public  ArrayList<Item> getDataFromObject(StringBuffer stringBuffer){
        ArrayList<Item> shoocar=new ArrayList<Item>();
        Item item=null;
        double discount;
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
    }

///*
// 从文件中获取数据到数据流中
//
// /
    public StringBuffer getDataStream(String file){
        String line = null ;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            InputStreamReader insReader = new InputStreamReader(
                    new FileInputStream(file), "UTF-8");
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
        return stringBuffer;
    };
}
