
package foodPkg;

import peoplePkg.ClassType;

import java.util.ArrayList;

//Masods
public class FoodAmountStorage {
    
    public ArrayList<FoodItem> foodStorage = new ArrayList<>();
    
    public FoodAmountStorage() {
        
        foodStorage.add(new FoodItem("Kaluga caviar", "FOOD", 20, ClassType.BUSINESS));
        foodStorage.add(new FoodItem("Cordon Bleu", "FOOD", 12, ClassType.BUSINESS));
        foodStorage.add(new FoodItem("Whole fried Goose", "FOOD", 50, ClassType.BUSINESS));
        foodStorage.add(new FoodItem("Beaujolais Patriarch", "DRINK", 15, ClassType.BUSINESS));
        foodStorage.add(new FoodItem("Champagne Intergalactic", "DRINK", 25, ClassType.BUSINESS));

        foodStorage.add(new FoodItem("Chicken", "FOOD", 8, ClassType.ECONOMY));
        foodStorage.add(new FoodItem("Beef", "FOOD", 10, ClassType.ECONOMY));
        foodStorage.add(new FoodItem("Milk", "DRINK", 2, ClassType.ECONOMY));
        foodStorage.add(new FoodItem("Water", "DRINK", 1, ClassType.ECONOMY));
        foodStorage.add(new FoodItem("Soft drink", "DRINK", 2, ClassType.ECONOMY));

    }
}
