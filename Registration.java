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
	Listener listen = new Listener();
	 
	 public  void registration(){
		 while(true){
	        System.out.print("��� ����� ������� signin, � ��� ����������� signup: ");
	        String scan = in.nextLine();
	        if(scan.equals(signin)){
	        	System.out.print("������� login: ");
	        	shifrlogin = listen.listenString(in.nextLine()); 	
	        	System.out.print("������� password: ");
	        	shifrpassword = listen.listenString(in.nextLine());
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
	        		 System.out.print("������� login: ");
	        		 shifrlogin = listen.listenString(in.nextLine()); 
	        		 repeatLogin = siguper.findLogin(fileName,shifrlogin);
	        		 if(repeatLogin){
	        			 System.out.println("����� login ��� ����������, ����������, �������� ������");
	        		 } 
	        	 }
	        	 while(true){
	        		System.out.print("������� password: ");
	        		shifrpassword = listen.listenString(in.nextLine()); 
	 	       		System.out.print("��������� ��������� ������ password: ");
	 	       		shifrrepassword  = listen.listenString(in.nextLine()); 
	 	       		if(shifrpassword.equals(shifrrepassword)){
	 	       			break;
	 	       		} else
	 	        		System.out.println("������ �� ���������. ���������� ��������� �����");
	 	        
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
	        System.out.println("���������� ������������������ ��� �����");	
		 }
	 }
}

