package main;

import Utility.CouchBaseDao;
import Utility.GoodAndUserDao;
import Utility.ManageDao;
import domains.Item;
import domains.Pos;
import domains.ShoppingChart;

import static org.hamcrest.CoreMatchers.is;


/**
 * Created by KEN on 2014/12/30.
 */
public class POSmain {
    public static void main(String[] args) {

        // given
        String GoodPath="F:no5\\good.json";
        String List="F:no5\\list.json";
        String memberfile="F:no5\\Member.json";
         GoodAndUserDao gooduserdao = new GoodAndUserDao(GoodPath,List,memberfile);
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.setItems(gooduserdao.getData());

        //需求三四的文本格式
        //索引文件和列表文件存放的位置
       //String GoodPath="F:\\good1.json";
       //String List="F:\\list.json";
        //ManageDao mainDao = new ManageDao(GoodPath,List);
        //ShoppingChart shoppingChart = new ShoppingChart();
        //shoppingChart.setItems(mainDao.getData());

        //测试需求一二
        // String GoodPath="F:\\good.json";
      //  ManageDao mainDao=new ManageDao(GoodPath);
       // ShoppingChart shoppingChart = new ShoppingChart();
        //shoppingChart.setItems(mainDao.getData());


        //链接数据库测试，需求三四
        // CouchBaseDao couchdao=new CouchBaseDao();
        //couchbaseServer
        //shoppingChart.setItems(couchdao.getData());


        // when
        Pos pos = new Pos();
        String ShoppingList = pos.getShoppingList(shoppingChart);

        System.out.println(ShoppingList);

    }

}
