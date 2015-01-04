package domains;

/**
 * Created by KEN on 2015/1/1.
 */
//基类
public class BaseItem {
    private String unit;
    private double price;
    private double discount;
    private boolean promotion;

    public BaseItem() {
    }

    public BaseItem(String unit, double price, double discount) {
        this.unit = unit;
        this.price = price;
        this.discount = discount;
        this.promotion=false;
    }
    public BaseItem(String unit, double price, double discount,boolean promotion) {
        this.unit = unit;
        this.price = price;
        this.discount = discount;
        this.promotion=promotion;
    }
    public BaseItem(String unit,double price,boolean promotion){
        this.unit = unit;
        this.price = price;
        this.discount = 1;
        this.promotion=promotion;
    }

    public BaseItem(String unit, double price) {
        this.unit = unit;
        this.price = price;
        this.discount = 1;
        this.promotion=false;
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

    public boolean isPromotion() {
        return promotion;
    }

    @Override
    public String toString() {
        return
                "unit='" + unit + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", promotion=" + promotion
                ;
    }
}
