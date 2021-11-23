import java.util.*;
import java.io.*;
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
        Driver driver = null;

        if (n == 1){

            int m = 0;

            do{
                System.out.println("Enter 1 for SignUp and 2 for Login");
                m = sc.nextInt();

            }while (m != 1 && m != 2);

            System.out.print("Enter your username: ");
            String username = sc.next();
            System.out.print("Enter password: ");
            String password = sc.next();
            System.out.println(username + "\t" + password);

            if (m == 1){
                System.out.println("Enter your name");
                String cname = sc.next();
                customer = new Customer(cname, username, password);
            }
            else
                customer = new Customer(username, password);


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
                // for(int i = 0; i < cabList.size(); i++) {
                //     System.out.println(cabList.get(i).driverDetails.name);
                // }
                Driver d = cabList.get(cabToTravel-1).driverDetails;
                d.notification(l1[0] + "_" + l1[1]);
                d.notification(l2[0] + "_" + l2[1]);
                
                String token = "";
                outer: while(true) {
                    Scanner scan = null;
                    try {
                        scan = new Scanner(new File("./../data/drivers.txt"));
                        // System.out.println("**********************");
        
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    // System.out.println("**********************");
        
        
        
                    while(scan.hasNextLine()) {
                        String detail = scan.nextLine();
                        StringTokenizer st = new StringTokenizer(detail);
                        String uname = "";
                        if(st.countTokens() != 0)
                            uname = st.nextToken();
                        if(uname.equals(d.getUsername())) {
                            int l = st.countTokens();
                            if(l == 9) {
                                for(int i = 0; i < l; i++) {
                                    token = st.nextToken();
                                }
                                break outer;
                            }

                        }
                    }
                    scan.close();
                }
                // System.out.println("Cab is arriving...");

                if(token.equals("Y")) {
                    System.out.println("Cab is arriving...");
                    j.cl[0] = cabList.get(cabToTravel-1).location[0];
                    j.cl[1] = cabList.get(cabToTravel-1).location[1];
                    j.travel(j.cl, j.source, 9);
                    // int startTrip = 0;
                    // while(startTrip != 1) {
                    // System.out.println("Press 1 to start trip");
                    // startTrip = sc.nextInt();
                    // }

                }



                sc.close();
        }
        
        else{
            //TODO
            int m = 0;

            do{
                System.out.println("Enter 1 to Login");
                m = sc.nextInt();
                sc.nextLine();

            }while (m != 1);

            System.out.print("Enter your username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            System.out.println(username + "\t" + password);
            driver = new Driver(username, password);
            // System.out.println("**********************");
            
            Scanner scan = null;
            try {
                scan = new Scanner(new File("./../data/drivers.txt"));
                // System.out.println("**********************");

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // System.out.println("**********************");


            int coorCust[] = new int[2];
            int destCust[] = new int[2];
            int coorDriver[] = new int[2];
            int flag = 0;
            while(scan.hasNextLine()) {
                String detail = scan.nextLine();
                StringTokenizer st = new StringTokenizer(detail);
                String uName = "";
                if(st.countTokens() != 0)
                    uName = st.nextToken();
                int l = st.countTokens();
                
                if(uName.equals(username) && l >= 8) {
                    // System.out.println("**********************");
                    flag = 1;
                    for(int i = 0; i < l; i++) {
                        String s = st.nextToken();
                        if(i == l-3) {
                            coorDriver[0] = Integer.parseInt(s.substring(0, s.indexOf("_")));
                            coorDriver[1] = Integer.parseInt(s.substring(s.indexOf("_")+1));
                        }
                        if(i == l-2) {
                            coorCust[0] = Integer.parseInt(s.substring(0, s.indexOf("_")));
                            coorCust[1] = Integer.parseInt(s.substring(s.indexOf("_")+1));
                        }
                        if(i == l-1) {
                            destCust[0] = Integer.parseInt(s.substring(0, s.indexOf("_")));
                            destCust[1] = Integer.parseInt(s.substring(s.indexOf("_")+1));
                        }
                    }
                    break;
                }
            }
            if(flag == 1) {
                System.out.println("Notification received, customer calling at coordinates: (" + coorCust[0] + ", " + coorCust[1] + "). Requesting to reach coordinates: (" + destCust[0] + ", " + destCust[1] + ")");
                System.out.print("Accept?(Y/N): ");
                char accept = sc.nextLine().charAt(0);
                if(accept == 'Y') {
                    driver.notification("Y");
                    Journey j = new Journey(coorCust, destCust);
                    j.travel(coorDriver, coorCust, 9);
                }
            }
            else {
                System.out.println("No new notification received");
            }
        }

    }
}