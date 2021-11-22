import java.util.*;
import java.io.*;

public class uberCab {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 1 for Customer or 2 for Driver");
        int n;
        do{
            n = sc.nextInt();
        }while (n != 1 || n != 2);

        if (n == 1){
            File userFile = new File("/data/customers.txt");
            System.out.println("Welcome to Uber Cab booking system!");
            System.out.println("Enter 1 to login or 2 to signup");
            int m;
            do{
                m = sc.nextInt();
            } while (m != 1 || m != 2);
            if (m == 1){
                System.out.print("Enter your username:\t");
                String user = sc.next();
                System.out.print("Enter your password:\t");
                String pwd = sc.next();
                Customer cr = new Customer(n, l)
                 
            }
        }
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