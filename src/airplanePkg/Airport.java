package airplanePkg;

import servicesPkg.FlyingPrintClass;

import java.util.Timer;

/**
 * Created by Jimmy on 2016-10-31.
 * Updated 16th nov
 */
public class Airport {
    Airplane[] plane = new Airplane[4];
    String name = "Arlanda";
    String airportCode = "ARN";
    Timer timer = new Timer();


    public Airport(){
        startPlanes("", true);
    }

    public Airport(String dest){
        startPlanes(dest, true);
    }

    public Airport(Airplane[] planes){
        for (Airplane plane: planes) {
            startPlanes(plane.getDestination(), false);//plane
        }
    }

    public void startPlanes(String destinationAlreadyUsed, boolean startOtherPlanes){
        if (destinationAlreadyUsed.equals("") == true){//if a new airport is created without any argument
            for(int i = 0; i < 4; i++){
                if (i == 0) {
                    plane[i] = new Airplane("Rome");
                }
                else if (i == 1){
                    plane[i] = new Airplane("Paris");
                }
                else if (i == 2){
                    plane[i] = new Airplane("London");
                    plane[i].fillPassengersWithRealPeople();
                }
                else if (i == 3){
                    plane[i] = new Airplane("Miami");
                }

                Thread planeThread = new Thread(plane[i]);
                System.out.println();
                plane[i].printPassengerList();
                planeThread.start();
            }
        }

        if(startOtherPlanes == false){ //takeoff one plane
            Airplane p = new Airplane(destinationAlreadyUsed);
            Thread planeThread = new Thread(p);
            System.out.println();
            p.printPassengerList();
            planeThread.start();
        }
        else{//takeoff all 4 planes
            FlyingPrintClass fpc = new FlyingPrintClass(destinationAlreadyUsed);
            for(int i = 0; i < 4; i++){
                boolean takeOffPlane = true;
                if (i == 0 & destinationAlreadyUsed.equalsIgnoreCase("Rome") == false) {
                    plane[i] = new Airplane("Rome");
                }
                else if (i == 1 & destinationAlreadyUsed.equalsIgnoreCase("Paris") == false) {
                    plane[i] = new Airplane("Paris");
                }
                else if (i == 2 & destinationAlreadyUsed.equalsIgnoreCase("London") == false) {
                    plane[i] = new Airplane("London");
                    plane[i].fillPassengersWithRealPeople();
                }
                else if (i == 3 & destinationAlreadyUsed.equalsIgnoreCase("Miami") == false) {
                    plane[i] = new Airplane("Miami");
                }
                else {
                    takeOffPlane = false;
                }

                if(takeOffPlane == true){
                    Thread planeThread = new Thread(plane[i]);
                    System.out.println();
                    plane[i].printPassengerList();
                    if (i > 0){
                        if (plane[i-1].isInTheAir() == true) {
                            planeThread.start();
                            //System.out.println("Plane " + plane[i-1] + " is in the air? " + plane[i-1].isInTheAir());
                        }
                    }
                    for (int j = 0; j <= i; j++) {
                        System.out.println("Plane " + plane[j] + " is in the air? " + plane[j].isInTheAir());
                        timer.schedule(fpc, 0, 1000);//bara en gång
                        timer.cancel();//när den kört 47 s från start
                    }
                    //planeThread.start();
                }
            }
        }
    }
}
