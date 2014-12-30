package test;

import java.io.Serializable;

public class Item implements Serializable{
	public String barcode;
	public String name;
	public String unit;
	public double price;
	public Item(String barcode,String name,String unit,double price){
		this.barcode=barcode;
		this.name=name;
		this.unit=unit;
		this.price=price;
	}
	public String tostring(){
	
		
		return "barcode"+barcode+"name"+name+"unit"+unit+"price"+price;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
