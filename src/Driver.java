public class Driver {
    String name;
    double rating;
    private int num;

    Driver(String n){
        name = n;
        rating = 0.0;
        num = 0;
    }
    
    Driver(String n, double d){
    	name = n;
    	rating = d;
    	num = 1;
    }

    void updateRating(double r){
        rating = (rating * num + r) / ++num;
    }

}
