package mainUI;

import static mainUI.JMAirFlightReservationUI.availableBusinussSeats;
import static mainUI.JMAirFlightReservationUI.availableEconomySeats;
import airplanePkg.Airplane;
import airplanePkg.Seat;

public class JMAirMain {    

public static void main(String[] args) {

        JMAirFlightReservationUI userInterface = new JMAirFlightReservationUI();
        Airplane[] airplanes = userInterface.welcomeToJMAirLine();

         // The planes are ready to fly when you booked for 5 seats Business and 5 Economy for each, 
         // or there are not more customers in the queue.
            while ((!airplanes[0].isReadyToFly())
                    && (!airplanes[1].isReadyToFly())
                    && (!airplanes[2].isReadyToFly())
                    && (!airplanes[3].isReadyToFly())) {
                
                int noOfPlane = userInterface.chooseADestination(airplanes); // Return 1, 2,3 or 4 for different destinations.
                
                int businessOrEconomi = userInterface.chooseFlightClass(); // Return 1 as BUSINESS or 2 as ECONOMY.
                
                // Return BUSINUSS or ECONOMY Seats number 1-10.
                int businessOrEconomiSeatsNo = userInterface.chooseASeat(airplanes, businessOrEconomi, noOfPlane,
                        availableBusinussSeats, availableEconomySeats);
                
                if (businessOrEconomiSeatsNo != 0 && businessOrEconomiSeatsNo != 11) {
                    // seat no = 0 The customer has repented booking flights.                
                    // seat no = 11 = Both the Business and Economy class seats are already full booked.
                    
                    Seat seat = userInterface.createASeat(businessOrEconomiSeatsNo, noOfPlane,
                            availableBusinussSeats, availableEconomySeats);                   
                    // Order the food to have onboard.
                    int foodCosts = userInterface.orderFood(seat);
                    // Print out the ticket.
                   airplanes = userInterface.doPrintTicket(airplanes, noOfPlane, seat, foodCosts);
                }
                boolean readyToFly = userInterface.readyToFly(airplanes);
                
                if(readyToFly) {
                    for (Airplane next : airplanes) {
                        if(next.readyToFly == true){
                            Thread planeThread = new Thread(next);
                            planeThread.start();
                        }
                    }
                    userInterface.printPassengersListAndTotalIncome(airplanes);
                }
            }
    }
}
