import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;



public class Cab implements Comparable<Cab>{
    String regNum;
    Driver driverDetails;
    int location[];
    int source[];
    double fare;


    Cab(String r, Driver dr, int l[], int s[]){
        regNum = r;
        driverDetails = dr;
        location = l;
        source = s;
        fare = 0;
        
    }
    void calcFare(int dist, int numCabs) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now();  
        String currentDateTime = dtf.format(now);
        int hh = Integer.parseInt(currentDateTime.substring(currentDateTime.indexOf(" ")+1, currentDateTime.indexOf(":")));
        double factor;
        if(hh >= 8 && hh < 21)
            factor = 100;
        else
            factor = 70;
        dist = dist+distance(source);
        fare = factor*dist/numCabs;
    }

    //Calculates distance between cab location and source
    int distance(int source[]) {
        int x = Math.abs(source[0] - location[0]);
        int y = Math.abs(source[1] - location[1]);

        return x + y;
    }

    @Override
    public int compareTo(Cab c) {
    	int x = this.distance(source) - c.distance(source);
    	if (x != 0)
    		return x;
    	
		int y = (int)(c.driverDetails.rating - this.driverDetails.rating);
		if (y != 0)
			return y;
		
		return this.regNum.compareTo(c.regNum);
    	
    		 
    }
    
   


}
