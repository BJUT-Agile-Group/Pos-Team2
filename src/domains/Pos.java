package domains;

import java.util.*;

/**
 * Created by Administrator on 2014/12/28.
 */
public class Pos {

    //返回主要的界面
    public String getShoppingList(ShoppingChart shoppingChart) {
        ArrayList<Item> items = shoppingChart.getItems();
        Map<String,Good> goodMapping =new HashMap<String, Good>();//映射存储商品名字和信息的映射
        List<String> nameList=new ArrayList<String>();//商品名字列表在遍历HashMap时使用，Hashmap自动排序遭不住啊……
       for(int i=0;i<items.size();i++){
           Item item=items.get(i);
          // System.out.println("Name:"+item.getName());
           if(goodMapping.containsKey(item.getName())==false){//如果不存在这个映射就加入这个映射
               goodMapping.put(item.getName(),new Good(item.getPrice(),item.getDiscount(),item.getUnit(),item.getPrice()));
               nameList.add(item.getName());
           }
            else//如果存在就对信息进行加减操作
           {
               goodMapping.get(item.getName()).statistics(item.getPrice(),item.getDiscount());
           }
       }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("***商店购物清单***\n");




        double total=0,save=0;
        Iterator iterator=nameList.iterator();
        //下面是按自动排序的顺序输出的遍历，又需要再注释回来
        //Iterator it=goodMapping.entrySet().iterator();
//        while(iterator.hasNext())
//        {
//            Map.Entry entry= (Map.Entry) iterator.next();
//            stringBuilder.append("名称：").append(entry.getKey());
//            stringBuilder.append(((Good)entry.getValue()).toString());
//        }
        while(iterator.hasNext())//遍历应该看得懂吧，看不懂，宝宝实在是……
        {
            String name=(String)iterator.next();
            stringBuilder.append("名称：").append(name);
            Good good=goodMapping.get(name);
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
