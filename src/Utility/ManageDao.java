package Utility;
import domains.Item;


import java.io.*;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * Created by KEN on 2014/12/30.
 */
public class ManageDao implements DataBase{

    private ArrayList<String> indexList = new ArrayList<String>();

    private String GoodPath;
    private String List;
    public ManageDao(){

    }
    public ManageDao(String path){
        GoodPath=path;
    }
    public ManageDao(String path,String URL){
        this.GoodPath=path;
        this.List=URL;
    }

    public ArrayList<String> getIndexList() {
        return indexList;
    }

    public void setIndexList(ArrayList<String> indexList) {
        this.indexList = indexList;
    }

    public String getGoodPath() {
        return GoodPath;
    }

    public String getList() {
        return List;
    }

    public ArrayList<Item> getData() {

        //从JSon文件读取数据
        StringBuffer stringBuffer = new StringBuffer();

        //输出数据流stringBuffer
        stringBuffer = getDataStream(getGoodPath());
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
            this.indexList.add(i,jsonArr.getString(i));
        }
    }

    //获取json对象从json数组里面
    public ArrayList<Item> getDataFromArray(StringBuffer stringBuffer){
        ArrayList<Item> shoocar=new ArrayList<Item>();

        setIndexList(getList());

        //通过索引文件找到项
        JSONObject jsonobj=JSONObject.fromObject(stringBuffer.toString());
        for(int i=0;i<getIndexList().size();i++) {
            //判断能否通过索引找到商品，如果能够找到，则输入，否则输出错误
            if(jsonobj.containsKey(getIndexList().get(i))) {
                JSONObject jsonarr = jsonobj.getJSONObject(getIndexList().get(i));
                shoocar.add(JSonParse(jsonarr,i));

            }

        }
        return shoocar;
    }
public Item JSonParse(JSONObject jsonarr,int i){

    double discount=1;
    boolean promotion=false;
    double vipDiscount=1;
    Item item=null;
    String name = jsonarr.getString("name");
    String unit = jsonarr.getString("unit");
    double price = jsonarr.getDouble("price");
    //是否存在打折情况
    if (jsonarr.containsKey("discount")) {
        discount = jsonarr.getDouble("discount");
    }
    if(jsonarr.containsKey("promotion")) {
        promotion = jsonarr.getBoolean("promotion");
    }
    if(jsonarr.containsKey("vipDiscount")){
        vipDiscount=jsonarr.getDouble("vipDiscount");
    }
    item=new Item(getIndexList().get(i),name,unit,price,discount,promotion,vipDiscount);
    //前面错误拦截
    item.valiate();
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
