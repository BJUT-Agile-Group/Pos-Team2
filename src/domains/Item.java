package domains;

/**
 * Created by Administrator on 2014/12/28.
 */
public class Item extends BaseItem {
    private String barCode;
    private String name;

    public Item(String barCode, String name, String unit, double price, double discount,boolean promotion,double vipDiscount) {

        super(unit, price, discount,promotion,vipDiscount);
        this.barCode = barCode;
        this.name = name;
    }
    public Item(String barCode, String name, String unit, double price, double discount) {

        super(unit, price, discount);
        this.barCode = barCode;
        this.name = name;
    }

    public Item(String barCode, String name, String unit, double price, boolean promotion) {

        super(unit, price, promotion);
        this.barCode = barCode;
        this.name = name;
    }
    public Item(String barCode, String name, String unit, double price) {

        super(unit, price);
        this.barCode = barCode;
        this.name = name;
    }

    public String getName() {
        return name;
    }





    @Override
    public String toString() {
        return "Item{" +
                "barCode='" + barCode + '\'' +
                ", name='" + name + '\'' +
                super.toString()+
                '}';
    }
}
