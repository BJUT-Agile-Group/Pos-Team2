package Utility;

import com.couchbase.client.CouchbaseClient;
import domains.Item;
import domains.Person;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.spy.memcached.CASValue;
import net.spy.memcached.internal.OperationFuture;


import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by KEN on 2015/1/4.
 */
public class CouchBaseDao extends ManageDao {

    //本地积分信息缓存地址
    private static final String SCOREFile="F:no5\\score.json";

    Person person=new Person();
    public CouchBaseDao() {

    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        Object good=client.get("good.json");
        Object list=client.get("list.json");
        Object member=client.get("Member.json");

        //从数据库中获取列表文件和索引文件，然后进行解析
        client.shutdown(3, TimeUnit.SECONDS);

        //获取index和person信息
        JSONObject listjson=JSONObject.fromObject(list);
        //获取user信息
        if(listjson.containsKey("user")){
            String item=listjson.getString("user");
            JSONObject memberjson=JSONObject.fromObject(member);
            JSONObject jsonObject=memberjson.getJSONObject(item);
            person.setName(jsonObject.getString("name"));
            person.setVip(jsonObject.getBoolean("isVip"));

        }
        JSONArray josnarr=listjson.getJSONArray("items");

        //获取列表
        JSONObject goodjson=JSONObject.fromObject(good);
        for(int i=0;i<josnarr.size();i++){

            getIndexList().add(i,josnarr.get(i).toString());
            JSONObject jsonarrlit=goodjson.getJSONObject(getIndexList().get(i));
            shoocar.add(JSonParse(jsonarrlit,i));

        }

           return shoocar;


    }

    public void UpdateScore(){
        //建立客户端
        List<URI> uris=new LinkedList<URI>();
        uris.add(URI.create(DataBase.Http));
        CouchbaseClient client=null;
        try {
            client=new CouchbaseClient(uris,"default","");
        } catch (IOException e) {

            System.err.println("IOException connetion to couchbase:"+e.getMessage() );
            System.exit(1);
        }
        //获取任务用户存储信息
        Object scorejson=client.get("score.json");
        JSONObject score=JSONObject.fromObject(scorejson);

           score.remove(person.getName());

           score.put(person.getName(),person.getScore());

          OperationFuture<Boolean> setop= client.replace("score.json", score.toString());
        //必须加上返回值不然会失败.
         try {
             if (setop.get().booleanValue()) {
                 System.out.println("Update success");
             } else {
                 System.err.println("set failed!");
             }
         }catch (InterruptedException e){
             System.err.println("InterruptionException "+e.getMessage());
         }catch(ExecutionException e){

             System.err.println("ExecutionExxception while update"+e.getMessage());
         }



            client.shutdown();


    }



}
