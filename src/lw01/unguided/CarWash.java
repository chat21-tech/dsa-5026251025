package lw01.unguided;

public class CarWash extends WashService {
 
    public CarWash(String id, int days, int units) {
        super(id, days, units);
    }
 
    public int calculateCharge() {
        int days = getDays();
        int dayCost;
        if (days <= 3) {
            dayCost = days * 35000;
        } else {
            dayCost = 3 * 35000 + (days - 3) * 25000;
        }
        return dayCost + 15000;
    }

    public String label() {
        return "Car";
    }
}
