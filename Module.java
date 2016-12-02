import java.awt.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;

public class Module extends Thread {

	private static ArrayList<String> validinformations = new ArrayList<String>();
	private static ArrayList<String> invalidinformations = new ArrayList<String>();
	private static ArrayList<String> together = new ArrayList<String>();
	long camein, cameout;
	private static String[] oneline;
	ArrayDeque<String> dequeString = new ArrayDeque<String>();
	
	public void add(String stringFromFile) {
		dequeString.add(stringFromFile);
	}
	


	@Override
	public void run() {
		
		while (true){
			Calendar time1 = Calendar.getInstance();
	    		camein= time1.getTimeInMillis();
			if (dequeString.peek()==null){
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			
			}
			
			if (dequeString.peek() == ""){
				break;
			}
			
			while (dequeString.peek()!=null){
				oneline = dequeString.pop().split("\n");
			}
			
			try {
				startWork();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 
		}
	}
	
	public void startWork() throws IOException {
		
		whatType();
		completenessOfData();
//		System.out.println("Valid");
//		for (int i=0; i<validinformations.size();i++){
//			System.out.println(validinformations.get(i));
//		}
		validinformations.clear();
//		System.out.println("InValid");
//		for (int i=0; i<invalidinformations.size();i++){
//			System.out.println(invalidinformations.get(i));
//		}
		invalidinformations.clear();
	}

	private void whatType(){
		for(int i=0; i<oneline.length; i++){
			try{
				int currentInt = Integer.parseInt(oneline[i]);
				checkInt(currentInt);
			} catch (NumberFormatException e) {
				try {
					checkString(oneline[i]);
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
			if (charArray[i]>= 'a' && charArray[i] <= 'z'){
				checkedString+=charArray[i];
			} else 
				notvalidString+=charArray[i];
		}
		
		validinformations.add(checkedString);
		if(notvalidString!=checkString)
			invalidinformations.add(notvalidString);
	}

	private void checkInt(int inspectionInt) {
		if (inspectionInt>-127 && inspectionInt<128){
			validinformations.add(Integer.toString(inspectionInt));
			
		} else
			invalidinformations.add(Integer.toString(inspectionInt));
	}
	
	private void completenessOfData() {
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
		System.out.println("Полнота данных="+((quantity.size()/inTheEnd.size())*100)+"%");
		Calendar time2 = Calendar.getInstance();
    	cameout= time2.getTimeInMillis();
    	//System.out.println("Time out"+cameout);
    	long time = cameout - camein;
		System.out.println("Потрачено времени - "+time+"ìñ");
	}

}
