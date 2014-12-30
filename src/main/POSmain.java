package main;

import Utility.ManageDao;
import domains.Item;
import domains.Pos;
import domains.ShoppingChart;

import static org.hamcrest.CoreMatchers.is;


/**
 * Created by KEN on 2014/12/30.
 */
public class POSmain {
    public static  void main(String[] args){

        // given

       ShoppingChart shoppingChart = new ShoppingChart();

        ManageDao mainDao=new ManageDao();

        shoppingChart.setItems(mainDao.getData());

         //   shoppingChart.add(new Item("ITEM000001","雪碧","瓶",3.00,0.8));
           // shoppingChart.add(new Item("ITEM000004","电池","个",2.00,0.7));





        // when
        Pos pos = new Pos();
        String ShoppingList = pos.getShoppingList(shoppingChart);

        System.out.println(ShoppingList);

    }

}
