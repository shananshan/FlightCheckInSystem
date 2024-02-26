import java.lang.reflect.Array;

public class Luggage {
    Array size;
    float weight;
    float personalWeightLimit;
    Array personalSizeLimit;

    float overageFee;

    int quantity;

    boolean luggageCheckOrNot;

    float sizeInput(float a, float b, float c){
    	float size = a*b*c;
        return size;
    };

    float overageFeeCalculation(float a) {
    	
        return 0.0F;
    }

    boolean overageFeePayment(float a) {
        return false;
    }
}
