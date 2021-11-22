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

    Customer(){
        
    }

    
    
    Customer(String name, String username, String password) {
        
        super(new File("./../data/customers.txt"));
        location = new int[2];
        sc = new Scanner(System.in);
        this.name = name;
        this.username = username;
        this.password = password;
        this.customerSignup();
        

    }

    Customer(String username, String password){
        //location = new int[2];
        super(new File("./../data/customers.txt"));
        location = new int[2];
        sc = new Scanner(System.in);
        this.username = username;
        this.password = password;
        //sc = new Scanner(System.in);
        this.customerLogin(); 
        
        
    }

    public void customerSignup(){
        while (true) {
            if (signUp(username, password, name)) {
                obtainLocation();
                break;
            }
            System.out.println("The username already exists, please try again");
            System.out.print("Enter username:\t");
            username = sc.nextLine();
            System.out.print("Enter password:\t");
            password = sc.nextLine();
        }
    }

    public void customerLogin(){
        while(true){
            if (login(username, password)){
                name = getDetails();
                obtainLocation();
                break;
            }
            System.out.println("Wrong info, enter 1 to try again, 2 to go to signUp");
            int choice = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter username:\t");
            username = sc.nextLine();
            System.out.print("Enter password:\t");
            password = sc.nextLine();
            if (choice == 2){
                System.out.print("Enter name:\t");
                name = sc.nextLine();
                customerSignup();
                break;
            }
            
        }
    }

    
    
    public void obtainLocation() {
    	System.out.println("Welcome, " + name);
        System.out.println("Enter your location");
        location[0] = sc.nextInt();
        location[1] = sc.nextInt();


    	
    }

    int[] getLocation(){
        return location;
    }

   

}