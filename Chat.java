package serverSimulation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Chat extends Thread {
	 Scanner in = new Scanner(System.in);
	 
	 boolean exit = true;
	 public  void run(){
		 System.out.println("Добропожаловать в чат!");
		 System.out.println("для выхода из чата введите !exit");
		 while(exit){
			 scanString();
			 try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 System.out.print("Досвидания!");
	 }
	 public  void scanString(){
		 String exit="!exit";
			 System.out.print(">");
			 String scan = in.nextLine();
			 if(scan.equals(exit)){
				 this.exit=false;
			 } else {
			 
				 String shifrString = Listener.listenString(scan);
				 try {
					 FileWriter writerChatString = new FileWriter("src/outputStream",false);
					 writerChatString.write(shifrString);
					 writerChatString.write(System.lineSeparator());
					 writerChatString.flush();
				 } catch (IOException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				 }
			 }
	 }
}
