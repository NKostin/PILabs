import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RSAHelper {
	int n;
	int k;
	int e;
	int d;
	private static ArrayList<Character> shchars = new ArrayList<Character>();
	private static ArrayList<Character> rasshchars = new ArrayList<Character>();
	private static Map<Character, Integer> code = new HashMap<Character, Integer>();
	
	public void makeCode(){
		char letter = 'a';
		for(int point=1;point<27;point++){
			code.put(letter,point ); 
			letter++;
		}
	}
	public void rsaShifr(){
		int p = 13;
		int q = 2;
		n = p*q;
		k=(p-1)*(q-1);
		foundE();
		foundD();
	}
	public void foundE(){
		int tempk=k;
		int tempe;
	do {
		tempe = (int)((Math.random()*10)+2);
		e=tempe;
		 while (tempe != tempk) {
	            if (tempe > tempk) {
	            	tempe = tempe - tempk;
	            } else {
	            	tempk = tempk - tempe;
	            }
	        }
		} while(tempe!=1&&e<k);
	}
	public void foundD(){
		int found;
	do{
		d=(int)((Math.random()*10)+1);
		found = (d*e)%k;
	} while(found!=1);
}
	public void shifr(String name){
		makeCode();
		ArrayList<Character> chars = new ArrayList<Character>();
		
		Shifr shifr = new Shifr();
		chars = shifr.readText(name);
		long shifrSRA=0;
		 for(int i=0;i<chars.size();i++){
			char tempchar =  chars.get(i);
			if(tempchar >= 'a' && tempchar <= 'z'){
				int tempvalue = code.get(tempchar);
				shifrSRA = (long)Math.pow(tempvalue,e)%n;
				for (Character key : code.keySet()) {
					if(code.get(key)==(shifrSRA)){
						shchars.add(key);
						break;
					}
				}
				
			} else {
				shchars.add(tempchar);
			}
		 }
		 System.out.println("Зашифрованный текст");
		 for(int i=0;i<shchars.size();i++){
			 System.out.print(shchars.get(i));
		 }
	}
	public void rasshifr(){
		long shifrSRA=0;
		 for(int i=0;i<shchars.size();i++){
			 char tempchar =  shchars.get(i);
				if(tempchar >= 'a' && tempchar <= 'z'){
					int tempvalue = code.get(tempchar);
					shifrSRA = (long)Math.pow(tempvalue,d)%n;
					for (Character key : code.keySet()) {
						if(code.get(key)==(shifrSRA)){
							rasshchars.add(key);
							break;
						}
					}
					
				} else {
					rasshchars.add(tempchar);
				}
		 }
		 System.out.println();
		 System.out.println("Рашифрованный текст");
		 for(int i=0;i<rasshchars.size();i++){
			 System.out.print(rasshchars.get(i));
		 }
	}
}