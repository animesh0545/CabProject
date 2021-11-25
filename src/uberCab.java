import java.util.*;
import java.io.*;
public class uberCab {

    //main function for execution
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n=1;
        //taking value to detect if the user is customer or driver
        do{
            System.out.print("Enter 1 for Customer or 2 for Driver: ");
            n = sc.nextInt();
        }while (n != 1 && n != 2);

        Customer customer = null;
        Driver driver = null;

        //if the user is customer then perform the following actions
        if (n == 1){
            int m = 0;

            //checking if the customer wants to login or signup
            do{
                System.out.print("Enter 1 for SignUp and 2 for Login: ");
                m = sc.nextInt();

            }while (m != 1 && m != 2);

            //login and signup both would require the following details
            System.out.print("Enter your username: ");
            String username = sc.next();
            sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            //if the customer want to login then he/she has to enter his/her first and last name
            if (m == 1){
                System.out.print("Enter your first name: ");
                String fname = sc.next();
                System.out.print("Enter your last name: ");
                String lname = sc.next();
                customer = new Customer(fname + "_" + lname, username, password);
                username = customer.getUserName();
                password = customer.getPassword();
            }
            else {
                customer = new Customer(username, password);
                username = customer.getUserName();
                password = customer.getPassword();
            }

                //get customer location and display the landmarks among which he/she has to choose from as destination
                int l1[] = customer.getLocation();
                City city = new City();
                System.out.println("Choose destination to reach among the below mentioned landmarks: ");
                for(Landmark l: city.lmk) {
                    System.out.println(l.id + ". " + l.name);
                }
                int l2[] = {-1, -1};
                char ch = '!';
                
                //this loop will run if the choice entered is invalid
                while(l2[0] == -1 && l2[1] == -1) {
                    System.out.print("Enter valid choice: ");
                    ch = sc.next().charAt(0);
                    l2 = city.getLandmarkLocationFromID(ch);
                }
                String lmName = city.getLandmarkNameFromID(ch);
                
                System.out.println("Landmark to reach: " + lmName + "(" + ch + ") [Coordinates: ("+l2[0] + "," + l2[1] + ")]");

                //once the landmark destination is entered, a list of available drivers would be displayed to the customer
                //if no cab is available then appropriate message would be displayed
                Journey j = new Journey(l1, l2);
                j.addCabs();
                if(j.cabs.size() == 0) {
                    sc.close();
                    return;
                }

                //customer would choose among the given cabs
                System.out.print("Choose any one Cab to travel: ");
                int cabToTravel = sc.nextInt();

                //once the customer chooses a cab, a notification would be sent to the chosen driver
                ArrayList<Cab> cabList = new ArrayList<Cab>(j.cabs);
                Cab cab = cabList.get(cabToTravel-1);
                Driver d = cab.driverDetails;
                d.notification(l1[0] + "_" + l1[1]);
                d.notification(l2[0] + "_" + l2[1]);
                
                //this loop will go on until the driver accepts customer's request
                String token = "";
                outer: while(true) {
                    Scanner scan = null;
                    try {
                        scan = new Scanner(new File("./../data/drivers.txt"));
        
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
        
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

                //if the driver accepts the request, then the foolowing code would be executed
                if(token.equals("Y")) {
                    //driver would start travelling towards customer
                    j.grid[l1[0]][l1[1]] = 'x';
                    System.out.println("Cab is arriving...");
                    j.cl[0] = cabList.get(cabToTravel-1).location[0];
                    j.cl[1] = cabList.get(cabToTravel-1).location[1];

                    j.travel(j.cl, j.source, '-');
                    
                    try {
                        j.t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //once the driver reaches the customer, the customer would wait until the driver starts the journey
                    Scanner scan = null;
                    outer: while(true) {
                        try {
                            scan = new Scanner(new File("./../data/drivers.txt"));
            
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
            
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

                    //once the driver starts the journey, the customer and driver both would start moving towards the destination
                    System.out.println("Cab travelling to destination...");
                    j.travel(l1, l2, '+');
                    try {
                        j.t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //once the journey is over, the customer would pay the generated fare and rate the journey
                    System.out.println("Fare to be paid: Rs " + (int)cab.fare);
                    System.out.print("Rate the driver(0-5.0): ");
                    double rating = sc.nextDouble();
                    d.notification(Double.toString(rating));
                }

                sc.close();
        }
        
        //if the user is driver then perform the following actions
        else{
            //TODO
            int m = 0;

            //this loop will run until the driver enters 1 to login
            do{
                System.out.print("Enter 1 to Login: ");
                m = sc.nextInt();
                sc.nextLine();

            }while (m != 1);

            //the driver would be prompted to enter his/her login credentials until some credential matches one in the database file
            System.out.print("Enter your username: ");
            String username = sc.next();
            sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            driver = new Driver(username, password);
            username = driver.getUsername();
            password = driver.getPassword();
            
            Scanner scan = null;
            try {
                scan = new Scanner(new File("./../data/drivers.txt"));

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //once the driver logs in, he/she will receive a notification if a customer is calling him/her to go somewhere
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
            //if flag is 1 then the notification is received and will be displayed
            //Then the driver would be prompted to enter if he/she accepts the request
            if(flag == 1) {
                City c = new City();
                char id = c.grid[destCust[0]][destCust[1]];
                String lmName = c.getLandmarkNameFromID(id);
                System.out.println("Notification received, customer calling at coordinates: (" + coorCust[0] + ", " + coorCust[1] + "). Requesting to reach " + lmName + "(" + id + "), coordinates: (" + destCust[0] + ", " + destCust[1] + ")");
                char accept = ' ';
                while(accept != 'Y') {
                    System.out.print("Press Y to accept: ");
                    accept = sc.nextLine().charAt(0);
                }

                //if the driver accepts the request, then the following lines would be executed
                if(accept == 'Y') {
                    driver.notification("Y");
                    Journey j = new Journey(coorCust, destCust);
                    j.grid[coorCust[0]][coorCust[1]] = 'x';
                    Cab cab = new Cab(regisNo, driver, coorDriver, coorCust);
                    //here we are trying to find the number of cabs currently available for fare calculation
                    int numCabs = 0;
                    try {
                        scan = new Scanner(new File("./../data/drivers.txt"));
        
                    } catch (FileNotFoundException e) {
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

                    //once the driver accepts the request, he/she will start moving towards customer
                    j.travel(coorDriver, coorCust, '-');
                    // j.addCabs();
                    
                    try {
                        j.t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //once the driver reaches the customer, he/she will be asked to start the trip
                    int startTrip = 0;
                    while(startTrip != 1) {
                    System.out.print("Press 1 to start trip: ");
                    startTrip = sc.nextInt();
                    }

                    driver.notification("S");

                    //once the driver responds 'yes' to start trip, he/she along with the customer will start moving towards destination
                    System.out.println("Cab travelling to destination...");
                    j.travel(coorCust, destCust, '+');

                    try {
                        j.t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    // String token = "";
                    //once the trip finishes, the driver will wait for the customer to pay the generated fare and rate the journey
                    outer: while(true) {
                        Scanner scann = null;
                        try {
                            scann = new Scanner(new File("./../data/drivers.txt"));
            
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
            
                        while(scann.hasNextLine()) {
                            String detail = scann.nextLine();
                            StringTokenizer st = new StringTokenizer(detail);
                            String uname = "";
                            if(st.countTokens() != 0)
                                uname = st.nextToken();
                            if(uname.equals(driver.getUsername())) {
                                int l = st.countTokens();
                                if(l == 11) {
                                    // for(int i = 0; i < l; i++) {
                                    //     token = st.nextToken();
                                    // }
                                    break outer;
                                }
                            }
                        }
                        scann.close();
                    }

                    //once the trip is over and the driver has been rated, the info about driver's availability, current position and current rating will change
                    PrintWriter pw = null;
                    try {
                        sc = new Scanner(new File("./../data/drivers.txt"));
                    } catch (FileNotFoundException e1) {
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
                        pw.print(data);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    finally {
                        sc.close();
                        pw.close();
                    }
                    cab.calcFare(j.distance(), numCabs);

                    //the amount of fare received and the rating received will be displayed to the driver
                    System.out.println("Fare received: Rs " + (int)cab.fare);
                    System.out.println("Rating received: " + currRating);

                }           

            }

            //if no notification is received, then the following message will be displayed to the driver
            else {
                System.out.println("No new notification received");
            }
        }
    }
}