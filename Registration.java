package serverSimulation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class Registration {
	
	Scanner in = new Scanner(System.in);
	String signin="signin";
	String signup="signup";
	String login;
	String password;
	String repassword;
	String fileName="src/baseofdata";
	 
	SignIn siginer = new SignIn();
	SignUp siguper = new SignUp();
	 
	 public  void registration(){
		 while(true){
	        System.out.print("��� ����� ������� signin, � ��� ����������� signup: ");
	        String scan = in.nextLine();
	        if(scan.equals(signin)){
	        	System.out.print("������� login: ");
	        	login = in.nextLine();	
	        	System.out.print("������� password: ");
		        password = in.nextLine();
		        try {
					siginer.sigin(fileName,login,password);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        break;
	        } else if(scan.equals(signup)) {
	        	 boolean repeatLogin=true;
	        	 while(repeatLogin){
	        		 System.out.print("������� login: ");
	        		 login = in.nextLine();
	        		 repeatLogin = siguper.findLogin(fileName,login);
	        		 if(repeatLogin){
	        			 System.out.println("����� login ��� ����������, ����������, �������� ������");
	        		 } 
	        	 }
	        	 while(true){
	        		System.out.print("������� password: ");
	 	       		password = in.nextLine();
	 	       		System.out.print("��������� ��������� ������ password: ");
	 	       		repassword = in.nextLine();
	 	       		if(password.equals(repassword)){
	 	       			break;
	 	       		} else
	 	        		System.out.println("������ �� ���������. ���������� ��������� �����");
	 	        
	        	  }
	        		Calendar day = Calendar.getInstance();
		        	long ar= day.getTimeInMillis();
		        	int salt = (int) ar;
		        	try {
						siguper.sigup(login,password, salt, fileName);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	break;
	        } 
	        System.out.println("���������� ������������������ ��� �����");	
		 }
	 }
}

