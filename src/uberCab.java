import java.util.*;
public class uberCab {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n=1;
        do{
            System.out.println("Enter 1 for Customer or 2 for Driver");
            n = sc.nextInt();
            //System.out.println(n);
        }while (n != 1 && n != 2);

        Customer customer = null;

        if (n == 1){

            int m = 0;

            do{
                System.out.println("Enter 1 for SignUp and 2 for Login");
                m = sc.nextInt();

            }while (m != 1 && m != 2);

            System.out.print("Enter your username:\t");
            String username = sc.next();
            System.out.print("Enter password\t");
            String password = sc.next();
            System.out.println(username + "\t" + password);

            if (m == 1){
                System.out.println("Enter your name");
                String cname = sc.next();
                customer = new Customer(cname, username, password);
            }
            else
                customer = new Customer(username, password);

                 
            }
        
        else{
            //TODO
        }
        
        int l1[] = customer.getLocation();
        System.out.println("Enter landmark");
        String lm = sc.next();
        
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