package domains;

/**
 * Created by UltrA on 2014/12/31.
 * 这个类计算总价，折扣等内容
 */
public class Good extends BaseItem {

   private int quantity;//数量
    private double amount;//总价，打折后
    private double save;//这个商品节省了多少钱
    private  String name;


    //首次输入时使用
    public Good(double price, double discount, String unit,boolean promotion,String name,double vipDiscount)//首次输入时使用
    {

        super(unit, price, discount,promotion,vipDiscount);
        this.quantity = 1;
        this.amount = price * discount*vipDiscount;
        this.save = price * (1 - discount*vipDiscount);
        this.name=name;

    }
    public Good(double price, double discount, String unit)//首次输入时使用
    {

        super(unit, price, discount);
        this.quantity = 1;
        this.amount = price * discount;
        this.save = price * (1 - discount);
        this.name=name;

    }
    public boolean valiate(){
        if(getQuantity()>=2&&getDiscount()<1&&getDiscount()>0&isPromotion()){
            System.out.println("同种商品不能同时打折和优惠 name"+getName()+"discount"+getDiscount()+"promotion"+isPromotion());
            return false;
        }
        else if(!super.valiate()){
            return false;
        }
        else{
            return true;
        }
    }

    public void statistics(double price2, double discount2,double vipDiscount)//在后面还有输入时使用
    {
        this.quantity++;//数量加一
        this.amount = this.amount + (price2 * discount2*vipDiscount);
        this.save = this.save + price2 * (1 - discount2*vipDiscount);
    }
    public void statistics(double price2, double discount2)
    {
        this.quantity++;//数量加一
        this.amount = this.amount + (price2 * discount2);
        this.save = this.save + price2 * (1 - discount2);
    }
    public String toString()//变成字符串
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("，数量：").append(quantity).append(super.getUnit()).append("，")
                .append("单价：").append(String.format("%.2f", super.getPrice())).append("(元)").append("，")
                .append("小计：").append(String.format("%.2f", amount)).append("(元)").append("\n");
        return stringBuilder.toString();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getSave() {
        return save;
    }

    public void setSave(double save) {
        this.save = save;
    }

    public String getName() {
        return name;
    }


}
