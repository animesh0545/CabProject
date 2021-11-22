import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Login {

    private String username, password;
    private File file;
    Scanner sc;
    // FileWriter fw;
    // BufferedWriter bw;
    PrintWriter pw;
    private String userDetails;

    public Login(){
        username = "";
        password = "";
        file = null;
        sc = null;
        pw = null;
        
        userDetails = "";
    }

    public Login(File f){
        
        userDetails = "";
        file = f;
        try{
            pw = new PrintWriter(file);
            sc = new Scanner(file);
        }
        catch(Exception e){
            System.out.println(e);
        }

        

    }
    

    boolean signUp(String username, String password, String name){
        /* Append username and password to the file after 
        ensuring that it has not been already used */
        if (sc == null)
            System.out.println("NOooooooooooO");
        while (sc.hasNextLine()){
        StringTokenizer st = new StringTokenizer(sc.nextLine());
            if (st.nextToken().equals(this.username)){
                return false;
            }
        }
        try {
            pw.println(username + " " + password + " " + name);
            System.out.println("Signup complete");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
        
    }

       

    boolean login(String user, String pwd){
        
        while(sc.hasNextLine()){
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            String s = st.nextToken();
            System.out.println(s);
            if (s.equals(user)){
                if (st.nextToken().equals(pwd)){
                    userDetails = st.toString().substring(user.length()+pwd.length()+2);
                    return true;
                }
            }
        }
        return false;
    }

    String getDetails(){
        return userDetails;
    }
    
}
