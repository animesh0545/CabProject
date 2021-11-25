import java.util.*;
import java.io.*;
public class uberCab {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n=1;
        do{
            System.out.print("Enter 1 for Customer or 2 for Driver: ");
            n = sc.nextInt();
            //System.out.println(n);
        }while (n != 1 && n != 2);

        Customer customer = null;
        Driver driver = null;

        if (n == 1){

            int m = 0;

            do{
                System.out.print("Enter 1 for SignUp and 2 for Login: ");
                m = sc.nextInt();

            }while (m != 1 && m != 2);

            System.out.print("Enter your username: ");
            String username = sc.next();
            sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            // System.out.println(username + "\t" + password);

            if (m == 1){
                System.out.print("Enter your first name: ");
                String fname = sc.next();
                System.out.print("Enter your last name: ");
                String lname = sc.next();
                customer = new Customer(fname + "_" + lname, username, password);
            }
            else {
                customer = new Customer(username, password);
                username = customer.getUserName();
                password = customer.getPassword();
            }

                int l1[] = customer.getLocation();
                City city = new City();
                System.out.println("Choose destination to reach among the below mentioned landmarks: ");
                for(Landmark l: city.lmk) {
                    System.out.println(l.id + ". " + l.name);
                }
                int l2[] = {-1, -1};
                char ch = '!';
                
                while(l2[0] == -1 && l2[1] == -1) {
                    System.out.print("Enter valid choice: ");
                    ch = sc.next().charAt(0);
                    l2 = city.getLandmarkLocationFromID(ch);
                }
                String lmName = city.getLandmarkNameFromID(ch);
                
                System.out.println("Landmark to reach: " + lmName + "(" + ch + ") [Coordinates: ("+l2[0] + "," + l2[1] + ")]");
                Journey j = new Journey(l1, l2);
                j.addCabs();
                if(j.cabs.size() == 0) {
                    sc.close();
                    return;
                }

                System.out.print("Choose any one Cab to travel: ");
                int cabToTravel = sc.nextInt();
                ArrayList<Cab> cabList = new ArrayList<Cab>(j.cabs);
                // for(int i = 0; i < cabList.size(); i++) {
                //     System.out.println(cabList.get(i).driverDetails.name);
                // }
                Cab cab = cabList.get(cabToTravel-1);
                Driver d = cab.driverDetails;
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
                    j.grid[l1[0]][l1[1]] = 'x';
                    System.out.println("Cab is arriving...");
                    j.cl[0] = cabList.get(cabToTravel-1).location[0];
                    j.cl[1] = cabList.get(cabToTravel-1).location[1];

                    j.travel(j.cl, j.source, '-');
                    
                    

                    try {
                        j.t.join();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    Scanner scan = null;
                    outer: while(true) {
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
                                if(l == 10) {
                                    break outer;
                                }
    
                            }
                        }
                        scan.close();
                    }

                    System.out.println("Cab travelling to destination...");
                    j.travel(l1, l2, '+');
                    try {
                        j.t.join();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    System.out.println("Fare to be paid: " + (int)cab.fare);
                    System.out.print("Rate the driver(0-5.0): ");
                    double rating = sc.nextDouble();
                    d.notification(Double.toString(rating));
                }



                sc.close();
        }
        
        else{
            //TODO
            int m = 0;

            do{
                System.out.print("Enter 1 to Login: ");
                m = sc.nextInt();
                sc.nextLine();

            }while (m != 1);

            System.out.print("Enter your username: ");
            String username = sc.next();
            sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            // System.out.println(username + "\t" + password);
            driver = new Driver(username, password);
            username = driver.getUsername();
            password = driver.getPassword();
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
            String regisNo = "";
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
                        if(i == l-4)
                            regisNo = s;
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
                    scan.close();
                    break;
                }
            }
            if(flag == 1) {
                System.out.println("Notification received, customer calling at coordinates: (" + coorCust[0] + ", " + coorCust[1] + "). Requesting to reach coordinates: (" + destCust[0] + ", " + destCust[1] + ")");
                
                char accept = ' ';
                while(accept != 'Y') {
                    System.out.print("Press Y to accept: ");
                    accept = sc.nextLine().charAt(0);
                }

                if(accept == 'Y') {
                    driver.notification("Y");
                    Journey j = new Journey(coorCust, destCust);
                    j.grid[coorCust[0]][coorCust[1]] = 'x';
                    Cab cab = new Cab(regisNo, driver, coorDriver, coorCust);
                    int numCabs = 0;
                    try {
                        scan = new Scanner(new File("./../data/drivers.txt"));
                        // System.out.println("**********************");
        
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    while(scan.hasNextLine()) {
                        String detail = scan.nextLine();
                        StringTokenizer st = new StringTokenizer(detail);
                        int l = st.countTokens();
                        if(l == 7) {
                            numCabs++;
                        }
                    }
                    numCabs++;




                    j.travel(coorDriver, coorCust, '-');
                    // j.addCabs();
                    
                    try {
                        j.t.join();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    int startTrip = 0;
                    while(startTrip != 1) {
                    System.out.print("Press 1 to start trip: ");
                    startTrip = sc.nextInt();
                    }

                    driver.notification("S");
                    System.out.println("Cab travelling to destination...");
                    j.travel(coorCust, destCust, '+');

                    try {
                        j.t.join();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                    String token = "";
                    outer: while(true) {
                        Scanner scann = null;
                        try {
                            scann = new Scanner(new File("./../data/drivers.txt"));
                            // System.out.println("**********************");
            
                        } catch (FileNotFoundException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        // System.out.println("**********************");
            
            
            
                        while(scann.hasNextLine()) {
                            String detail = scann.nextLine();
                            StringTokenizer st = new StringTokenizer(detail);
                            String uname = "";
                            if(st.countTokens() != 0)
                                uname = st.nextToken();
                            if(uname.equals(driver.getUsername())) {
                                int l = st.countTokens();
                                if(l == 11) {
                                    for(int i = 0; i < l; i++) {
                                        token = st.nextToken();
                                    }
                                    break outer;
                                }
    
                            }
                        }
                        scann.close();
                    }


                    PrintWriter pw = null;
                    try {
                        sc = new Scanner(new File("./../data/drivers.txt"));
                    } catch (FileNotFoundException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    String data = "";
                    double currRating = 0.0;
                    while(sc.hasNextLine()) {
                        String detail = sc.nextLine();
                        StringTokenizer st = new StringTokenizer(detail);
                        String uName = "";
                        if(st.countTokens()!=0) {
                            uName = st.nextToken();
                            // data += uName + " ";
                        }
                        if(uName.equals(driver.getUsername())) {
                            data += uName + " ";
                            int l = st.countTokens();
                            int numRated = 0;
                            double totRating = 0.0;
                            currRating = 0.0;
                            String regNo = "";
                            for(int i = 0; i < l; i++) {
                                String s = st.nextToken();
                                if(i == 0 || i == 1)
                                    data += s + " ";
                                if(i == 2)
                                    totRating = Double.parseDouble(s);
                                if(i == 3)
                                    numRated = Integer.parseInt(s);
                                if(i == 4)
                                    regNo = s;
                                if(i == l-1)
                                    currRating = Double.parseDouble(s);
                            }
                            totRating = (totRating*numRated+currRating)/(numRated+1);
                            numRated++;
                            data += String.format("%.2f", totRating) + " " + numRated + " " + regNo + " " + destCust[0] + "_" + destCust[1] + "\n";
                        }
                        else
                            data += detail + "\n";
                    }
                    try {
                        pw = new PrintWriter(new File("./../data/drivers.txt"));
                        // System.out.println(this.username);
                        // System.out.println(data);
                        pw.print(data);
                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    finally {
                        sc.close();
                        pw.close();
                    }
                    cab.calcFare(j.distance(), numCabs);
                    // System.out.println("*****************j.distance(): " + j.distance() + " numCabs " + numCabs);
                    System.out.println("Fare received: " + (int)cab.fare);
                    System.out.println("Rating received: " + currRating);


                }           

            }
            else {
                System.out.println("No new notification received");
            }
        }
    }
}