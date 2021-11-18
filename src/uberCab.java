import java.util.*;
public class uberCab {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name");
        String cname = sc.nextLine();
        System.out.println("Enter current location");
        int x = sc.nextInt();
        int y = sc.nextInt();
        int l1[] = {x, y};
        Customer c = new Customer(cname, l1);
        System.out.println("Enter landmark");
        String lm = sc.next();
        c.getName();
        City ct = new City();
        int l2[] = ct.getLandmark(lm);
        System.out.println("Landmark to reach: "+ lm + "[Coordinates: ("+l2[0] + "," + l2[1] + ")]");
        Journey j = new Journey(l1, l2);
        j.addCabs();
        System.out.print("Choose any one Cab to travel: ");
        int cabToTravel = sc.nextInt();
        ArrayList<Cab> cabList = new ArrayList<Cab>(j.cabs);
        j.cl[0] = cabList.get(cabToTravel-1).location[0];
        j.cl[1] = cabList.get(cabToTravel-1).location[1];
        // System.out.println("Cab is arriving...");
        j.travel(j.cl, j.source, 9);
        sc.close();
        

    }
}