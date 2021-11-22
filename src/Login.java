import java.io.*;
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
            pw = new PrintWriter(new FileOutputStream(file, true));
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
            pw.append(username + " " + password + " " + name +"\n");
            System.out.println("Signup complete");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        finally{
            pw.close();
        }
        return true;
        
    }

       

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
        
        return false;
    }

    String getDetails(){
        return userDetails;
    }
    
}
