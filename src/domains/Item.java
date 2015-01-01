package domains;

/**
 * Created by Administrator on 2014/12/28.
 */
public class Item  extends BaseItem{
    private String barCode;
    private String name;

    public Item(String barCode, String name, String unit, double price,double discount) {

        super(unit,price,discount);
        this.barCode = barCode;
        this.name = name;
    }
    public Item(String barCode, String name, String unit, double price) {

        super(unit,price);
        this.barCode = barCode;
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
