import java.util.*;
import java.io.*;

public class Driver extends Login {
    private String driverDetails;
    private String name;
    private String username;
    private String password;
    double rating;
    int num;
    Scanner in;
    
    
    //getter function
    String getName() {
        return name;
    }
    
    //getter function
    String getUsername() {
        return username;
    }

    //getter function
    String getPassword() {
        return password;
    }

    //parameterized constructor
    Driver(String username, String name, double rating, int num){
        this.username = username;
        this.name = name;
        this.rating = rating;
        this.num = num;
        in = new Scanner(System.in);
        File file = new File("./../data/drivers.txt"); 
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            StringTokenizer st = new StringTokenizer(s);
            String uname = null;
            if(st.countTokens() != 0)
                uname = st.nextToken();
            if(uname.equals(username)) {
                this.password = st.nextToken();
                break;
            }
        }
    }

    //paramertized constructor
    Driver(String username, String password) {
        super(new File("./../data/drivers.txt"));
        this.username = username;
        this.password = password;
        in = new Scanner(System.in);
        this.driverLogin();
    }
    
    //parameterized constructor
    Driver(String n, double d) {
    	name = n;
    	rating = d;
    	num = 1;
    }

    //function for rating updation
    void updateRating(double r){
        rating = (rating * num + r) / ++num;
    }

    //function for driver login
    void driverLogin() {
        while(true){
            if (login(username, password)){
                driverDetails = getDetails();
                break;
            }
            System.out.println("Wrong info entered, please try again");
            System.out.print("Enter username: ");
            username = in.next();
            in.nextLine();
            System.out.print("Enter password: ");
            password = in.nextLine();
        }
    }

    //notification function to notify something from customer to driver or vice versa
    void notification(String s) {
        PrintWriter pw = null;
        Scanner sc = null;
        try {
            sc = new Scanner(new File("./../data/drivers.txt"));
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        String data = "";
        while(sc.hasNextLine()) {
            String detail = sc.nextLine();
            StringTokenizer st = new StringTokenizer(detail);
            String uName = st.nextToken();
            if(uName.equals(this.username))
                data += detail + " " + s + "\n";
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

    }

}
