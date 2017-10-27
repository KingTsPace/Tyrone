package cn.soso.entity;

/**
 * 使用场景
 * @author Administrator
 *
 */
public class Scene {

	private String type;
	private int data;
	private int description;
	private double price;
	
	//[start]getter && setter
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getData() {
		return data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public int getDescription() {
		return description;
	}
	public void setDescription(int description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	//[end]
	
	
}
