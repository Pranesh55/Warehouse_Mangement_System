package model;

public class Product {
	private int id;
	private String name;
	private String description;
	private int stock;
	private Float price;
	private String image;
	private String unit;
	
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getStock() {
		return stock;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getPrice() {
		return price;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImage() {
		return image;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnit() {
		return unit;
	}
	public void setDetails(int id,String name,String description ,int stock ,String unit,float price,String image) {
		setId(id);
		setName(name);
		setDescription(description);
		setStock(stock);
		setPrice(price);
		setId(id);
		setId(id);
	}
}
