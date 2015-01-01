package domains;

/**
 * Created by KEN on 2015/1/1.
 */
//基类
public class BaseItem {
    private String unit;
    private double price;
    private double discount;
    public BaseItem(){}
    public BaseItem(String unit,double price,double discount){
        this.unit=unit;
        this.price=price;
        this.discount=discount;
    }
    public BaseItem(String unit,double price){
        this.unit=unit;
        this.price=price;
        this.discount=1;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
