
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Shifr {
	private static ArrayList<Character> chars = new ArrayList<Character>();
	private static ArrayList<Character> shchars = new ArrayList<Character>();
	private static ArrayList<Character> rasshchars = new ArrayList<Character>();
	public void shifrText(String name, int sdvig){
	
		chars = readText(name);
	       
	        for(int i=0;i<chars.size();i++){
	            if(chars.get(i) >= 'a' && chars.get(i) <= 'z'){
	            	shchars.add (( (char)littleChar(chars.get(i) + sdvig)));
	            } else if(chars.get(i) >= ' ' && chars.get(i) <= '@'||chars.get(i)=='\n'){
	            	shchars.add (chars.get(i));
	            } 
	        }
	        System.out.println("Зашифрованный текст");
	        for(int i=0;i<shchars.size();i++){
		        System.out.print(shchars.get(i));
		        }
	        
    }
	public ArrayList<Character> readText(String name){
		ArrayList<Character> readchars = new ArrayList<Character>();
	   try {
           FileInputStream fis = new FileInputStream(name);
        
           char current;
           while (fis.available() > 0) {
        	  readchars.add((char) fis.read());
            
           }
          
         } catch (IOException e) {
           e.printStackTrace();
         }
	return readchars;
	}
	public void rasshifrText(int sdvig){
		
		  for(int i=0;i<shchars.size();i++){
	            if(shchars.get(i) >= 'a' && shchars.get(i) <= 'z'){
	            	rasshchars.add (( (char)raslittleChar(shchars.get(i) - sdvig)));
	            } else if(shchars.get(i) >= ' ' && shchars.get(i) <= '@'||shchars.get(i)=='\n'){
	            	rasshchars.add (shchars.get(i));
	            } 
	        }
		  System.out.println();
		  System.out.println("Расшифрованный текст");
	        for(int i=0;i<rasshchars.size();i++){
		        System.out.print(rasshchars.get(i));
		        }
	}
	private static int littleChar(int i) {
		if (i > 97 && i<=122 ){
			return i;
		}
        else if (i > 122){
        	 return i-26;
        }
        return i;
	}
	private static int raslittleChar(int i) {
		if (i > 97 && i<=122 ){
			return i;
		}
        else if (i < 97){
        	 return i+26;
        }
        return i;
	}
}
