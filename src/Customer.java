import java.io.File;
import java.util.Scanner;

public class Customer extends Login{
    private String name;
    private String username;
    private String password;
    int[] location;
    int[] destination;
    int[] currentLocation; 
    Scanner sc;

    //default constructor
    Customer(){
        name = "";
        username = "";
        password = "";
        location = new int[2];
        destination = new int[2];
        currentLocation = new int[2];
    }

    //username getter function
    String getUserName() {
        return username;
    }

    //password getter function
    String getPassword() {
        return password;
    }

    //location getter function
    int[] getLocation(){
        return location;
    }
    
    //parameterized constructor called when customer is trying to signup
    Customer(String name, String username, String password) {
        super(new File("./../data/customers.txt"));
        location = new int[2];
        sc = new Scanner(System.in);
        this.name = name;
        this.username = username;
        this.password = password;
        this.customerSignup();
        

    }

    //parameterized constructor called when customer is trying to login
    Customer(String username, String password){
        super(new File("./../data/customers.txt"));
        location = new int[2];
        sc = new Scanner(System.in);
        this.username = username;
        this.password = password;
        this.customerLogin(); 
        
        
    }

    //function for customer signup
    public void customerSignup(){
        while (true) {
            if (signUp(username, password, name)) {
                obtainLocation();
                break;
            }
            System.out.println("The username already exists, please try again");
            System.out.print("Enter username: ");
            username = sc.next();
            sc.nextLine();
            System.out.print("Enter password: ");
            password = sc.nextLine();
        }
    }

    //function for customer login
    //This function checks the username and password against all the entries in our database
    //and if any entry matches then logs in as that customer
    //and if no entry matches then asks customer if he wants to login or signup
    //customer can choose if he/she wants to login or signup.
    public void customerLogin(){
        while(true){
            if (login(username, password)){
                name = getDetails();
                obtainLocation();
                break;
            }
            int choice;
            System.out.print("Wrong info, enter 1 to try again, 2 to go to signUp: ");
            while(true) {
                choice = sc.nextInt();
                if(choice == 1 || choice == 2)
                    break;
                else
                    System.out.print("Enter 1 to login or enter 2 to signup: ");
            }
            sc.nextLine();
            System.out.print("Enter username: ");
            username = sc.next();
            sc.nextLine();
            System.out.print("Enter password: ");
            password = sc.nextLine();
            if (choice == 2){
                System.out.print("Enter your first name: ");
                String fname = sc.next();
                System.out.print("Enter your last name: ");
                String lname = sc.next();
                name = fname + "_" + lname;
                customerSignup();
                break;
            }
            
        }
    }

    //function to get customer's initial position
    public void obtainLocation() {
    	System.out.println("Welcome, " + name.substring(0, name.indexOf('_')) + " " + name.substring(name.indexOf('_')+1));
        System.out.print("Enter your location: ");
        location[0] = sc.nextInt();
        location[1] = sc.nextInt();

    } 

}