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
        System.out.println(l2[0] + " " + l2[1]);
        Journey j = new Journey(l1, l2);
        j.addCabs();
        //j.travel();
        sc.close();
        

    }
}
