import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Scanner;

public class Start {
	public static void main (String []args) throws Exception{
		 Scanner in = new Scanner(System.in);
		 String temp="log";
		 String temp1="sign";
		 String login;
		 String password="";
		 String repassword;
		 String fileName="C:/Users/User/workspace/LabRegist/src/baseofdata";
		 boolean flag = true;
		 Treatment sig = new Treatment();
		 Treatment log = new Treatment();
		 while(flag){
	        System.out.print("��� ����� ������� log, � ��� ����������� sign: ");
	        String scan = in.nextLine();
	        if(scan.equals(temp)){
	        	System.out.print("������� login: ");
	        	login = in.nextLine();
	        	System.out.print("������� password: ");
		        password = in.nextLine();
		       
		        // ����� ������� �����
		        log.login(fileName, login,password);
	        } else if(scan.equals(temp1)) {
	        	boolean flag2=true;
	        	System.out.print("������� login: ");
	        	login = in.nextLine();
	        	while(flag2){
	        	System.out.print("������� password: ");
	 	       	password = in.nextLine();
	 	        System.out.print("��������� ��������� ������ password: ");
	 	        repassword = in.nextLine();
	 	        if(password.equals(repassword)){
	 	        	flag2=false;
	 	        	
	 	        }
	 	        else{
	 	        	 System.out.println("������ �� ���������. ���������� ��������� �����");
	 	        }
	 	       
	 	        
	        	}
	        		Calendar day = Calendar.getInstance();
		        	long ar= day.getTimeInMillis();
		        	int salt = (int) ar;
			       sig.sigin(login,password, salt, fileName);
	        	
	        	 
	        	 
	    
	 	        
	        	
	        } 
	       
	        
	       
	}
	}
}
