public class Customer {
    private String name;
    int[] location;
    int[] destination;
    int[] currentLocation;
    
    private String loginId;
    private String password;
    

    Customer(String n, int l[]){
        name = n;
        location = l;
    }

    
    
    void getName() {
    	System.out.println("Welcome, " + name);
    	
    }

    int[] getLocation(){
        return location;
    }


}