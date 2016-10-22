import java.io.*

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class Treatment {
	
static ArrayList<String> list= new ArrayList<String>();
public static void sigin(String login,String password, int salt, String fileName) throws Exception{
	String stsalt = String.valueOf(salt);
	byte[] hashpsstr = makeHash(password,stsalt);
	
	String text = "\n"+login+"\n"+ stsalt+"\n";
	
	writehash(fileName, hashpsstr);
	write(fileName,text);
	System.out.println("Регистрация прошла успешно");
	
	}
public static void write(String fileName, String text){
	try(FileWriter writer = new FileWriter(fileName, true))
    {
        writer.write(text);
        writer.flush();
    }
    catch(IOException ex){
         
        System.out.println(ex.getMessage());
    } 
	
}
public static void writehash(String fileName,byte[] hashps ){
	try(FileOutputStream fos=new FileOutputStream(fileName, true))
    {
		 fos.write(hashps);
    }
    catch(IOException ex){
         
        System.out.println(ex.getMessage());
    } 
	
}
public static void readhash(String fileName, String login, String password ) throws Exception{
	byte[] buffer = new byte[32];
	try(
			FileInputStream fin=new FileInputStream(fileName))
    { while(fin.available()>0){
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
				}
				else flag=false;
				
			}
			
		}
		
		if(ns=='\n'){
			
			boolean flag = true;
			
			while(flag){
				ns = (char)fin.read();
				if(ns!='\n'){
					insalt +=ns; 
				}
				else flag=false;
				
			}
			
		}
		if(login.equals(inlogin)){
			
			byte[] provhashps = makeHash(password, insalt);
			if (new String(provhashps).equals(new String(buffer))){
				System.out.println("Успешный вход");
				return;
			
		} 
		}
		
    }
    System.out.println("Неверный логин или пароль");  
    }
    catch(IOException ex){
         
        System.out.println(ex.getMessage());
    }	
}

public static byte[] makeHash(String password, String stsalt) throws Exception{
	
	String ps = password + stsalt;
	
	MessageDigest md1 = MessageDigest.getInstance("SHA-256");
	
	md1.update(ps.getBytes("UTF-16"));
	byte[] hashps = md1.digest();
	return hashps;
}
public void login(String fileName, String login, String password) throws Exception{
	
	readhash(fileName, login, password);
}
}
