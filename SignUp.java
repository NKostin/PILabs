package serverSimulation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;

public class SignUp {
	
	public void sigup(String shifrlogin, String shifrpassword, int salt, String fileName) throws Exception {
		
			String stsalt = String.valueOf(salt);
			String password = Encryption.rasshifr(shifrpassword);
			byte[] hashpsstr = makeHash(password,stsalt);
			String login = Encryption.rasshifr(shifrlogin);
			String text = "\n"+login+"\n"+ stsalt+"\n";
			writehash(fileName, hashpsstr);
			write(fileName,text);
			writeLogin(login);
			System.out.println("Регистрация прошла успешно");
			
			
			}
	public static byte[] makeHash(String password, String stsalt) throws Exception{
		String ps = password + stsalt;
		MessageDigest md1 = MessageDigest.getInstance("SHA-256");
		md1.update(ps.getBytes("UTF-16"));
		byte[] hashps = md1.digest();
		return hashps;
	}
	public static void write(String fileName, String text){
		try (FileWriter writer = new FileWriter(fileName, true)){
	        writer.write(text);
	        writer.flush();
	    }
	    catch(IOException ex){
	        System.out.println(ex.getMessage());
	    } 
	}
	public static void writehash(String fileName,byte[] hashps ){
		try(
				FileOutputStream fos=new FileOutputStream(fileName, true))
	    {
			 fos.write(hashps);
			 
	    }
	    catch(IOException ex){
	        System.out.println(ex.getMessage());
	    } 
		
	}
	public boolean findLogin(String fileName,String login) {
		byte[] buffer = new byte[32];
		try {
			FileInputStream fin=new FileInputStream(fileName);
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
					return true;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
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


