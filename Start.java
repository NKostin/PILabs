package serverSimulation;

import java.io.FileWriter;
import java.io.IOException;

public class Start {
	public static void main (String []args) throws Exception{
		makePublicKey();
		letsgo();
	}
	
	public static void makePublicKey(){
		int p = 13;
		int q = 3;
		try {
			FileWriter writerPublicKey = new FileWriter("src/outputStream",false);
			String publicKey = Integer.toString(p)+" "+Integer.toString(q);
			writerPublicKey.write(publicKey);
			writerPublicKey.write(System.lineSeparator());
			writerPublicKey.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void clearStream(){
		String clear = "";
		try {
			FileWriter clearStream = new FileWriter("src/outputStream",false);
			clearStream.write(clear);
			clearStream.write(System.lineSeparator());
			clearStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void letsgo() throws IOException{
		Encryption encryption = new Encryption();
		encryption.takePublicKey();
		Registration reg = new Registration();
		reg.registration();
		clearStream();
		Chat chat = new Chat();
		Handler handler = new Handler();
		chat.start();
		handler.start();
	}
}
