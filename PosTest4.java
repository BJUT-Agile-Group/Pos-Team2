import domains.Item;
import domains.Pos;
import domains.ShoppingChart;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2014/12/28.
 */
public class PosTest {
    @Test
    public void testGetCorrectShoppingListForSingleItem() throws Exception {
        // given
        Item cokeCola = new Item("ITEM000000", "可口可乐", "瓶", 3.00);
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(cokeCola);

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                          "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "----------------------\n"
                        + "总计：3.00(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    @Test
    public void testGetCorrectShoppingListForTwoSameItems() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00));
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                          "***商店购物清单***\n"
                        + "名称：可口可乐，数量：2瓶，单价：3.00(元)，小计：6.00(元)\n"
                        + "----------------------\n"
                        + "总计：6.00(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testGetCorrectShoppingListForTwoDifferentItems()throws Exception{
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：2.00(元)\n"
                        + "----------------------\n"
                        + "总计：5.00(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testGetCorrectShoppingListForSingleDicountItems()throws Exception{
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0.8));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                + "名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                + "----------------------\n"
                + "总计：1.60(元)\n"
                        + "节省：0.40(元)\n"
                + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testGetCorrectShoppingListForDoubleSameDicountItems()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0.8));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0,8));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：电池，数量：2个，单价：2.00(元)，小计：3.20(元)\n"
                        + "----------------------\n"
                        + "总计：3.20(元)\n"
                        + "节省：0.80(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testGetCorrectShoppingListForTwoDifferentItemsWithOneDiscountAnotherOneNormal()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0.8));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                        + "----------------------\n"
                        + "总计：4.60(元)\n"
                        + "节省：0.40(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testGetCorrectShoppingListForThreeDifferentItemsWithOneDiscountAnotherTwoNormal()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00));
        shoppingChart.add(new Item("ITEM000001", "雪碧", "瓶", 3.00));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0.8));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：雪碧，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                        + "----------------------\n"
                        + "总计：7.60(元)\n"
                        + "节省：0.40(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testGetCorrectShoppingListForThreeDifferentItemsWithTwoDiscountAnotherOneNormal()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00));
        shoppingChart.add(new Item("ITEM000002", "王老吉", "听", 12.00,0.1));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0.8));


        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：王老吉，数量：1听，单价：12.00(元)，小计：1.20(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                        + "----------------------\n"
                        + "总计：5.80(元)\n"
                        + "节省：11.20(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    //This is the third time test
    @Test
    public void ThirdtestGetCorrectShoppingListForThreeDifferentItemsWithTwoDiscountAnotherOneNormal()throws Exception{



        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000002"));
        shoppingChart.add(new Item("ITEM000004"));


        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：王老吉，数量：1听，单价：12.00(元)，小计：1.20(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                        + "----------------------\n"
                        + "总计：5.80(元)\n"
                        + "节省：11.20(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void ThirdtestGetCorrectShoppingListForSingleItem() throws Exception {
        // given
        Item cokeCola = new Item("ITEM000000");
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(cokeCola);

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "----------------------\n"
                        + "总计：3.00(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    @Test
    public void ThirdtestGetCorrectShoppingListForTwoSameItems() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：2瓶，单价：3.00(元)，小计：6.00(元)\n"
                        + "----------------------\n"
                        + "总计：6.00(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void ThirdtestGetCorrectShoppingListForTwoDifferentItems()throws Exception{
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000004"));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：2.00(元)\n"
                        + "----------------------\n"
                        + "总计：5.00(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void ThirdtestGetCorrectShoppingListForSingleDicountItems()throws Exception{
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000004"));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                        + "----------------------\n"
                        + "总计：1.60(元)\n"
                        + "节省：0.40(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void ThirdtestGetCorrectShoppingListForDoubleSameDicountItems()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000004"));
        shoppingChart.add(new Item("ITEM000004"));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：电池，数量：2个，单价：2.00(元)，小计：3.20(元)\n"
                        + "----------------------\n"
                        + "总计：3.20(元)\n"
                        + "节省：0.80(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void ThirdtestGetCorrectShoppingListForTwoDifferentItemsWithOneDiscountAnotherOneNormal()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000004"));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                        + "----------------------\n"
                        + "总计：4.60(元)\n"
                        + "节省：0.40(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void ThirdtestGetCorrectShoppingListForThreeDifferentItemsWithOneDiscountAnotherTwoNormal()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000001"));
        shoppingChart.add(new Item("ITEM000004"));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：雪碧，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                        + "----------------------\n"
                        + "总计：7.60(元)\n"
                        + "节省：0.40(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    //This is the Fourth test
    @Test
    public void FourthtestGetCorrectShoppingListForThreeSameItemsWithOnePresentedl()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
        "***商店购物清单***\n"
        //"打印时间：2014年08月04日 08:09:05"
                ----------------------
                +"名称：可口可乐，数量：3瓶，单价：3.00(元)，小计：6.00(元)\n"
                +" ----------------------\n"
                +" 挥泪赠送商品：\n"
                +" 名称：可口可乐，数量：1瓶\n"
                +" ----------------------\n"
                     +"总计：6.00(元)\n"
                     +"节省：3.00(元)\n"
               +"**********************\n"
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void FourthTestGetCorrectShoppingListForFiveSameItemsWithOnePresentedl()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        //"打印时间：2014年08月04日 08:09:05"
                        ----------------------
                        +"名称：可口可乐，数量：5瓶，单价：3.00(元)，小计：12.00(元)\n"
                        +" ----------------------\n"
                        +" 挥泪赠送商品：\n"
                        +" 名称：可口可乐，数量：1瓶\n"
                        +" ----------------------\n"
                        +"总计：12.00(元)\n"
                        +"节省：3.00(元)\n"
                        +"**********************\n"
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    @Test
    public void FourthTestGetCorrectShoppingListForSixSameItemsWithDoublePresentedl()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        //"打印时间：2014年08月04日 08:09:05"
                        ----------------------
                        +"名称：可口可乐，数量：6瓶，单价：3.00(元)，小计：12.00(元)\n"
                        +" ----------------------\n"
                        +" 挥泪赠送商品：\n"
                        +" 名称：可口可乐，数量：2瓶\n"
                        +" ----------------------\n"
                        +"总计：12.00(元)\n"
                        +"节省：6.00(元)\n"
                        +"**********************\n"
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void FourthTestGetCorrectShoppingListForFiveSameItemsAndSingleItemWithOnePresentedl()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000001"));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        //"打印时间：2014年08月04日 08:09:05"
                        ----------------------
                        +"名称：可口可乐，数量：5瓶，单价：3.00(元)，小计：12.00(元)\n"
                        +"名称：雪碧，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        +" ----------------------\n"
                        +" 挥泪赠送商品：\n"
                        +" 名称：可口可乐，数量：1瓶\n"
                        +" ----------------------\n"
                        +"总计：15.00(元)\n"
                        +"节省：3.00(元)\n"
                        +"**********************\n"
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void FourthTestGetCorrectShoppingListForFiveSameItemsAndSingleDiscountItemWithOnePresentedl()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000004"));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        //"打印时间：2014年08月04日 08:09:05"
                        ----------------------
                        +"名称：可口可乐，数量：5瓶，单价：3.00(元)，小计：12.00(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                        +" ----------------------\n"
                        +" 挥泪赠送商品：\n"
                        +" 名称：可口可乐，数量：1瓶\n"
                        +" ----------------------\n"
                        +"总计：13.60(元)\n"
                        +"节省：3.40(元)\n"
                        +"**********************\n"
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void FourthTestGetCorrectShoppingListForFiveSameItemsAndSingleDiscountItemAndOneFullItemWithOnePresentedl()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM000004"));
        shoppingChart.add(new Item("ITEM000001"));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        //"打印时间：2014年08月04日 08:09:05"
                        ----------------------
                        +"名称：可口可乐，数量：5瓶，单价：3.00(元)，小计：12.00(元)\n"
                        +"名称：电池，数量：1个，单价：2.00(元)，小计：1.60(元)\n"
                        +"名称：雪碧，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        +" ----------------------\n"
                        +" 挥泪赠送商品：\n"
                        +" 名称：可口可乐，数量：1瓶\n"
                        +" ----------------------\n"
                        +"总计：16.60(元)\n"
                        +"节省：3.40(元)\n"
                        +"**********************\n"
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

}