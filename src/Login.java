import java.io.File;
import java.util.Scanner;
public class Login {

    private String username, password;
    private File file;

    public Login(){
        username = "";
        password = "";
        file = null;
    }

    public Login(String username, String password, File f){
        signUp(username, password);
        file = f;
    }

    void signUp(String username, String password){
        this.username = username;
        this.password = password;
        
    }

    boolean checkPassword(String pwd){
        return password.equals(pwd);
    }

    boolean checkUsername(String user){
        return username.equals(user);
    }

    boolean login(String user, String pwd){
        Scanner sc = new Scanner(file);
        while(sc.hasNext()){
            String s = sc.nextLine();

        }
        return checkUsername(user) && checkPassword(pwd);
    }
    
}
