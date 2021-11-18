

//import java.util.Comparator;

import java.util.TreeSet;

public class Journey extends City {
    int source[];
    int dest[];
    int cl[];

    TreeSet<Cab> cabs = new TreeSet<>();
    
    Thread t;
    

    void addCabs(){
    	
    	Cab c1 = new Cab("001", new Driver("A"), new int[] {0, 3}, source, 1);
        Cab c2 = new Cab("002", new Driver("B"), new int[] {1, 0}, source, 1);
        Cab c3 = new Cab("003", new Driver("C"), new int[] {3, 3}, source, 1);
        Cab c4 = new Cab("004", new Driver("D", 4.7), new int[] {4, 1}, source, 1);
        Cab c5 = new Cab("005", new Driver("E"), new int[] {2, 4}, source, 1);
        cabs.add(c1);
        cabs.add(c2);
        cabs.add(c3);
        cabs.add(c4);
        cabs.add(c5);
        
        for (Cab c:cabs) {
        	System.out.println(c.regNum + " " + c.location[0] + " " + c.location[1] + " " + c.source[0] + " " + c.source[1]);
        }
        cl = cabs.first().location;
    }
    Journey(int l1[], int l2[]){
        source = l1;
        dest = l2; 
        //addCabs();
        
    }
    
    // void travel() {
    	
    // 	t = new Thread() {
    // 		public void run() {
    // 			grid[cl[0]][cl[1]] = 9;
    // 			display();
    // 			while(!(cl[x] == l1[x] && cl[y] == l[y]))
    			
    // 		}
    // 	};
    	
    // 	t.start();
    
    	
    	
    // }


}


// class SortByDistance implements Comparator<Cab>{
//     public int compare(Cab a, Cab b){
//         return a.distance() - b.distance();
//     }
// }

