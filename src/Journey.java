
//import java.util.Comparator;
import java.util.*;
import java.io.*;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;

import java.util.TreeSet;

public class Journey extends City {
    int source[];
    int dest[];
    int cl[];
	double fare;

    TreeSet<Cab> cabs = new TreeSet<>();
    
    Thread t;
    
	int distance() {
		return Math.abs(source[0]- dest[0]) + Math.abs(source[1] - dest[1]);
	}

    void addCabs(){
		File file = new File("./../data/drivers.txt"); 
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
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
			
            //System.out.println("Im here");
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
    	
    	// Cab c1 = new Cab("001", new Driver("D1"), new int[] {0, 3}, source);
        // Cab c2 = new Cab("002", new Driver("D2"), new int[] {1, 0}, source);
        // Cab c3 = new Cab("003", new Driver("D3"), new int[] {3, 3}, source);
        // Cab c4 = new Cab("004", new Driver("D4"), new int[] {4, 1}, source);
        // Cab c5 = new Cab("005", new Driver("D5"), new int[] {2, 4}, source);
        // cabs.add(c1);
        // cabs.add(c2);
        // cabs.add(c3);
        // cabs.add(c4);
        // cabs.add(c5);
        System.out.println("Choose from the following available cabs: ");
		int i = 1;
        for (Cab c:cabs) {
        	System.out.println(i + ". " + "Registration no.: " + c.regNum + ", Driver's Name: " + c.driverDetails.getName() + ", Distance from you: " + c.distance(source) + ", Location: (" + c.location[0] + ", " + c.location[1] + "), Rating: " + c.driverDetails.rating);
        	i++;
        }
		cl = new int[2];
        // cl = cabs.first().location;
    }
    Journey(int l1[], int l2[]){
        source = l1;
        dest = l2; 
		cl = new int[2];
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
   		LocalDateTime now = LocalDateTime.now();  
		String currentDateTime = dtf.format(now);
		int hh = Integer.parseInt(currentDateTime.substring(currentDateTime.indexOf(" ")+1, currentDateTime.indexOf(":")));
		int factor;
		if(hh >= 8 && hh < 21)
			factor = 100;
		else
			factor = 70;
		fare = factor*distance();
        //addCabs();
        
    }
	// @Override
	// void displayGrid() {

	// }
    
    void travel(int[] initialCoord, int[] finalCoord, int customerSeated) {
    	
    	t = new Thread() {
    		public void run() {
				int dist = Math.abs(initialCoord[0] - finalCoord[0]) + Math.abs(initialCoord[1] - finalCoord[1]);
				int timeInSec = eta(dist);
    			int i = initialCoord[0]; 
    			while(i-finalCoord[0] != 0) {
        			grid[i][initialCoord[1]] = customerSeated;
					// eta(distance());
					System.out.println("ETA: " + timeInSec + " sec");
					timeInSec--;
        			displayGrid();
        			try {
        				Thread.sleep(2000);
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
    					grid[i][initialCoord[1]] = 0;
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
					// System.out.println("ETA: " + )
					System.out.println("ETA: " + timeInSec + " sec");
					timeInSec--;
        			displayGrid();
        			try {
        				Thread.sleep(2000);
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
    					grid[i][j] = 0;
    				}
    		    	deleteGrid();
					System.out.print("\033[1A");
    				
    				if(initialCoord[1]-finalCoord[1]>0)
    					j--;
    				else
    					j++;
    					
    		}

			grid[i][j] = 9;
			System.out.println("Cab is here");
			displayGrid();
    			
    	};
    	
    };


    	t.start();
}

// 36 seconds in real life is shown to be equivalent to 1 seconds for this project.
//distance is input in km
int eta(int distance) {
	return distance;
        // t = new Thread() {
            // public void run() {
                //speed is in kmps
                // int speed = 50;
                // double time = (double)distance/speed;
                // int timeInSec = (int)(time*60*60);
                // timeInSec /= 72;
				// return timeInSec;
                // for(int i = timeInSec; i > 0; i--) {
                //     if(i < 10) {
                //             System.out.print(i + " sec");
                //             System.out.print("\b\b\b\b\b");
                //         }
                //         else if(i >= 10 && i <= 99) {
                //             System.out.print(i + " sec");
                //             System.out.print("\b\b\b\b\b\b");
                //         }
                //         else if(i >= 100 && i <= 999) {
                //             System.out.print(i + " sec");
                //             System.out.print("\b\b\b\b\b\b\b");
                //         }
                //         else {
                //             System.out.print(i + " sec");
                //             System.out.print("\b\b\b\b\b\b\b\b");
                //         }
                //         try	{
                //             Thread.sleep(1000);
                //         } catch(Exception e) {
                //             e.printStackTrace();
                //         }
                // }
               
                // int hr = timeInSec/3600;
                // timeInSec %= 3600;
                // int min = timeInSec/60;
                // timeInSec %= 60;
                // int sec = timeInSec;
                // while(!(hr == 0 && min == 0 && sec == 0)) {
                // 	if(hr != 0) {
                // 		if(hr < 10) {
                // 			System.out.print(hr + " hr");
                // 			System.out.print("\b\b\b\b");
                // 		}
                // 	}
                // }
            // }
        // };
    
        // t.start();
    }


}


// class SortByDistance implements Comparator<Cab>{
//     public int compare(Cab a, Cab b){
//         return a.distance() - b.distance();
//     }
// }

