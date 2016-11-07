package peoplePkg;

/**
 * Created by Jimmy on 2016-10-26.
 */
public class Passenger {
    String firstName;
    String lastName;
    int ticketPrice;
    //FoodList orderedFood;
    int seatNr;
    String destination;
    int age;

    public Passenger(){

    }

    //utan orderedFood
    public Passenger(String fName, String lName, int price, int seatNr, String dest, int age){
        this.firstName = fName;
        this.lastName = lName;
        this.ticketPrice = price;
        //this.orderedFood = fList;
        this.seatNr = seatNr;
        this.destination = dest;
        this.age = age;
    }

    /*public Passenger(String fName, String lName, int price, FoodList fList, int seatNr, String dest, int age){
        this.firstName = fName;
        this.lastName = lName;
        this.ticketPrice = price;
        this.orderedFood = fList;
        this.seatNr = seatNr;
        this.destination = dest;
        this.age = age;
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    /*public FoodList getOrderedFood() {
        return orderedFood;
    }

    public void setOrderedFood(FoodList orderedFood) {
        this.orderedFood = orderedFood;
    }*/

    public int getSeatNr() {
        return seatNr;
    }

    public void setSeatNr(int seatNr) {
        this.seatNr = seatNr;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString(){
        String printout;
        printout = "Passenger traveling to " + getDestination() + " at seat number: " + getSeatNr();
        printout = printout + ". Name: " + getFirstName() + " " + getLastName() + " , age: " + getAge();
        printout = printout + ". Total ticket price: " + getTicketPrice();
        return printout;
    }
}
