import java.util.*;
import java.io.*;

public class Journey extends City {
    int source[];
    int dest[];
    int cl[];
    TreeSet<Cab> cabs = new TreeSet<>(); 
    Thread t;


    //Calculates distance between source and destination
	int distance() {
		return Math.abs(source[0]- dest[0]) + Math.abs(source[1] - dest[1]);
	}

	//function to add available cabs from the database to the TreeSet and display them
	//appropriate message is displayed if no cab is available at the moment
    void addCabs(){
		File file = new File("./../data/drivers.txt"); 
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String driverName = null;
		String regisNo = null;
		String userName = null;
		double rating = 0.0;
		int numRated = 0;
		int cabLocation[] = new int[2];
		Cab cabObj = null;
		while(sc.hasNextLine()){
			
            String s = sc.nextLine();
            StringTokenizer st = new StringTokenizer(s);
            int l = st.countTokens();
			if(l < 8) {
				for(int i = 0; i < l; i++) {
					String detail = st.nextToken();
					if(i == 0)
						userName = detail;
					if(i == 2)
						driverName = detail;
					if(i == 3)
						rating = Double.parseDouble(detail);
					if(i == 4)
						numRated = Integer.parseInt(detail);
					if(i == 5)
						regisNo = detail;
					if(i == 6) {
						cabLocation[0] = Integer.parseInt(detail.substring(0, detail.indexOf('_')));
						cabLocation[1] = Integer.parseInt(detail.substring(detail.indexOf('_')+1));
					}
				}
				cabObj = new Cab(regisNo, new Driver(userName, driverName, rating, numRated), new int[] {cabLocation[0], cabLocation[1]}, source);
				cabs.add(cabObj);
			}
        }

        sc.close();
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        };
    	
		int numCabs = cabs.size();
		if(numCabs == 0) {
			System.out.println("Sorry, no cabs available at the moment");
			return;
		}
        System.out.println("Choose from the following available cabs: ");
		int i = 1;
        for (Cab c:cabs) {
			c.calcFare(distance(), numCabs);
			String dname = c.driverDetails.getName();
			String fdname = dname.substring(0, dname.indexOf("_"));
			String ldname = dname.substring(dname.indexOf("_")+1);
        	System.out.println(i + ". " + "Registration no.: " + c.regNum + ", Driver's Name: " + fdname + " " + ldname + ", Distance from you: " + c.distance(source) + ", Location: (" + c.location[0] + ", " + c.location[1] + "), Fare charged: " + (int)c.fare + ", Rating: " + String.format("%.2f", c.driverDetails.rating));
        	i++;
        }
		cl = new int[2];
    }

	//parameterized constructor
    Journey(int l1[], int l2[]){
        source = l1;
        dest = l2; 
		cl = new int[2];
		
    }
    
	//function to display dynamically the current positions of cab and customer
    void travel(int[] initialCoord, int[] finalCoord, char customerSeated) {
    	
    	t = new Thread() {
    		public void run() {
				int dist = Math.abs(initialCoord[0] - finalCoord[0]) + Math.abs(initialCoord[1] - finalCoord[1]);
				int timeInSec = eta(dist);
    			int i = initialCoord[0]; 
    			while(i-finalCoord[0] != 0) {
        			grid[i][initialCoord[1]] = customerSeated;
					if(timeInSec >= 10)
						System.out.println("ETA: " + timeInSec + " sec");
					else
						System.out.println("ETA: 0" + timeInSec + " sec");

					timeInSec--;
        			displayGrid();
        			try {
        				Thread.sleep(1000);
        			} catch(InterruptedException e) {
        				e.printStackTrace();
        			}
        			int lmflag = 0;
    				for(Landmark lk: lmk) {
    					if(i == lk.location[0] && initialCoord[1] == lk.location[1]) {
    						grid[i][initialCoord[1]] = lk.id;
    						lmflag = 1;
    						break;
    					}
    				}
    				if(lmflag == 0) {
    					grid[i][initialCoord[1]] = '0';
    				}
    		    	deleteGrid();
					System.out.print("\033[1A");
    				
    				if(initialCoord[0]-finalCoord[0]>0)
    					i--;
    				else
    					i++;
    				
    			
    			}

				int j = initialCoord[1];
    			while(j-finalCoord[1] != 0) {
        			grid[i][j] = customerSeated;
					if(timeInSec >= 10)
						System.out.println("ETA: " + timeInSec + " sec");
					else
						System.out.println("ETA: 0" + timeInSec + " sec");
					timeInSec--;
        			displayGrid();
        			try {
        				Thread.sleep(1000);
        			} catch(InterruptedException e) {
        				e.printStackTrace();
        			}
					int lmflag = 0;
    				for(Landmark lk: lmk) {
    					if(i == lk.location[0] && j == lk.location[1]) {
    						grid[i][j] = lk.id;
							lmflag = 1;
    						break;
    					}
    				}
					if(lmflag == 0) {
    					grid[i][j] = '0';
    				}
    		    	deleteGrid();
					System.out.print("\033[1A");
    				
    				if(initialCoord[1]-finalCoord[1]>0)
    					j--;
    				else
    					j++;
    					
    			}

				grid[i][j] = customerSeated;
				System.out.println("Cab reached");
				displayGrid();
    			
    		};
    	
    	};
    	t.start();

	}

	// 36 seconds in real life is shown to be equivalent to 1 second for this project.
	//distance is input in km
	int eta(int distance) {
		return distance;
	}

}
