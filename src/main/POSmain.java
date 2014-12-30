package main;

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


            shoppingChart.add(new Item("ITEM000001","雪碧","瓶",3.00));
            shoppingChart.add(new Item("ITEM000004","电池","个",2.00));



        shoppingChart.add(new Item("ITEM000001","雪碧","瓶",3.00));
        //shoppingChart.add(new Item("ITEM000001","雪碧","瓶",3.00));
        shoppingChart.add(new Item("ITEM000004","电池","个",2.00));

        // when
        Pos pos = new Pos();
        String ShoppingList = pos.getShoppingList(shoppingChart);

        System.out.println(ShoppingList);

    }

}
