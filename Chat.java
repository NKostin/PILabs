package serverSimulation;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Chat extends Thread {
	 Scanner in = new Scanner(System.in);
	 
	 boolean exit = true;
	 public  void run(){
		 System.out.println("��������������� � ���!");
		 System.out.println("��� ������ �� ���� ������� !exit");
		 while(exit){
			 scanString();
			 try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 System.out.print("����������!");
	 }
	 public  void scanString(){
		 String exit="!exit";
			 System.out.print(">");
			 String scan = in.nextLine();
			 if(scan.equals(exit)){
				 this.exit=false;
			 } else {
			 
				 String shifrString = Encryption.shifr(scan);
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
