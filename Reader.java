import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Reader extends Thread {
	BufferedReader bufferedReader; 
	 Module module;
	 Reader(Module module){
		 this.module = module;
	 }

	
	 
	    @Override
	public void run() {
	    	try {
				bufferedReader = new BufferedReader(new FileReader("src/text"));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        while (true){
	            try {
	            	 module.add(readPartFromFile());
	            	
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	            try {
	                Thread.sleep(200);
	            } catch (InterruptedException e) {
	                System.out.println("File is empty");
	              
	              //  String s = null;
	                //module.add(s);
	                break;
	            }
	          	
	        }
	    }
	    public String readPartFromFile() throws Exception{
	    
	        String part="";
	        for (int i = 0; i < 3; i++) {
	            String oneLine = bufferedReader.readLine();
	            if (oneLine==null) {
	            	interrupt();
	                return "";
	            }
	            part+=oneLine+"\n";
	        }
	       // System.out.println(part);
	        //part.trim();
	      
	return part;
	    }
}

