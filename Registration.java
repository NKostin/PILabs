package serverSimulation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class Registration {
	
	Scanner in = new Scanner(System.in);
	String signin="signin";
	String signup="signup";
	String shifrlogin;
	String shifrpassword;
	String shifrrepassword;
	String fileName="src/baseofdata";
	 
	SignIn siginer = new SignIn();
	SignUp siguper = new SignUp();
	
	 
	 public  void registration(){
		 while(true){
	        System.out.print("Для входа введите signin, а для регистрации signup: ");
	        String scan = in.nextLine();
	        if(scan.equals(signin)){
	        	System.out.print("Введите login: ");
	        	shifrlogin = Listener.listenString(in.nextLine()); 	
	        	System.out.print("Введите password: ");
	        	shifrpassword = Listener.listenString(in.nextLine());
		        try {
					siginer.sigin(fileName,shifrlogin,shifrpassword);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        break;
	        } else if(scan.equals(signup)) {
	        	 boolean repeatLogin=true;
	        	 while(repeatLogin){
	        		 System.out.print("Введите login: ");
	        		 shifrlogin = Listener.listenString(in.nextLine()); 
	        		 repeatLogin = siguper.findLogin(fileName,shifrlogin);
	        		 if(repeatLogin){
	        			 System.out.println("Такой login уже существует, пожалуйста, выберите другой");
	        		 } 
	        	 }
	        	 while(true){
	        		System.out.print("Введите password: ");
	        		shifrpassword = Listener.listenString(in.nextLine()); 
	 	       		System.out.print("Повторите введенный пароль password: ");
	 	       		shifrrepassword  = Listener.listenString(in.nextLine()); 
	 	       		if(shifrpassword.equals(shifrrepassword)){
	 	       			break;
	 	       		} else
	 	        		System.out.println("Пароли не совпадают. Пожалуйста повторите снова");
	 	        
	        	  }
	        		Calendar day = Calendar.getInstance();
		        	long ar= day.getTimeInMillis();
		        	int salt = (int) ar;
		        	try {
						siguper.sigup(shifrlogin,shifrpassword, salt, fileName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	break;
	        } 
	        System.out.println("Необходимо зарегестрироваться или войти");	
		 }
	 }
}


