package domains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2014/12/28.
 */
public class Pos {

    //返回主要的界面
    public String getShoppingList(ShoppingChart shoppingChart) {
        ArrayList<Item> items = shoppingChart.getItems();

        double XuebiPrice=0.0,ColaPrice=0.0,BearPrice=0.0;
        double Xuebidiscount=0.0,ColaDiscount=0.0,BearDiscount=0.0;
        int XueBiNumber=0,ColaNumber=0,BearNumber=0;
        String Colaunit="",BearUnit="",XuebiUnit="";
        Map<String,Good> goodMapping =new HashMap<String, Good>();

       for(int i=0;i<items.size();i++){
           Item item=items.get(i);
           if(goodMapping.containsKey(item.getName())==false){
               goodMapping.put(item.getName(),new Good(item.getPrice(),item.getDiscount(),item.getUnit(),item.getPrice()));
           }
            else
           {
               goodMapping.get(item.getName()).statistics(item.getPrice(),item.getDiscount());
           }
       }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("***商店购物清单***\n");



        double ColaTotal=ColaNumber*ColaPrice;
        double BearTotal=BearNumber*BearPrice;
        double XuebiTotal=XueBiNumber*XuebiPrice;
        double StartTotal=ColaTotal+BearTotal+XuebiTotal;
        double total=0,save=0;
        Iterator iterator=goodMapping.entrySet().iterator();

        while(iterator.hasNext())
        {
            Map.Entry entry=(Map.Entry) iterator.next();
            stringBuilder.append("名称：").append(entry.getKey().toString());
            Good good=(Good)entry.getValue();
            stringBuilder.append(good.toString());
            total=total+good.getPrice();
            save=save+good.getSave();
        }

           /* if(ColaNumber!=0){
                stringBuilder.append("名称：").append("可口可乐").append("，")
                        .append("数量：").append(ColaNumber).append(Colaunit).append("，")
                        .append("单价：").append(String.format("%.2f", ColaPrice)).append("(元)").append("，")
                        .append("小计：").append(String.format("%.2f", ColaTotal*ColaDiscount)).append("(元)").append("\n");
            }
             if(BearNumber!=0){
                 stringBuilder.append("名称：").append("电池").append("，")
                         .append("数量：").append(BearNumber).append(BearUnit).append("，")
                         .append("单价：").append(String.format("%.2f", BearPrice)).append("(元)").append("，")
                         .append("小计：").append(String.format("%.2f", BearTotal*BearDiscount)).append("(元)").append("\n");
            }
            if(XueBiNumber!=0){
                stringBuilder.append("名称：").append("雪碧").append("，")
                        .append("数量：").append(XueBiNumber).append(XuebiUnit).append("，")
                        .append("单价：").append(String.format("%.2f", XuebiPrice)).append("(元)").append("，")
                        .append("小计：").append(String.format("%.2f",XuebiTotal*Xuebidiscount)).append("(元)").append("\n");
            }*/

               stringBuilder .append("----------------------\n")
                .append("总计：").append(String.format("%.2f",total )).append("(元)").append("\n");
                if(save!=0)
                {
                    stringBuilder.append("节省：").append(String.format("%.2f",save)).append("(元)").append("\n");
                }
                 stringBuilder.append("**********************\n");


        return stringBuilder.toString();
    }
}
