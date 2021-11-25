import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Login {

    private String username, password;
    private File file;
    private String userDetails;
    Scanner sc;
    PrintWriter pw;
    RandomAccessFile rac;

    //default constructor
    public Login(){
        username = "";
        password = "";
        file = null;
        sc = null;
        pw = null;
        rac = null;
        userDetails = "";
    }

    //parameterized constructor
    public Login(File f){
        userDetails = "";
        file = f;
        try{
            pw = new PrintWriter(new FileOutputStream(file, true));
            sc = new Scanner(file);
            rac = new RandomAccessFile(file, "r");
        }
        catch(Exception e){
            System.out.println(e);
        }

        

    }
    
    //signup function to add the details of the new customer to the file database and return if sugnup was successful or not
    boolean signUp(String username, String password, String name){
        /* Append username and password to the file after 
        ensuring that it has not been already used */
        if (sc == null)
            System.out.println("NOooooooooooO");
        while (sc.hasNextLine()){
        StringTokenizer st = new StringTokenizer(sc.nextLine());
            if (st.nextToken().equals(username)){
                return false;
            }
        }
        try {
            pw.append(username + " " + password + " " + name +"\n");
            System.out.println("Signup complete");
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            pw.close();
        }
        return true;
        
    }

    //login function to verify if the given username and password are in the database or not
    boolean login(String user, String pwd){
        while(sc.hasNextLine()){
            String s = sc.nextLine();
            StringTokenizer st = new StringTokenizer(s);
            if (st.nextToken().equals(user)){
                if (st.nextToken().equals(pwd)){
                    userDetails = s.substring(user.length()+pwd.length()+2);
                    return true;
                }
            }
        }

        sc.close();
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        };
        return false;
    }

    //function to get user details from database
    String getDetails(){
        return userDetails;
    }
    
}
