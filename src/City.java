import java.util.*;

public class City {
    
    //data members of city class
    ArrayList<Landmark> lmk = new ArrayList<>();
    Landmark l1 = new Landmark("FD=III", new int[] {1, 2}, '1');
    Landmark l2 = new Landmark("Mal-A Bhawan", new int[] {10, 2}, '2');
    Landmark l3 = new Landmark("Ram Bhawan", new int[] {14, 10}, '3');
    Landmark l4 = new Landmark("Krishna Bhawan", new int[] {5, 9}, '4');
    Landmark l5 = new Landmark("Shankar Bhawan", new int[] {7, 3}, '5');
    Landmark l6 = new Landmark("SR Bhawan", new int[] {3, 14}, '6');
    Landmark l7 = new Landmark("FD-II", new int[] {0, 8}, '7');
    Landmark l8 = new Landmark("LTC", new int[] {13, 12}, '8');
    Landmark l9 = new Landmark("FD-I", new int[] {9, 1}, '9');

    //function to add landmarks to the ArrayList
    void addLandmarks(){
        lmk.add(l1);
        lmk.add(l2);
        lmk.add(l3);
        lmk.add(l4);
        lmk.add(l5);
        lmk.add(l6);
        lmk.add(l7);
        lmk.add(l8);
        lmk.add(l9);
    }

    char grid[][];

    //constructor
    public City(){
        grid = new char[15][15];
        for(char[] arr : grid) 
            Arrays.fill(arr, '0');
        addLandmarks();
        for (Landmark l:lmk){
            grid[l.location[0]][l.location[1]] = l.id;
        }
        
    }
    
    //function to display the city grid
    void displayGrid(){
    	for (int i = 0; i < 15; i++) {
        	for (int j = 0; j < 15; j++)
        		System.out.print(grid[i][j] + " ");
        	System.out.println();
        }
        System.out.println();
    }

    //function to remove the city grid from display
    void deleteGrid(){
    	for (int j = 0; j <= 15; j++) {
        	System.out.print("\033[1A");
    	}
    }

    //function to get landmark location from landmark name
    int[] getLandmarkLocation(String s){
        for (Landmark l : lmk) {
            if (s.equals(l.name))
                return l.location;
        }
        return new int[] {-1, -1};
    }

    //function to get landmark location from landmark ID
    int[] getLandmarkLocationFromID(char c){
        for (Landmark l : lmk) {
            if (c == l.id)
                return l.location;
        }
        return new int[] {-1, -1};
    }

    //function to get landmark name from landmark ID
    String getLandmarkNameFromID(char c){
        for (Landmark l : lmk) {
            if (c == l.id)
                return l.name;
        }
        return null;
    }
    
}
