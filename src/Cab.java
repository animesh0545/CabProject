public class Cab implements Comparable<Cab>{
    String regNum;
    Driver driverDetails;
    int location[];
    int source[];
    //Speed is stored in kmph
    int speed;
    int status;



    Cab(String r, Driver dr, int l[], int s[]){
        regNum = r;
        driverDetails = dr;
        location = l;
        source = s;
        speed = 50;
        status = 1;
    }

    void changeStatus() {
        status  = 0;
    }

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
