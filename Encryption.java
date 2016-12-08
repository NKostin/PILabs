package serverSimulation;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Encryption {
	private static int n,k,e,d;
	
	private static ArrayList<Character> shchars = new ArrayList<Character>();
	private static ArrayList<Character> rasshchars = new ArrayList<Character>();
	private static Map<Character, Integer> code = new HashMap<Character, Integer>();
	
	public void takePublicKey(){
		makeCode();
		Path path = Paths.get("src/outputStream");
		Scanner scanner;
		try {
			scanner = new Scanner(path);
			int p = scanner.nextInt();
			int q = scanner.nextInt(); 
			n = p*q;
			k=(p-1)*(q-1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		e=5;
		d=53;
		//foundE();
		//foundD();
		
	}
	
	public void makeCode(){
		
		char letter = 'a';
		for(int point=0;point<26;point++){
			code.put(letter,point ); 
			letter++;
		}
		letter = '0';
		for(int point=26;point<36;point++){
			code.put(letter,point ); 
			letter++;
		}
		letter = ',';
		for(int point=36;point<40;point++){
			code.put(letter,point ); 
			letter++;
		}

	}

	public void foundE(){
		int tempk=k;
		int tempe;
	do {
		tempe = (int)((Math.random()*20)+2);
		e=tempe;
		 while (tempe != tempk) {
	            if (tempe > tempk) {
	            	tempe = tempe - tempk;
	            } else {
	            	tempk = tempk - tempe;
	            }
	        }
		} while(tempe!=1&&e<k&&simple());
	}
	
	public boolean simple(){
		boolean simple = false;
		int n=19;
        int i;           
        for(i=2; i<n ;i++)
        {
            if (e%i==0) 
            {
            	
            } else {
            	simple = true;
                return simple; 
            }
        }
		return simple;
	}
	
	public void foundD(){
		
		BigInteger foundD, e, k, willbe1, mull;
		do{
			this.d=(int)((Math.random()*100)+10);
			foundD = BigInteger.valueOf(this.d);
			e = BigInteger.valueOf(this.e);
			k= BigInteger.valueOf(this.k);
			mull = foundD.multiply(e);
			willbe1 = mull.mod(k);
		} while(willbe1.intValue()!=1);
	}
	
	public String shifr(String informations){
		char [] charArray = informations.toCharArray ();
		String shifrInformations="";
		int countedSRA=0;
		 for(int i=0;i<charArray.length;i++){
			char tempchar =  charArray[i];
			if(tempchar >= 'a' && tempchar <= 'z'|| tempchar >= '0' && tempchar <= '9'|| tempchar >= ',' && tempchar <= '/'){
				int tempvalue = code.get(tempchar);
				countedSRA = shifrRSA(tempvalue, e);
				for (Character key : code.keySet()) {
					if(code.get(key)==(countedSRA)){
						shifrInformations+=key;
						break;
					}
				}
				
			}else 
				shifrInformations+=tempchar;
		 }
		 return shifrInformations;
	}
	
	public String rasshifr(String informations){
		int countedSRA=0;
		String shifrInformations="";
		char [] charArray = informations.toCharArray();
		 for(int i=0;i<charArray.length;i++){
			 char tempchar =  charArray[i];
			 if(tempchar >= 'a' && tempchar <= 'z'|| tempchar >= '0' && tempchar <= '9'|| tempchar >= ',' && tempchar <= '/'){
					int tempvalue = code.get(tempchar);
					countedSRA = shifrRSA(tempvalue, d);
					for (Character key : code.keySet()) {
						if(code.get(key)==(countedSRA)){
							shifrInformations+=key;
							break;
						}
					}
					
				} else 
					shifrInformations+=tempchar;
		 }
		
		return shifrInformations;
	}
	
	public int shifrRSA(int tempvalue, int exponent){
		BigInteger  bigtempvalue, exp,modyl, numberRSA;
		bigtempvalue = BigInteger.valueOf(tempvalue);
		modyl = BigInteger.valueOf(n);
		exp = BigInteger.valueOf(exponent);
		numberRSA = bigtempvalue.modPow(exp, modyl);
		return numberRSA.intValue();
	}
}
