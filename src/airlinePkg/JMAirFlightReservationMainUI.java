package airlinePkg;

import airplanePkg.Airplane;
import airplanePkg.Airport;
import airplanePkg.Seat;
import foodPkg.FoodAmountStorage;
import foodPkg.FoodItem;
import foodPkg.FoodItemsAmount;
import peoplePkg.ClassType;
import peoplePkg.Passenger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class JMAirFlightReservationMainUI {

    public static Scanner sc = new Scanner(System.in);

    //la jag(Jimmy) till. Blev dåligt med static
    private String fNamn;//static
    private String eNamn;//static
    private int totP;//static

    //lägger jag(Jimmy) till 3:e nov
    private Airplane ap;
    //private JMAirFlightReservationMainUI ui;

    public static void main(String[] args) {

        JMAirFlightReservationMainUI ui = new JMAirFlightReservationMainUI();
        ui.ap = ui.createADestination();

        int[] availableBusinussSeats = {1, 2, 3, 4, 5};
        int[] availableEconomySeats = {6, 7, 8, 9, 10};

        // The plane is ready to fly when you booked for 5 seats Business and 5 seats Economy.
        while (!Airplane.isReadyToFly) {

            int businessOrEconomi = ui.chooseFlightClass(); // Return 1 as BUSINESS or 2 as ECONOMY.

            // Return BUSINUSS or ECONOMY Seats number.
            int businessOrEconomiSeatsNo = ui.chooseASeat(businessOrEconomi, ui.ap, availableBusinussSeats, availableEconomySeats);

            // seat no 1-5 Business & 6-10 Economy
            if (businessOrEconomiSeatsNo != 0) {// seat no = 0 The customer has repented booking flights.
                Seat seat = ui.createASeat(businessOrEconomiSeatsNo, availableBusinussSeats, availableEconomySeats);

                // Order the fodd to have onboard.
                int foodCosts = orderFood(seat);

                // Print out the ticket.
                ui.doPrintTicket(ui.ap, seat, foodCosts);

                //min kommentar: skapa passagerare här med tillgänglig info? och skicka till seat o airplane?
                //så man kan lägga till
                //age o destination får jag hitta på
                //Passenger p = new Passenger(fNamn, eNamn, totP, seat.getSeatNumber(), "Sydney", 38);

                //behöver också flytta till andra klasser
            }

            Airplane.isReadyToFly = ui.readyToFly(ui.ap);
            if(Airplane.isReadyToFly == true){
                Thread planeThread = new Thread(ui.ap);
                ui.ap.printPassengerList();
                planeThread.start();

                Airport aPortWDest = new Airport(ui.ap.getDestination());
            }
        }
    }

    public  Airplane createADestination() {

        System.out.println("Welcome to JM airline flight reservation.");
        System.out.println("Please choose and then write your flight destination among the below list and the press Enter");

        ArrayList<Seat> seats = new ArrayList<>();
        Airplane airPlane = new Airplane(seats);

        String dest = "";
        do {
            System.out.println("London Paris Rome Miami");
            dest = sc.nextLine();
        } while (!dest.equalsIgnoreCase("London"));
        airPlane.setDestination(dest);
        return airPlane;
    }

    public Seat createASeat(int businessOrEconomiSeatsNo, int[] availableBusinussSeats, int[] availableEconomySeats) {
        Seat seat = new Seat(businessOrEconomiSeatsNo);
        seat.setBooked(true);
        if (businessOrEconomiSeatsNo < 6) {
            seat.setClassStatus(ClassType.BUSINESS);
            availableBusinussSeats[businessOrEconomiSeatsNo - 1] = 0;
        } else {
            seat.setClassStatus(ClassType.ECONOMY);
            availableEconomySeats[businessOrEconomiSeatsNo - 5 - 1] = 0;
        }
        return seat;
    }

    public int chooseFlightClass() {
        String businessClass = "1";
        String economyClass = "2";
        String businessOrEconomiToString = null;
        System.out.println("Please press 1 for booking Business class or 2 for Economy class.");
        System.out.println("1- Business class costs 20 000 SEK");
        System.out.println("2- Economy class costs 5 000 SEK");
        do {
            businessOrEconomiToString = sc.nextLine();
        } while (!businessOrEconomiToString.equals(businessClass)
                && !businessOrEconomiToString.equals(economyClass));
        return Integer.parseUnsignedInt(businessOrEconomiToString);
    }

    public static int orderFood(Seat seat) {
        String yesOrNoOrderFood;
        do {
            System.out.println("If you would you like to order food and drink to have onboard, please enter YES and press Enter, "
                    + "otherwise enter NO and then press Enter");
            yesOrNoOrderFood = sc.nextLine();
        } while (!yesOrNoOrderFood.equalsIgnoreCase("Yes")
                && !yesOrNoOrderFood.equalsIgnoreCase("No"));
        if (yesOrNoOrderFood.equalsIgnoreCase("No")) {
            return 0;
        }

        System.out.println("Now you are able to order food and drink to eat on board as:");
        String amountOfThisRow;
        int nextRowInFoodList = 0;
        int foodCosts = 0;

        FoodAmountStorage foodAmountStorage = new FoodAmountStorage();
        switch (seat.getClassStatus()) {
            case BUSINESS:
                for (FoodItem foodItem : foodAmountStorage.foodStorage) {
                        if (foodItem.getCustomerClass().equals(ClassType.BUSINESS)) {
                            System.out.println(++nextRowInFoodList + "- " + foodItem);
                    }
                }

                System.out.println("Please enter your desired amount of this food item (max 3) and enter, otherwise enter 0 and then press enter");

                for (int i = 0; i < 5; i++) {
                    do {
                        System.out.print(i + 1 + "- ");
                        amountOfThisRow = sc.nextLine();
                    } while (!amountOfThisRow.equals("1")
                            && !amountOfThisRow.equals("2")
                            && !amountOfThisRow.equals("3")
                            && !amountOfThisRow.equals("0"));
                    FoodItemsAmount foodItemAmount = new FoodItemsAmount(
                            foodAmountStorage.foodStorage.get(i).getName(),
                            foodAmountStorage.foodStorage.get(i).getType(),
                            foodAmountStorage.foodStorage.get(i).getPrice(),
                            foodAmountStorage.foodStorage.get(i).getCustomerClass(),
                            Integer.parseUnsignedInt(amountOfThisRow));
                    seat.addFoodItemAmount(foodItemAmount);
                    foodCosts += Integer.parseUnsignedInt(amountOfThisRow) * foodAmountStorage.foodStorage.get(i).getPrice();
                }
                break;
            case ECONOMY:
                for (FoodItem foodItem : foodAmountStorage.foodStorage) {
                    if (foodItem.getCustomerClass().equals(ClassType.BUSINESS)) {
                        System.out.println(++nextRowInFoodList + "- " + foodItem);
                    }
                }

                System.out.println("Please enter your desired amount of this food item (max 3) and enter, otherwise enter 0 and then press enter");

                for (int i = 0; i < 5; i++) {
                    do {
                        System.out.print(i + 1 + "- ");
                        amountOfThisRow = sc.nextLine();
                    } while (!amountOfThisRow.equals("1")
                            && !amountOfThisRow.equals("2")
                            && !amountOfThisRow.equals("3")
                            && !amountOfThisRow.equals("0"));
                    FoodItemsAmount foodItemAmount = new FoodItemsAmount(
                            foodAmountStorage.foodStorage.get(i + 5).getName(),
                            foodAmountStorage.foodStorage.get(i + 5).getType(),
                            foodAmountStorage.foodStorage.get(i + 5).getPrice(),
                            foodAmountStorage.foodStorage.get(i + 5).getCustomerClass(),
                            Integer.parseUnsignedInt(amountOfThisRow));
                    seat.addFoodItemAmount(foodItemAmount);
                    foodCosts += Integer.parseUnsignedInt(amountOfThisRow) * foodAmountStorage.foodStorage.get(i + 5).getPrice();
                }
                break;
        }

        return foodCosts;
    }

    public int chooseASeat(int businessOrEconomi, Airplane airPlane, int[] availableBusinussSeats, int[] availableEconomySeats) {
        String businessOrEconomiSeatsNo = null;
        int[] zeroArray = {0, 0, 0, 0, 0};
        if (businessOrEconomi == 1 && Arrays.equals(availableBusinussSeats, zeroArray)) {
            System.out.println("Unfortunately the Business class seats are already full booked.");
            String yesOrNoToBookEconomy;
            do {
                System.out.println("If you would like to book a Economy class in stead enter YES and press enter. "
                        + "Otherwise No and then press enter.");
                yesOrNoToBookEconomy = sc.nextLine();
            } while (!yesOrNoToBookEconomy.equalsIgnoreCase("yes")
                    && !yesOrNoToBookEconomy.equalsIgnoreCase("no"));
            if (yesOrNoToBookEconomy.equalsIgnoreCase("yes")) {
                businessOrEconomi = 2;
            } else {
                System.out.println("You have repented booking flights.");
                return 0;
            }
        } else if (businessOrEconomi == 2 && Arrays.equals(availableEconomySeats, zeroArray)) {
            System.out.println("Unfortunately the Economy class seats are already full booked.");
            String yesOrNoToBookEconomy;
            do {
                System.out.println("If you would like to book a Business class in stead enter YES and press enter. Otherwise No and then press enter.");
                yesOrNoToBookEconomy = sc.nextLine();
            } while (!yesOrNoToBookEconomy.equalsIgnoreCase("yes")
                    && !yesOrNoToBookEconomy.equalsIgnoreCase("no"));
            if (yesOrNoToBookEconomy.equalsIgnoreCase("yes")) {
                businessOrEconomi = 1;
            } else {
                System.out.println("You have repented booking flights.");
                return 0;
            }
        }
        switch (businessOrEconomi) {
            case 1:
                do {
                    System.out.println("The Business class seats no are 1, 2, 3, 4 and 5.");
                    System.out.println("Now you are able to choose among the available one/ones as below. So write your favorit one and then press Enter");
                    for (int i = 0; i < availableBusinussSeats.length; i++) {
                        if (availableBusinussSeats[i] != 0) {
                            System.out.print(availableBusinussSeats[i] + ", ");
                        }
                    }
                    System.out.println("So write your favorit one and then press Enter");
                    businessOrEconomiSeatsNo = sc.nextLine();
                } while (!businessOrEconomiSeatsNo.equals(Integer.toString(availableBusinussSeats[0]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableBusinussSeats[1]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableBusinussSeats[2]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableBusinussSeats[3]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableBusinussSeats[4])));
                break;
            case 2:
                do {

                    System.out.print("The Economy class seats no are 6, 7, 8, 9 and 10.");
                    System.out.println("Now you are able to choose among the available one/ones as below. So write your favorit one and then press Enter");
                    for (int i = 0; i < availableEconomySeats.length; i++) {
                        if (availableEconomySeats[i] != 0) {
                            System.out.print(availableEconomySeats[i] + ", ");
                        }
                    }

                    System.out.println("So write your favorit one and then press Enter");
                    businessOrEconomiSeatsNo = sc.nextLine();
                } while (!businessOrEconomiSeatsNo.equals(Integer.toString(availableEconomySeats[0]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableEconomySeats[1]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableEconomySeats[2]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableEconomySeats[3]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableEconomySeats[4])));
                break;
//            default:
//                System.out.println("You did'nt choose a right option. Please try again.");
//                System.out.println("1- Business class");
//                System.out.println("2- Economi class");
            }
        return Integer.parseUnsignedInt(businessOrEconomiSeatsNo);
    }

    public void doPrintTicket(Airplane airPlane, Seat seat, int foodCosts) {
        System.out.println("Please enter your firstname");
        String firstName = sc.next();
        fNamn = firstName;

        System.out.println("Please enter your surname");
        String surname = sc.next();
        eNamn = surname;

        System.out.println("\nPASSENGER TICKET INFORMATION:");
        System.out.println("Welcome onboard dear " + firstName + " " + surname);
        System.out.println("Your destination is " + airPlane.getDestination() + ". You booked " + seat.getClassStatus()
                + " class and your seat nnmber is " + seat.getSeatNumber());
        System.out.println("You ordered food and drink for " + foodCosts + " SEK");
        int totalTicketPrice;
        if (seat.getClassStatus().equals(ClassType.BUSINESS)) {
            totalTicketPrice = foodCosts + 20000;
            airPlane.setBusinessSeats(airPlane.getBusinessSeats() - 1);
        } else {
            totalTicketPrice = foodCosts + 5000;
            airPlane.setEconomiSeats(airPlane.getEconomiSeats() - 1);
        }
        totP = totalTicketPrice;
        System.out.println("Your total ticket price is " + totalTicketPrice + " SEK \n");

        Airplane.totalTicketSales += totalTicketPrice;

        Passenger p = new Passenger(fNamn, eNamn, totP, seat.getSeatNumber(), airPlane.getDestination(), 38);
        seat.setPassenger(p);
        airPlane.addSeat(seat);
        airPlane.addSeatAndPass(seat, p);
    }

    public boolean readyToFly(Airplane airPlane) {

        System.out.println("\nPlease check if there are more travelers in queue to book flight. Are there? enter YES,"
                + " otherwise enter No and then press Enter.");
        String yesOrNoToBookFlight;
        do {
            yesOrNoToBookFlight = sc.nextLine();
        } while (!yesOrNoToBookFlight.equalsIgnoreCase("YES")
                && !yesOrNoToBookFlight.equalsIgnoreCase("NO"));
        if(yesOrNoToBookFlight.equalsIgnoreCase("No") ||
                (airPlane.getBusinessSeats() == 0 && airPlane.getEconomiSeats() == 0)) {

            System.out.println("Please check in and go onboard.");
            System.out.println("Now this plane is ready to fly.\n");
            printPassengersListAndTotalIncome(airPlane);

            System.out.println();
            System.out.println("Fills the rest of the passengers");
            this.ap.fillRestOfPassengersOnPlane();

            return true;
        }
        return false;
    }

    public static void printPassengersListAndTotalIncome(Airplane airPlane) {
        ArrayList<Seat> seats = airPlane.getSeats();
        for (Seat seat : seats) {
            System.out.println(seat);
        }
        System.out.println("\nThe total ticket sales is " + airPlane.totalTicketSales + " SEK");
        System.out.println("\nThe total profit for JMairline is " + (int) (airPlane.totalTicketSales * 0.3) + " SEK");
    }
}
