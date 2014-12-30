package domains;

import java.util.ArrayList;

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


       for(int i=0;i<items.size();i++){
           if("可口可乐".equals(items.get(i).getName())){
               ColaPrice=items.get(i).getPrice();
               Colaunit=items.get(i).getUnit();
               ColaDiscount=items.get(i).getDiscount();
               ColaNumber++;
           }
            else if("雪碧".equals(items.get(i).getName())){
               XuebiPrice=items.get(i).getPrice();
               XuebiUnit=items.get(i).getUnit();
               Xuebidiscount=items.get(i).getDiscount();
               XueBiNumber++;
           }
           else if("电池".equals(items.get(i).getName())){
               BearNumber++;
               BearUnit=items.get(i).getUnit();
               BearDiscount=items.get(i).getDiscount();
               BearPrice=items.get(i).getPrice();
           }
           else{
               return "商品不存在";
           }


       }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("***商店购物清单***\n");



       double ColaTotal=ColaNumber*ColaPrice;
        double BearTotal=BearNumber*BearPrice;
        double XuebiTotal=XueBiNumber*XuebiPrice;
        double StartTotal=ColaTotal+BearTotal+XuebiTotal;
        double Total=ColaTotal*ColaDiscount+BearTotal*BearDiscount+XuebiTotal*Xuebidiscount;



            if(ColaNumber!=0){
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
            }

               stringBuilder .append("----------------------\n")
                .append("总计：").append(String.format("%.2f",Total )).append("(元)").append("\n")
                       .append("节省:").append(String.format("%.2f",StartTotal-Total)).append("元").append("\n")
                .append("**********************\n")
                .toString();

        return stringBuilder.toString();
    }
}
