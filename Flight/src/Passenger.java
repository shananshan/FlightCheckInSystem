import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Passenger {
    String bookingRefCode;
    String name;
    String flightCode;
    boolean passCheckOrNot;

    String lastName;

    Passenger(){}
    Passenger(String [] args){
        bookingRefCode = args[0];
        name = args[1] + " " + args[2];
        lastName = args[2];
        flightCode = args[3];
        passCheckOrNot = Objects.equals(args[4], "TRUE");//数组第四个元素是否等于TRUE
    }

    String[] readNameAndCode() {
        return new String[0];
    }

    boolean findPass(String pass, String ch){
        return passCheckOrNot;
    }

    String getFlightCode() {
        return flightCode;
    }

    public String toString(){
        return bookingRefCode + "," + name + "," + flightCode + "," + (passCheckOrNot?"True":"False");
    }

    public boolean checkOne(String lastname, String flightcode){
        return Objects.equals(lastname, lastName) && Objects.equals(flightcode, bookingRefCode);
    }
}
