package foodPkg;

import peoplePkg.ClassType;

//Masods
public class FoodItem {

	private String name;
	private String type;
	private int price;
	private ClassType customerClass;

        public FoodItem (String name, String type, int price, ClassType customerClass) {
            this.name = name;
            this.type = type;
            this.price = price;
            this.customerClass = customerClass;
        }
	public int getPrice() {
		return this.price;
	}

	public String getName() {
		return this.name;
	}
        
	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ClassType getCustomerClass() {
		return this.customerClass;
	}

	public void setCustomerClass(ClassType customerClass) {
		this.customerClass = customerClass;
	}
        
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "FoodItem{" + "name=" + name + ", type=" + type + ", price=" + price + ", customerClass=" + customerClass + '}';
    }
    
}