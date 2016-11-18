package servicesPkg;

import java.util.TimerTask;

/**
 * Created by Jimmy on 2016-11-17.
 */
public class FlyingPrintClass extends TimerTask {
    String destination = "";
    private String asteriskUtskrift = "";

    public FlyingPrintClass(){

    }

    public FlyingPrintClass(String dest){
        destination = dest;
    }

    public void run(){
        asteriskUtskrift = asteriskUtskrift + "*";
        System.out.println("Plane to " + destination + " is flying " + asteriskUtskrift);
    }
}
