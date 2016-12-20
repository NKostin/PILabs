package serverSimulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;

public class SignIn {
	String login,password;
	
	public void sigin(String fileName, String shifrlogin, String shifrpassword) throws Exception{
		login = Encryption.rasshifr(shifrlogin);
		password = Encryption.rasshifr(shifrpassword);
		readhash(fileName, login, password);
	}

	private void readhash(String fileName, String login, String password) {
		byte[] buffer = new byte[32];
		try(FileInputStream fin=new FileInputStream(fileName)){ 
			while(fin.available()>0){
				String inlogin="";
				String insalt="";
				fin.read(buffer);
				int currentByte=fin.read();
				char ns = 0;
				
				if(currentByte=='\n'){
					boolean flag = true;
					while(flag){
						ns = (char)fin.read();
						if(ns!='\n'){
							inlogin +=ns; 
						}else 
							flag=false;
					}
				}
				
				
				if(ns=='\n'){
					boolean flag = true;
					while(flag){
						ns = (char)fin.read();
						if(ns!='\n'){
							insalt +=ns; 
						}else 
							flag=false;
					}
				
				}	
			
				if(login.equals(inlogin)){
					byte[] provhashps = makeHash(password, insalt);
					if (new String(provhashps).equals(new String(buffer))){
						System.out.println("Успешный вход");
						writeLogin(inlogin);
						return;
				
					} 
				}	
			}
			
	    System.out.println("Неверный логин или пароль");  
	    
	    }catch(IOException ex){
	         
	        System.out.println(ex.getMessage());
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
		
	public static byte[] makeHash(String password, String stsalt) throws Exception{
		String ps = password + stsalt;
		MessageDigest md1 = MessageDigest.getInstance("SHA-256");
		md1.update(ps.getBytes("UTF-16"));
		byte[] hashps = md1.digest();
		return hashps;
	}
	
	 public static void writeLogin(String login){
			try {
				FileWriter writeLogin = new FileWriter("src/login",false);
				writeLogin.write(login);
				writeLogin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
