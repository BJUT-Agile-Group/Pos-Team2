package domains;

/**
 * Created by UltrA on 2014/12/31.
 */
public class Good {
    String name;
    int quantity;
    double price;
    double save;
    double single;
    String unit;
    double discount;
    public Good(double price,double discount,String unit,double single)
    {
        this.unit=unit;
        this.single=single;
        this.quantity=1;
        this.price=price*discount;
        this.discount=discount;
        save=price*(1-discount);
    }
    public void statistics (double price2,double discount2)
    {
        this.quantity++;
        this.price=this.price+(price2*discount2);
        this.save=this.save+price2*(1-discount2);
    }
    public String  toString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(",数量：").append(quantity).append(unit).append("，")
                .append("单价：").append(String.format("%.2f", single)).append("(元)").append("，")
                .append("小计：").append(String.format("%.2f",price )).append("(元)").append("\n");
        return stringBuilder.toString();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSave() {
        return save;
    }

    public void setSave(double save) {
        this.save = save;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
