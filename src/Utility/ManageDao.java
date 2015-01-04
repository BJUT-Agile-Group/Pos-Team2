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
public class ManageDao implements DataBase{

    ArrayList<String> indexList = new ArrayList<String>();

    String GoodPath;
    String List;
    public ManageDao(){

    }
    public ManageDao(String path){
        GoodPath=path;
    }
    public ManageDao(String path,String URL){
        GoodPath=path;
        List=URL;
    }
    public ArrayList<Item> getData() {

        //从JSon文件读取数据
        StringBuffer stringBuffer = new StringBuffer();

       // String GoodPath = "F:\\good1.json";

        //输出数据流stringBuffer
        stringBuffer = getDataStream(GoodPath);
        stringBuffer.deleteCharAt(0);

        if (stringBuffer.charAt(0) == '[') {
            return getDataFromObject(stringBuffer);
        } else {

            return getDataFromArray(stringBuffer);
        }
    };

    public void setIndexList(String List) {
        //获取索引文件
        // String List="F:\\list.json";
        StringBuffer  stringIndex=getDataStream(List);
        //stringIndex.deleteCharAt(0);

        JSONArray jsonArr=JSONArray.fromObject(stringIndex.toString());

        for(int i=0;i<jsonArr.size();i++){
            indexList.add(i,jsonArr.getString(i));
        }
    }

    //获取json对象从json数组里面
    public ArrayList<Item> getDataFromArray(StringBuffer stringBuffer){
        ArrayList<Item> shoocar=new ArrayList<Item>();

        setIndexList(List);

        //通过索引文件找到项
        JSONObject jsonobj=JSONObject.fromObject(stringBuffer.toString());
        for(int i=0;i<indexList.size();i++) {
            //判断能否通过索引找到商品，如果能够找到，则输入，否则输出错误
            if(jsonobj.containsKey(indexList.get(i))) {
                JSONObject jsonarr = jsonobj.getJSONObject(indexList.get(i));
                shoocar.add(JSonParse(jsonarr,i));

            }

        }
        return shoocar;
    }
public Item JSonParse(JSONObject jsonarr,int i){

    double discount;
    Item item=null;
    String name = jsonarr.getString("name");
    String unit = jsonarr.getString("unit");
    double price = jsonarr.getDouble("price");
    //是否存在打折情况
    if (jsonarr.containsKey("discount")) {
        discount = jsonarr.getDouble("discount");


        if(jsonarr.containsKey("promotion")){
            boolean promotion=jsonarr.getBoolean("promotion");
            item=new Item(indexList.get(i),name,unit,price,discount,promotion);
        }else{
            item = new Item(indexList.get(i), name, unit, price, discount);
        }
    } else {
        //如果包括键promotion，则进行买二赠一的活动
        if(jsonarr.containsKey("promotion")){
            boolean promotion=jsonarr.getBoolean("promotion");
            item=new Item(indexList.get(i),name,unit,price,promotion);
        }
        else {
            item = new Item(indexList.get(i), name, unit, price);
        }
    }

    return item;
}

//
//   json解析数据到数组里面
//
    public  ArrayList<Item> getDataFromObject(StringBuffer stringBuffer){
        ArrayList<Item> shoocar=new ArrayList<Item>();
        Item item=null;
        double discount;
        JSONArray jsonArr=JSONArray.fromObject(stringBuffer.toString());
        //把条形码信息存到索引数组里面
        for(int i=0;i<jsonArr.size();i++){
            String barcode=jsonArr.getJSONObject(i).getString("barcode");
            indexList.add(i,barcode);
        }
        for(int i=0;i<jsonArr.size();i++){
           JSONObject jsonarr=jsonArr.getJSONObject(i);

            shoocar.add(JSonParse(jsonarr,i));
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
