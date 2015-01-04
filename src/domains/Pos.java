package domains;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2014/12/28.
 */
public class Pos {
    private static final int  OfferNumber=2;
    //返回主要的界面
    public String getShoppingList(ShoppingChart shoppingChart) {

        ArrayList<Item> items = shoppingChart.getItems();
        Map<String, Good> goodMapping = new HashMap<String, Good>();//映射存储商品名字和信息的映射
        List<String> nameList = new ArrayList<String>();//商品名字列表在遍历HashMap时使用，Hashmap自动排序遭不住啊……
        List<Good> OfferList = new ArrayList<Good>();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            if (goodMapping.containsKey(item.getName()) == false) {//如果不存在这个映射就加入这个映射
                goodMapping.put(item.getName(), new Good(item.getPrice(), item.getDiscount(), item.getUnit(), item.isPromotion(),item.getName()));
                nameList.add(item.getName());
            } else//如果存在就对信息进行加减操作
            {
                goodMapping.get(item.getName()).statistics(item.getPrice(), item.getDiscount());
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("***商店购物清单***\n")
                .append("打印时间:").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())).append("\n");


        double total = 0, save = 0;
        Iterator iterator = nameList.iterator();
        //下面是按自动排序的顺序输出的遍历，又需要再注释回来
        //Iterator it=goodMapping.entrySet().iterator();
//        while(iterator.hasNext())
//        {
//            Map.Entry entry= (Map.Entry) iterator.next();
//            stringBuilder.append("名称：").append(entry.getKey());
//            stringBuilder.append(((Good)entry.getValue()).toString());
//        }
        while (iterator.hasNext())//遍历应该看得懂吧，看不懂，宝宝实在是……
        {
            String name = (String) iterator.next();
            stringBuilder.append("名称：").append(name);
            Good good = goodMapping.get(name);
            stringBuilder.append(good.toString());
            total = total + good.getAmount();
            save = save + good.getSave();
            //商品要优惠
            if (good.isPromotion() && good.getQuantity() >= OfferNumber) {
                save = save + good.getPrice();
                OfferList.add(good);
            }
        }

        if (OfferList.size()!= 0) {
            stringBuilder.append("\n挥泪赠送商品:\n").append("----------------------\n");
            for (int i = 0; i < OfferList.size(); i++) {
                stringBuilder.append("名称：").append(OfferList.get(i).getName()).append(",数量：").append(1).append(OfferList.get(i).getUnit()).append("\n");
            }
        }
        stringBuilder.append("----------------------\n")
                .append("总计：").append(String.format("%.2f", total)).append("(元)").append("\n");
        if (save != 0) {
            stringBuilder.append("节省：").append(String.format("%.2f", save)).append("(元)").append("\n");
        }
        stringBuilder.append("**********************\n");


        return stringBuilder.toString();
    }
}
