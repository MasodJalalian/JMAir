package mainUI;

import airplanePkg.Airplane;
import airplanePkg.Seat;
import foodPkg.FoodAmountStorage;
import foodPkg.FoodItem;
import foodPkg.FoodItemsAmount;
import peoplePkg.ClassType;
import peoplePkg.Passenger;
import java.util.Arrays;
import java.util.Scanner;

public class JMAirFlightReservationUI {

    public static Scanner sc = new Scanner(System.in);
    public static int[][] availableBusinussSeats = {{1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}, {1, 2, 3, 4, 5}};
    public static int[][] availableEconomySeats = {{6, 7, 8, 9, 10}, {6, 7, 8, 9, 10}, {6, 7, 8, 9, 10}, {6, 7, 8, 9, 10}};

    public Airplane[] welcomeToJMAirLine() {
        System.out.println("Welcome to JM airline flight reservation!");
        Airplane[] airplanes = new Airplane[4];
        airplanes[0] = new Airplane();
        airplanes[1] = new Airplane();
        airplanes[2] = new Airplane();
        airplanes[3] = new Airplane();
        airplanes[0].setDestination("London");
        airplanes[1].setDestination("Paris");
        airplanes[2].setDestination("Rome");
        airplanes[3].setDestination("Miami");
        // initiate an an array with 4 planes and destinations.
        return airplanes;
    }

    public int chooseADestination(Airplane[] airplanes) { // returns London = 0, Paris = 1, Rome = 2 and Miami = 3
        System.out.println();
        System.out.println("Please choose flight destination among the list as below:");
        System.out.println("Please type 1, 2, 3 or 4 as your wished destination and then press <Enter>");
        String dest;
        do {
            System.out.println("1- London 2- Paris 3- Rome 4- Miami");
            dest = sc.nextLine();
        } while (!dest.equalsIgnoreCase("1")
                && !dest.equalsIgnoreCase("2")
                && !dest.equalsIgnoreCase("3")
                && !dest.equalsIgnoreCase("4"));
        switch (dest) {
            case "1":
                return 0;
            case "2":
                return 1;
            case "3":
                return 2;
            case "4":
                return 3;
        }
        return 4; // wrong destination
    }

    public Seat createASeat(int businessOrEconomiSeatsNo, int noOfPlane,
            int[][] availableBusinussSeats, int[][] availableEconomySeats) {

        Seat seat = new Seat(businessOrEconomiSeatsNo);
        seat.setBooked(true);
        if (businessOrEconomiSeatsNo < 6) {
            seat.setClassStatus(ClassType.BUSINESS);
            availableBusinussSeats[noOfPlane][businessOrEconomiSeatsNo - 1] = 0;
        } else {
            seat.setClassStatus(ClassType.ECONOMY);
            availableEconomySeats[noOfPlane][businessOrEconomiSeatsNo - 5 - 1] = 0;
        }
        return seat;
    }

    public int chooseFlightClass() {
        String businessClass = "1";
        String economyClass = "2";
        String businessOrEconomiToString = null;
        System.out.println("\nPlease type 1 for booking Business class or 2 for Economy class and then press <Enter>");
        System.out.println("1- Business class costs 20 000 SEK");
        System.out.println("2- Economy class costs 5 000 SEK");
        do {
            businessOrEconomiToString = sc.nextLine();
        } while (!businessOrEconomiToString.equals(businessClass)
                && !businessOrEconomiToString.equals(economyClass));
        return Integer.parseUnsignedInt(businessOrEconomiToString);
    }

    public int orderFood(Seat seat) {
        String yesOrNoOrderFood;
        do {
            System.out.println("If you would like to order food and drink to have onboard, please type YES and then press <Enter> "
                    + "otherwise type NO and then press <Enter>");
            yesOrNoOrderFood = sc.nextLine();
        } while (!yesOrNoOrderFood.equalsIgnoreCase("Yes")
                && !yesOrNoOrderFood.equalsIgnoreCase("No"));
        if (yesOrNoOrderFood.equalsIgnoreCase("No")) {
            return 0;
        }
        System.out.println();
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
                System.out.println("Please type your desired amount of this food item (max 3) and then press <Enter>, otherwise type 0 and then press <Enter>");
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
                //likadant här som i business men "i + 5" istället för i
                for (FoodItem foodItem : foodAmountStorage.foodStorage) {
                    if (foodItem.getCustomerClass().equals(ClassType.BUSINESS)) {
                        System.out.println(++nextRowInFoodList + "- " + foodItem);
                    }
                }
                System.out.println("Please type your desired amount of this food item (max 3) and then ptrss <Enter>, "
                        + "otherwise type 0 and then press <Enter>");
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

    public int chooseASeat(Airplane[] airplanes, int businessOrEconomi, int noOfPlane,
            int[][] availableBusinussSeats, int[][] availableEconomySeats) {
        String businessOrEconomiSeatsNo = null;
        int[] zeroArray = {0, 0, 0, 0, 0};
        if (businessOrEconomi == 1 && Arrays.equals(availableBusinussSeats[noOfPlane], zeroArray)) {
            System.out.println("Unfortunately the Business class seats are already full booked.");
            String yesOrNoToBookEconomy;
            do {
                System.out.println();
                System.out.println("If you would like to book a Economy class instead type 'YES' and then press <Enter> "
                        + "Otherwise type 'No' and then press <Enter>");
                yesOrNoToBookEconomy = sc.nextLine();
            } while (!yesOrNoToBookEconomy.equalsIgnoreCase("yes")
                    && !yesOrNoToBookEconomy.equalsIgnoreCase("no"));
            if (yesOrNoToBookEconomy.equalsIgnoreCase("yes")
                    && !Arrays.equals(availableEconomySeats[noOfPlane], zeroArray)) {
                businessOrEconomi = 2;
            } else if (yesOrNoToBookEconomy.equalsIgnoreCase("yes")
                    && Arrays.equals(availableEconomySeats[noOfPlane], zeroArray)) {
                System.out.println("\nUnfortunately both the Business and Economy class seats are already full booked for destination: "
                        + airplanes[noOfPlane].getDestination());
                System.out.println("If you wish to book a flight to an other destination so please wait in this queue.\n");
                return 11;  // both the Business and Economy class seats are already full booked.
            } else {
                System.out.println("You have repented booking flights.");
                return 0;
            }
        } else if (businessOrEconomi == 2 && Arrays.equals(availableEconomySeats[noOfPlane], zeroArray)) {
            System.out.println("Unfortunately the Economy class seats are already full booked.");
            String yesOrNoToBookEconomy;
            do {
                System.out.println("If you would like to book a Business class in stead type 'YES' and press <Enter>, Otherwise type 'No' and then press <Enter>");
                yesOrNoToBookEconomy = sc.nextLine();
            } while (!yesOrNoToBookEconomy.equalsIgnoreCase("yes")
                    && !yesOrNoToBookEconomy.equalsIgnoreCase("no"));
            if (yesOrNoToBookEconomy.equalsIgnoreCase("yes")
                    && !Arrays.equals(availableBusinussSeats[noOfPlane], zeroArray)) {
                businessOrEconomi = 1;
            } else if (yesOrNoToBookEconomy.equalsIgnoreCase("yes")
                    && Arrays.equals(availableBusinussSeats[noOfPlane], zeroArray)) {
                System.out.println("\nUnfortunately both the Business and Economy class seats are already full booked for destination: "
                        + airplanes[noOfPlane].getDestination());
                System.out.println("If you wish to book a flight to an other destination so please wait in this queue.\n");
//                airplane.readyToFly = true;
                return 11;  // both the Business and Economy class seats are already full booked.
            } else {
                System.out.println("You have repented booking flights.");
                return 0;
            }
        }
        switch (businessOrEconomi) {
            case 1:
                do {
                    System.out.println("The Business class seats no are 1, 2, 3, 4 and 5.");
                    System.out.println("Now you are able to choose among the available one/ones as below. So type your favorit one and then press <Enter>");
                    for (int i = 0; i < 5; i++) {
                        if (availableBusinussSeats[noOfPlane][i] != 0) {
                            System.out.print(availableBusinussSeats[noOfPlane][i] + ", ");
                        }
                    }
                    System.out.println("So type your favourite one and then press <Enter>");
                    businessOrEconomiSeatsNo = sc.nextLine();
                } while (!businessOrEconomiSeatsNo.equals(Integer.toString(availableBusinussSeats[noOfPlane][0]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableBusinussSeats[noOfPlane][1]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableBusinussSeats[noOfPlane][2]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableBusinussSeats[noOfPlane][3]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableBusinussSeats[noOfPlane][4])));
                break;
            case 2:
                do {
                    System.out.print("The Economy class seats no are 6, 7, 8, 9 and 10.");
                    System.out.println("Now you are able to choose among the available one/ones as below."
                            + " So type your favourite one and then press <Enter>");
                    for (int i = 0; i < 5; i++) {
                        if (availableEconomySeats[noOfPlane][i] != 0) {
                            System.out.print(availableEconomySeats[noOfPlane][i] + ", ");
                        }
                    }
                    System.out.println("So type your favourite one and then press <Enter>");
                    businessOrEconomiSeatsNo = sc.nextLine();
                } while (!businessOrEconomiSeatsNo.equals(Integer.toString(availableEconomySeats[noOfPlane][0]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableEconomySeats[noOfPlane][1]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableEconomySeats[noOfPlane][2]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableEconomySeats[noOfPlane][3]))
                        && !businessOrEconomiSeatsNo.equals(Integer.toString(availableEconomySeats[noOfPlane][4])));
                break;
        }
        return Integer.parseUnsignedInt(businessOrEconomiSeatsNo);
    }

    public Airplane[] doPrintTicket(Airplane[] airPlanes, int noOfPlane, Seat seat, int foodCosts) {

        System.out.println("\nPlease type your firstname");
        String firstName;
        do {
            firstName = sc.nextLine();
        } while (firstName.isEmpty());
        System.out.println("\nPlease type your surname");
        String surname;
        do {
            surname = sc.nextLine();
        } while (surname.isEmpty());
        System.out.println("\nPlease type your age between 1-100");
        String age;
        do {
            age = sc.nextLine();
        } while (!isInteger(age));

        System.out.println("\nPASSENGER TICKET INFORMATION:");
        System.out.println("Welcome onboard dear " + firstName + " " + surname + ", " + age);

        System.out.println("Your destination is " + airPlanes[noOfPlane].getDestination()
                + ". You booked " + seat.getClassStatus()
                + " class and your seat nnmber is " + seat.getSeatNumber());
        System.out.println("You ordered food and drink for " + foodCosts + " SEK");
        int totalTicketPrice;
        if (seat.getClassStatus().equals(ClassType.BUSINESS)) {
            totalTicketPrice = foodCosts + 20000;
            airPlanes[noOfPlane].setBusinessSeats(airPlanes[noOfPlane].getBusinessSeats() - 1);
        } else {
            totalTicketPrice = foodCosts + 5000;
            airPlanes[noOfPlane].setEconomiSeats(airPlanes[noOfPlane].getEconomiSeats() - 1);
        }
        System.out.println("Your total ticket price is " + totalTicketPrice + " SEK");
        airPlanes[noOfPlane].totalTicketSales += totalTicketPrice;

        Passenger p = new Passenger(firstName, surname, totalTicketPrice, seat.getSeatNumber(),
                airPlanes[noOfPlane].getDestination(), Integer.parseUnsignedInt(age));
        seat.setPassenger(p);
        airPlanes[noOfPlane].addSeatAndPass(seat, p);
        return airPlanes;
    }

    public boolean readyToFly(Airplane[] airplanes) {

        System.out.println("\nPlease check if there are more travelers in queue to book flight. Are there? enter YES,"
                + " otherwise enter No and then press Enter.");
        String yesOrNoToBookFlight;
        do {
            yesOrNoToBookFlight = sc.nextLine();
        } while (!yesOrNoToBookFlight.equalsIgnoreCase("YES")
                && !yesOrNoToBookFlight.equalsIgnoreCase("NO"));
        for (int i = 0; i < airplanes.length; i++) {
            if ((airplanes[i].getBusinessSeats() == 0 && airplanes[i].getEconomiSeats() == 0)
                    || yesOrNoToBookFlight.equalsIgnoreCase("No")) {
                System.out.println("Please check in and go onboard.");
                System.out.println("Now this plane is ready to fly to " + airplanes[i].getDestination() + ".\n");
                //airplanes[i].readyToFly = true;//la Jag(Jimmy) till
                //Jag(Jimmy) kommenterar bort denna loop för det är nog den som gör att samma läggs till i 4 plan
                for (Airplane next : airplanes) {
                    if (next.getBusinessSeats() < 5 || next.getEconomiSeats() < 5) {
                        next.readyToFly = true;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void printPassengersListAndTotalIncome(Airplane[] airplanes) {
        int JMAirtotalTicketSales = 0;
        for (int i = 0; i < airplanes.length; i++) {
            if (airplanes[i].totalTicketSales != 0) {
                System.out.println("The Passengers List And Total Income of the flight to " + airplanes[i].getDestination() + " :");

                /*for (Seat next : airplanes[i].getSeats()) {
                    if (!next.equals(null)) {
                        System.out.println(next);
                    }
                }*/
                System.out.println("\nThe total ticket sales is " + airplanes[i].totalTicketSales + " SEK");
                System.out.println("\nThe total profit for JMairline " + airplanes[i].getDestination() + " flight is " + (int) (airplanes[i].totalTicketSales * 0.3) + " SEK\n");
                JMAirtotalTicketSales += (int) airplanes[i].totalTicketSales * 0.3;
            }


            System.out.println("\nThe total ticket sales is " + airplanes[i].totalTicketSales + " SEK");
            System.out.println("\nThe total profit for JMairline " + airplanes[i].getDestination() + " flight is " + (int) (airplanes[i].totalTicketSales * 0.3) + " SEK\n");
            JMAirtotalTicketSales += (int) airplanes[i].totalTicketSales * 0.3;
        }
        System.out.println("\nThe total profit for all JMairline flights is " + JMAirtotalTicketSales + " SEK");
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        if (Integer.parseInt(s) > 0 && Integer.parseInt(s) < 101) {
            return true;
        }
        return false;
    }
}
