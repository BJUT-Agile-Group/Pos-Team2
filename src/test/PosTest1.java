package test;

import domains.Item;
import domains.Pos;
import domains.ShoppingChart;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by Administrator on 2014/12/28.
 */
public class PosTest1 {
    @Test
    public void testGetCorrectShoppingListForSingleItem() throws Exception {
        // given

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00,0.8));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：2.40(元)\n"
                        + "----------------------\n"

                        + "总计：2.40(元)\n"
                        + "节省：0.60(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }

    @Test
    public void testGetCorrectShoppingListForTwoSameItems() throws Exception {
        // given
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00,0.8));
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00,0.8));

        // when
        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);

        // then
        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：2瓶，单价：3.00(元)，小计：4.80(元)\n"
                        + "----------------------\n"

                        + "总计：4.80(元)\n"
                        + "节省：1.20(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testGetCorrectShoppingListForTwoDifferentItems()throws Exception{
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00,0.8));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0.7));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：2.40(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.40(元)\n"
                        + "----------------------\n"

                        + "总计：3.80(元)\n"
                        + "节省：1.20(元)\n"
                        + "**********************\n";

        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testGetCorrectShoppingListForSingleDicountItems()throws Exception{
        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0.7));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.40(元)\n"
                        + "----------------------\n"
                        + "总计：1.40(元)\n"
                        + "节省：0.60(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testGetCorrectShoppingListForDoubleSameDicountItems()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0.7));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0.7));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：电池，数量：2个，单价：2.00(元)，小计：2.80(元)\n"
                        + "----------------------\n"
                        + "总计：2.80(元)\n"
                        + "节省：1.20(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testGetCorrectShoppingListForTwoDifferentItemsWithOneDiscountAnotherOneNormal()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00,0.8));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0.7));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：2.40(元)\n"
                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.40(元)\n"
                        + "----------------------\n"
                        + "总计：3.80(元)\n"
                        + "节省：1.20(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testGetCorrectShoppingListForThreeDifferentItemsWithOneDiscountAnotherTwoNormal()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00,0.8));
        shoppingChart.add(new Item("ITEM000000", "雪碧", "瓶", 3.00,1));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0.7));

        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：2.40(元)\n"

                        + "名称：电池，数量：1个，单价：2.00(元)，小计：1.40(元)\n"
                        + "名称：雪碧，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "----------------------\n"
                        + "总计：6.80(元)\n"
                        + "节省：1.20(元)\n"
                        + "**********************\n";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    @Test
    public void testGetCorrectShoppingListForThreeDifferentItemsWithTwoDiscountAnotherOneNormal()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000", "可口可乐", "瓶", 3.00,0.8));
        shoppingChart.add(new Item("ITEM12080113", "白酒", "匹", 12.00,0.1));
        shoppingChart.add(new Item("ITEM000004", "电池", "个", 2.00,0.7));


        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "商品不存在";
        assertThat(actualShoppingList, is(expectedShoppingList));
    }
    /*
    @Test
    public void ThirdtestGetCorrectShoppingListForThreeDifferentItemsWithTwoDiscountAnotherOneNormal()throws Exception{

        ShoppingChart shoppingChart = new ShoppingChart();
        shoppingChart.add(new Item("ITEM000000"));
        shoppingChart.add(new Item("ITEM12080113"));
        shoppingChart.add(new Item("ITEM000004"));


        Pos pos = new Pos();
        String actualShoppingList = pos.getShoppingList(shoppingChart);


        String expectedShoppingList =
                "***商店购物清单***\n"
                        + "名称：可口可乐，数量：1瓶，单价：3.00(元)，小计：3.00(元)\n"
                        + "名称：杀马特，数量：1匹，单价：12.00(元)，小计：1.20(元)\n"
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
        shoppingChart.add(new Item("ITEM000000"));
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
    }*/
}