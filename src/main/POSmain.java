package main;

import Utility.CouchBaseDao;
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
        //索引文件和列表文件存放的位置
       String GoodPath="F:\\good1.json";
       String List="F:\\list.json";
        // String GoodPath="F:\\good.json";
        ShoppingChart shoppingChart = new ShoppingChart();

        ManageDao mainDao = new ManageDao(GoodPath,List);
        CouchBaseDao couchdao=new CouchBaseDao();
      //  ManageDao mainDao=new ManageDao(GoodPath);
        //shoppingChart.setItems(mainDao.getData());
        shoppingChart.setItems(couchdao.getData());

        // when
        Pos pos = new Pos();
        String ShoppingList = pos.getShoppingList(shoppingChart);

        System.out.println(ShoppingList);

    }

}
