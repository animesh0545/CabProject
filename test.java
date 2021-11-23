
import java.io.*;
import java.util.*;

 class test {

   static void notification(String username) {
      PrintWriter pw = null;
      Scanner sc = null;
      try {
          sc = new Scanner(new File("./test.txt"));
      } catch (FileNotFoundException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
      }
      String data = "";
      while(sc.hasNextLine()) {
          String detail = sc.nextLine();
          StringTokenizer st = new StringTokenizer(detail);
          String uName = st.nextToken();
          if(uName.equals(username))
            data += detail + " " + 1 + "_" + 2 + "\n";
          else
            data += detail + "\n";
      }
      try {
          pw = new PrintWriter(new File("./test.txt"));
          System.out.println("*********************");
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
   public static void main(String[] args) {
      // notification("disco");
      // String s = "This is a line";
      // StringTokenizer st = new StringTokenizer(s);
      // System.out.println("length = " + st.countTokens());
      // String ss = st.nextToken();
      // System.out.println("length = " + st.countTokens());
      while(true) {
         int i = 0;
         while(true) {
            System.out.println("Inner Loop");
            if(i == 10)
               break;
            i++;
         }
         System.out.println("Outer Loop");

      }
   }
}