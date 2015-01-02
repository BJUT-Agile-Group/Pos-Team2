package test;

import Utility.ManageDao;
import domains.*;
import domains.Item;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by KEN on 2015/1/2.
 */
public class HashMapTest extends TestCase{
    private Map testgoodMap;
    private Map testMap2;



    @Override
    public void setUp() throws Exception {

        testgoodMap = new HashMap<String, Good>();//映射存储商品名字和信息的映射


    }

    public void testADD() throws Exception {
       // ShoppingChart shoppingChart = new ShoppingChart();
        List<String> nameList = new ArrayList<String>();
       // ManageDao mainDao = new ManageDao();

       // shoppingChart.setItems(mainDao.getData());
       // ArrayList<Item> items = shoppingChart.getItems();
        ArrayList<Item> items=new ArrayList<Item>();
        items.add(new Item("item","可口","bottle",2.8,0.7));
        items.add(new Item("item","可口","bottle",2.8,0.7));
        items.add(new Item("item2","电池","个",3.4));

        Map<String, Good> testgoodMap = new HashMap<String, Good>();
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            if ( testgoodMap.containsKey(item.getName()) == false) {//如果不存在这个映射就加入这个映射
                testgoodMap.put(item.getName(), new Good(item.getPrice(), item.getDiscount(), item.getUnit()));
                nameList.add(item.getName());
            } else//如果存在就对信息进行加减操作
            {
                testgoodMap.get(item.getName()).statistics(item.getPrice(), item.getDiscount());
            }
        }

       assertTrue("contain 电池",testgoodMap.containsKey("电池"));
        assertEquals(2,testgoodMap.size());
        assertTrue("this is right",testgoodMap.containsKey("可口"));
        assertEquals(2,nameList.size());
    }

    public HashMapTest(String name) {
        super(name);
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public static Test suite(){
        return new TestSuite(HashMapTest.class);
    }

    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }

}
