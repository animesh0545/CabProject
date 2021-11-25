import java.util.*;

public class City {
    
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

    City(){
        grid = new char[15][15];
        for(char[] arr : grid) 
            Arrays.fill(arr, '0');
        addLandmarks();
        for (Landmark l:lmk){
            grid[l.location[0]][l.location[1]] = l.id;
        }
        // displayGrid();
        
    }
    
    void displayGrid(){
    	for (int i = 0; i < 15; i++) {
        	for (int j = 0; j < 15; j++)
        		System.out.print(grid[i][j] + " ");
        	System.out.println();
        }
        System.out.println();
    }
    void deleteGrid(){
    	for (int j = 0; j <= 15; j++) {
        	System.out.print("\033[1A");
    	}
    }

    int[] getLandmarkLocation(String s){
        for (Landmark l : lmk) {
            if (s.equals(l.name))
                return l.location;
        }
        return new int[] {-1, -1};
    }


    int[] getLandmarkLocationFromID(char c){
        for (Landmark l : lmk) {
            if (c == l.id)
                return l.location;
        }
        return new int[] {-1, -1};
    }

    String getLandmarkNameFromID(char c){
        for (Landmark l : lmk) {
            if (c == l.id)
                return l.name;
        }
        return null;
    }
    
}
