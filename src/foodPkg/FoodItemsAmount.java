package foodPkg;

import peoplePkg.ClassType;

//Masods
public class FoodItemsAmount extends FoodItem{
    private int amountofFoodItem;
    public FoodItemsAmount(String name, String type, int price, ClassType customerClass, int amountofFoodItem) {
        super(name, type, price, customerClass);
        this.amountofFoodItem = amountofFoodItem;
    }

    public void setAmountofFoodItem(int amountofFoodItem) {
        this.amountofFoodItem = amountofFoodItem;
    }

    @Override
    public String toString() {
        return getName() + " " + getPrice() + " SEK " + amountofFoodItem + " pcs";
    }
    
}
