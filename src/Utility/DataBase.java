package Utility;

import domains.Item;
import net.sf.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by KEN on 2015/1/4.
 */
public interface DataBase {

    public static final String KEY="index.json";
    public static final String Value="good.json";
    public static final String Http="http://127.0.0.1:8091";
   //获取数据源
    public Object  getData();
    //源里面获取对象，得到数组
    public Object getDataFromObject(StringBuffer stringBuffer);
    //解析JSON数组对象
    public Object JSonParse(JSONObject jsonarr,int i);
    public StringBuffer getDataStream(String file);
}
