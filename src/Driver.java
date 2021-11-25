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

    String getUsername() {
        return username;
    }
    String getPassword() {
        return password;
    }

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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        while(sc.hasNextLine()){
            //System.out.println("Im here");
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

    Driver(String username, String password) {
        super(new File("./../data/drivers.txt"));
        this.username = username;
        this.password = password;
        in = new Scanner(System.in);
        this.driverLogin();
    }
    
    Driver(String n, double d) {
    	name = n;
    	rating = d;
    	num = 1;
    }

    String getName() {
        // StringTokenizer st = new StringTokenizer(driverDetails);
        // name = st.nextToken();
        // name += " " + st.nextToken();
        return name;
    }

    void updateRating(double r){
        rating = (rating * num + r) / ++num;
    }

    void driverLogin() {
        while(true){
            if (login(username, password)){
                driverDetails = getDetails();
                // obtainLocation();
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

    void notification(String s) {
        PrintWriter pw = null;
        Scanner sc = null;
        try {
            sc = new Scanner(new File("./../data/drivers.txt"));
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
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

    }

}
