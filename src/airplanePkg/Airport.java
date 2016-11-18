package airplanePkg;

/**
 * Created by Jimmy on 2016-10-31.
 */
public class Airport {//implements Runnable{//kanske inte runnable

    Airplane[] plane = new Airplane[4];
    String name = "Arlanda";
    String airportCode = "ARN";
// Masods constructor
//    public Airport(Airplane[] plane) {
//        this.plane = plane;
//    }

    public Airport() {
        startPlanes("");
    }

    public Airport(String dest) {
        startPlanes(dest);
    }

    public Airplane[] getPlane() {
        return plane;
    }

    public void setPlane(Airplane[] plane) {
        this.plane = plane;
    }

    public void startPlanes(String destinationAlreadyUsed) {
        if (destinationAlreadyUsed.equals("") == true) {

        }

        for (int i = 0; i < 4; i++) {
            boolean takeOffPlane = true;
            if (i == 0 & destinationAlreadyUsed.equals("Rome") == false) {
                plane[i] = new Airplane("Rome");
                //plane[i].setDestination("Rome");

                /*Thread planeThread = new Thread(plane[0]);
                System.out.println();
                plane[0].printPassengerList();
                //System.out.println();
                planeThread.start();*/
            } else if (i == 1 & destinationAlreadyUsed.equals("Paris") == false) {
                plane[i] = new Airplane("Paris");
                //plane[i].setDestination("Paris");
            } else if (i == 2 & destinationAlreadyUsed.equalsIgnoreCase("London") == false) {
                plane[i] = new Airplane("London");
                //plane[i].setDestination("London");
                plane[i].fillPassengersWithRealPeople();
            } else if (i == 3 & destinationAlreadyUsed.equals("Miami") == false) {
                plane[i] = new Airplane("Miami");
                //plane[i].setDestination("Miami");
            } else {
                takeOffPlane = false;
            }

            if (takeOffPlane == true) {
                Thread planeThread = new Thread(plane[i]);
                System.out.println();
                plane[i].printPassengerList();
                //System.out.println();
                planeThread.start();
            }
        }

        /*Thread planeThread2 = new Thread(plane[1]);
        System.out.println();
        plane[1].printPassengerList();
        //System.out.println();
        planeThread2.start();

        Thread planeThread3 = new Thread(plane[2]);
        System.out.println();
        plane[2].printPassengerList();
        //System.out.println();
        planeThread3.start();

        Thread planeThread4 = new Thread(plane[3]);
        System.out.println();
        plane[3].printPassengerList();
        System.out.println();
        planeThread4.start();*/
    }
    /*@Override
    public void run(){

    }*/
}
