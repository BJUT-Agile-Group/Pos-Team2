package Utility;

import com.couchbase.client.CouchbaseClient;
import domains.Item;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by KEN on 2015/1/4.
 */
public class CouchBaseDao extends ManageDao {

    public CouchBaseDao() {

    }

    @Override
    public ArrayList<Item> getData() {
        ArrayList<Item> shoocar=new ArrayList<Item>();
        StringBuffer stringBuffer=new StringBuffer();
        List<URI> uris=new LinkedList<URI>();
        uris.add(URI.create(DataBase.Http));
        CouchbaseClient client=null;
        try {
            client=new CouchbaseClient(uris,"default","");
        } catch (IOException e) {

            System.err.println("IOException connetion to couchbase:"+e.getMessage() );
            System.exit(1);
        }
        Object o=client.get("good.json");
        Object p=client.get("index.json");
//从数据库中获取列表文件和索引文件，然后进行解析
        client.shutdown(3, TimeUnit.SECONDS);

        JSONObject jonobj=JSONObject.fromObject(p);
        JSONArray josnarr=jonobj.getJSONArray("good");

        JSONObject goodjson=JSONObject.fromObject(o);


        for(int i=0;i<josnarr.size();i++){

            getIndexList().add(i,josnarr.get(i).toString());
            JSONObject jsonarrlit=goodjson.getJSONObject(getIndexList().get(i));
            shoocar.add(JSonParse(jsonarrlit,i));

        }
           return shoocar;


    }



    public static void main(String[] args){
        ArrayList<Item> arr=new ArrayList<Item>();
        CouchBaseDao dao=new CouchBaseDao();
        arr=dao.getData();

            System.out.println(arr.toString());

    }
}
