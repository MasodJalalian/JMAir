package airplanePkg;

import foodPkg.FoodItem;
import foodPkg.FoodItemsAmount;
import peoplePkg.ClassType;
import peoplePkg.Passenger;

import java.util.ArrayList;

/**
 * Created by Jimmy on 2016-10-26.
 */
public class Seat {
    private int seatNumber;
    private Passenger passenger;
    private FoodItem foodItem;//överflödig?

    //Masods
    private ClassType classStatus;
    private boolean booked;

    private ArrayList<FoodItemsAmount> foodItemAmounts = new ArrayList<>();//jag ändra till private. Get?

    public Seat(){

    }

    //utan food
    public Seat(int seatNr, Passenger pass){
        this.seatNumber = seatNr;
        this.passenger = pass;
    }

    public Seat(int seatNr, Passenger pass, FoodItem food){
        this.seatNumber = seatNr;
        this.passenger = pass;
        this.foodItem = food;
    }

    //Masods konstruktor
    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.booked = false;//true?
//        this.classStatus = ClassType.BUSINESS;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    //ny metod jag skapar 3:e nov för att lägga till foodItemAmount
    public void addFoodItemAmount(FoodItemsAmount foodItemAmount){
        foodItemAmounts.add(foodItemAmount);
    }

    //Masods toString()
    @Override
    public String toString() {
        return "Seat{" + "classStatus=" + classStatus + ", seatNumber=" + seatNumber + ", booked=" + booked + ", foodItemAmounts=" + foodItemAmounts + '}';
    }

    //Masods getters o setters
    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public ClassType getClassStatus() {
        return classStatus;
    }

    public void setClassStatus(ClassType classStatus) {
        this.classStatus = classStatus;
    }
}
