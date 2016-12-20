package serverSimulation;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;

public class Handler extends Thread {
	
	ArrayDeque<String> dequeString = new ArrayDeque<String>();
	long camein, cameout;
	private static String[] oneline;
	private static ArrayList<String> validinformations = new ArrayList<String>();
	private static ArrayList<String> invalidinformations = new ArrayList<String>();
	private static ArrayList<String> together = new ArrayList<String>();
	
	String currentString,rasshifrString,rasshifrLogin;
	String tempshifrString="";
	
	boolean notString,notReadLogin;
	
	FileWriter writerString;
	 
	 public  void run() {
		readLogin();
		notReadLogin = false;
		 try {
			writerString = new FileWriter("src/inputStream",true);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 while(true){
			 if(notReadLogin){
				 notString=true;
				 try {
					read();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 if(notString){
					 try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				 }else {
					 String goExit = "!exit";
					 Calendar time1 = Calendar.getInstance();
				     camein= time1.getTimeInMillis();
				     add(rasshifrString);
				     if (dequeString.peek()==null){
							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						
						}
						
					if (dequeString.peek().equals(goExit) ){
						break;
					}
					oneline = dequeString.peek().split("\n");
					treatment();
				 }
			 }
			 notReadLogin = true;
		 }
		
	 }
	 
	 private void read() throws IOException {
			String shifrString = "";
			String login=" ";

			 try {
				 BufferedReader bufferedReader = new BufferedReader(new FileReader("src/outputStream"));
				 if((shifrString = bufferedReader.readLine())!=null){
						if(tempshifrString.equals(shifrString)){
							
						} else {
							tempshifrString = shifrString;
							rasshifrString = Encryption.rasshifr(shifrString);
							if(rasshifrString.equals(login)){
								notString = true;
							} else
								notString = false;
							}
						}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			
					
		
	public void add(String stringFromFile) {
			dequeString.add(stringFromFile);
	}
	
	private void treatment() {
		String nullString = " ";
		String stream= "" ;
		whatType();
		String shifrStream="";
		for(int i=0;i<validinformations.size();i++){
			stream+=validinformations.get(i);
		}
		
		if(stream.equals(nullString)){
		
		} else {
			shifrStream = rasshifrLogin+stream;
			try {
				writerString.write(shifrStream);
				writerString.write(System.lineSeparator());
				writerString.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			completenessOfData();
			validinformations.clear();
			invalidinformations.clear();
		}
		
	}
	
	private void whatType() {
			
			while(dequeString.peek()!=null){
				try{
					int currentInt = Integer.parseInt(dequeString.peek());
					checkInt(currentInt);
				} catch (NumberFormatException e) {
					try {
						String currentString = dequeString.pop();
						checkString(currentString);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			
	}
	
	private void checkString(String inspectionString) throws IOException {
		String checkedString="";
		String notvalidString="";
		String checkString="";
		char [] charArray = inspectionString.toCharArray ();
		for (int i=0; i<charArray.length;i++){
			if (charArray[i]>= 'a' && charArray[i] <= 'z'||charArray[i]== ' ' || charArray[i]== '!'  || charArray[i]>= ','
					&& charArray[i] <= '.' || charArray[i]== '?' ){
				checkedString+=charArray[i];
			} else 
				notvalidString+=charArray[i];
		}
		validinformations.add(checkedString);
		if(notvalidString!=checkString)
			invalidinformations.add(notvalidString);
	}

	private void checkInt(int inspectionInt) {
		if (inspectionInt>-127 && inspectionInt<=128){
			validinformations.add(Integer.toString(inspectionInt));
			
		} else
			invalidinformations.add(Integer.toString(inspectionInt));
		dequeString.pop();
	}
	
	private void completenessOfData() {
		// TODO Auto-generated method stub
		ArrayList<Character> quantity = new ArrayList<Character>();
		ArrayList<Character> inTheEnd = new ArrayList<Character>();
		inTheEnd.clear();
		together.clear();
		
		for (int i=0; i<oneline.length; i++){
			char [] charArray = oneline[i].toCharArray ();
			for(int j=0; j<charArray.length;j++){
				quantity.add(charArray[j]);
			}
		}
		
		boolean invalidEmpty = (invalidinformations == null) || invalidinformations.isEmpty();
		together.addAll(validinformations);
		if(invalidEmpty){	
		} else 
			together.addAll(invalidinformations);
		
		validinformations.clear();
		invalidinformations.clear();
		
		for(int i=0; i<together.size();i++){
			char [] charArray = together.get(i).toCharArray ();
			for(int j=0; j<charArray.length;j++){
				inTheEnd.add(charArray[j]);
			}
		}
		//System.out.println("Вконце"+inTheEnd.size());
		if(quantity.size()!=0){
			System.out.println("Полнота данных="+((inTheEnd.size()/quantity.size())*100)+"%");
			Calendar time2 = Calendar.getInstance();
	    	cameout= time2.getTimeInMillis();
	    	//System.out.println("Time out"+cameout);
	    	long time = cameout - camein;
			System.out.println("Потрачено времени - "+time+"мс");
		}
		
	}
	
	private void readLogin() {
		 try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader("src/login"));
			rasshifrLogin = Encryption.rasshifr(bufferedReader.readLine())+": ";
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 try {
			 String clear="";
				FileWriter clearStream = new FileWriter("src/login",false);
				clearStream.write(clear);
				clearStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}	
	
	
