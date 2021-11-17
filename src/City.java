import java.util.HashSet;

public class City {
    
    HashSet<Landmark> lmk = new HashSet<>();
    Landmark l1 = new Landmark("A", new int[] {1, 2}, 1);
    Landmark l2 = new Landmark("B", new int[] {2, 2}, 2);
    Landmark l3 = new Landmark("C", new int[] {3, 0}, 3);
    Landmark l4 = new Landmark("D", new int[] {4, 4}, 4);
    Landmark l5 = new Landmark("E", new int[] {0, 1}, 5);

    void addLandmarks(){
        lmk.add(l1);
        lmk.add(l2);
        lmk.add(l3);
        lmk.add(l4);
        lmk.add(l5);
        
    }

    int grid[][];

    City(){
        grid = new int[5][5];
        addLandmarks();
        for (Landmark l:lmk){
            grid[l.location[0]][l.location[1]] = l.id;
        }
        
    }
    
    void display(){
    	for (int i = 0; i < 5; i++) {
        	for (int j = 0; j < 5; j++)
        		System.out.print(grid[i][j] + " ");
        	System.out.println();
        }
    }

    int[] getLandmark(String s){
        for (Landmark l : lmk) {
            if (s.equals(l.name))
                return l.location;
        }
        return new int[] {-1, -1};
    }

    
}
