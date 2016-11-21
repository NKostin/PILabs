import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Start {
	
	 private static int k=3;
	    
	 private static ArrayList<Character> chars = new ArrayList<Character>();
	 private static ArrayList<Character> shchars = new ArrayList<Character>();
	
	 private static Map<Character, Integer> hmnd = new HashMap<Character, Integer>();
	 private static Map<Character, Float> srednd = new HashMap<Character, Float>();
	 private static Map<Character, Character> deshnew = new HashMap<Character, Character>();
	 
	 private static Map<Character, Integer> hm = new HashMap<Character, Integer>();
	 private static Map<Character, Float> sred = new HashMap<Character, Float>();
	 private static Map<Character, Float> sredtabl = new HashMap<Character, Float>();
	 private static Map<Character, Character> desh = new HashMap<Character, Character>();
	 private static String name = "C:/Users/User/workspace/Shifr/src/text";
	 
	    public static void main(String[] args) {
	        BufferedReader reader = null;
	        try {
	            FileInputStream fis = new FileInputStream(name);
	         
	            char current;
	            while (fis.available() > 0) 
	            {
	              chars.add((char) fis.read());
	             
	            }
	           
	          } catch (IOException e) 
	        	{
	            e.printStackTrace();
	        	}

	        int sizendalf=0;
	        for(int i=0;i<chars.size();i++)
	        {
	        
	        	int j=1;
	        	if(chars.get(i) >= 'a' && chars.get(i) <= 'z')
	        	{
	        		sizendalf=sizendalf+1;
	        		if(hmnd.containsKey(chars.get(i)))
	        		{
	        			int l = hmnd.get(chars.get(i));
	        			l=l+1;
	        			hmnd.put(chars.get(i), l);
	        		
	        		} 
	        		else 
	        			hmnd.put(chars.get(i), j);
	        	
	        	}
	        	else 
	        		hmnd.put(chars.get(i), j);
	        }
	        for (char key : hmnd.keySet()) 
	        {
	        	if(key >= 'a' && key <= 'z')
	        	{
	        		int value = (hmnd.get(key));
	        		float sredval = (float)value/sizendalf;
	        		srednd.put(key, sredval);
	        	}
	        }

	        for(int i=0;i<chars.size();i++)
	        {
	            if (chars.get(i) >= 'A' && chars.get(i) <= 'Z') 
	            {
	            	shchars.add (( (char)bigChar(chars.get(i) + k)));
	            } else if(chars.get(i) >= 'a' && chars.get(i) <= 'z')
	            {
	            	shchars.add (( (char)littleChar(chars.get(i) + k)));
	            } else if(chars.get(i) >= ' ' && chars.get(i) <= '@'||chars.get(i)=='\n')
	            {
	            	shchars.add (chars.get(i));
	            } 
	        }
	     
	        System.out.println("Зашифрованно>");
	        for(int i=0;i<shchars.size();i++)
	        {
	        	System.out.print(shchars.get(i));
		    }
	      
	    
	     
	      int sizealf = 0;
	        for(int i=0;i<shchars.size();i++)
	        {
	     	    int j=1;
	        	if(shchars.get(i) >= 'a' && shchars.get(i) <= 'z'){
	        		sizealf=sizealf+1;
	        	if(hm.containsKey(shchars.get(i)))
	        	{
	        		int l = hm.get(shchars.get(i));
	        		l=l+1;
	        		hm.put(shchars.get(i), l);
	        	
		        } else 
		        	hm.put(shchars.get(i), j);
		        
	        	} else
	        		hm.put(shchars.get(i), j);
	        }
	       

	   
	        for (char key : hm.keySet()) 
	        {
	        	if(key >= 'a' && key <= 'z')
	        	{
	        		int value = (hm.get(key));
	        		float sredval = (float)value/sizealf;
	        		sred.put(key, sredval);
	        	}
	        }
	         
	        sredtabl.put('a', (float)0.0804); 
	        sredtabl.put('b', (float)0.0154);
	        sredtabl.put('c', (float)0.0306);
	        sredtabl.put('d', (float)0.0399);
	        sredtabl.put('e', (float)0.1251);
	        sredtabl.put('f', (float)0.0230);
	        sredtabl.put('g', (float)0.0196);
	        sredtabl.put('h', (float)0.0549);
	        sredtabl.put('i', (float)0.0726);
	        sredtabl.put('j', (float)0.0016);
	        sredtabl.put('k', (float)0.0067);
	        sredtabl.put('l', (float)0.0414);
	        sredtabl.put('m', (float)0.0253);
	        sredtabl.put('n', (float)0.0709);
	        sredtabl.put('o', (float)0.0760);
	        sredtabl.put('p', (float) 0.0200);
	        sredtabl.put('q', (float) 0.0011);
	        sredtabl.put('r', (float) 0.0612);
	        sredtabl.put('s', (float) 0.0654);
	        sredtabl.put('t', (float) 0.0925);
	        sredtabl.put('u', (float) 0.0271);
	        sredtabl.put('v', (float) 0.0099);
	        sredtabl.put('w', (float) 0.0192);
	        sredtabl.put('x', (float) 0.0019);
	        sredtabl.put('y', (float) 0.0173);
	        sredtabl.put('z', (float) 0.0009);


	        for (char key : srednd.keySet()) 
	        {
	        	float valuend = (srednd.get(key));
	        	 for (char sredkey : sred.keySet()) 
	        	 {
	        		 float valued = (sred.get(sredkey));
	 	        	if(valuend==valued)
	 	        	{
	 	        		deshnew.put(sredkey, key);
	 	        	} 
	 	        	
	 	        }
	        	 
	        }
	        
	        for (char key : sred.keySet()) 
	        {
	        	char realkey = 0;
	        	float min=1;
	        	float value = (sred.get(key));
	        	 for (char sredkey : sredtabl.keySet()) 
	        	 {
	        			float sredvalue = (sredtabl.get(sredkey));
	        			float mod = Math.abs(value-sredvalue);
	 	        	if(mod<min)
	 	        	{
	 	        		realkey = sredkey;
	 	        		min = mod;
	 	        	} 
	 	        	
	 	        }
	        	 desh.put(key, realkey);
	        	
	        }      

	        System.out.println("\n"+"Расшифровано по среднему значению>");
	        for (int i = 0; i < shchars.size(); i++) 
	        {
	            if (desh.containsKey(shchars.get(i)))
	                System.out.print(desh.get(shchars.get(i)));
	            else
	                System.out.print(shchars.get(i));
	        }
	        
	        System.out.println("\n"+"Свои частоты");
	        
	        for (int i = 0; i < shchars.size(); i++) 
	        {
		           
	            if (deshnew.containsKey(shchars.get(i)))
	                System.out.print(deshnew.get(shchars.get(i)));
	            else
	                System.out.print(shchars.get(i));
	        }
}
		private static int bigChar(int i) 
		{
			if (i > 65 && i<=90 )
			{
				return i;
			}
	        else if (i > 90)
	        {
	        	 return i-26;
	        }
	        return i;
			
		}
		
		private static int littleChar(int i) 
		{
			if (i > 97 && i<=122 )
			{
				return i;
			}
	        else if (i > 122)
	        {
	        	 return i-26;
	        }
	        return i;
		}
}
	        
