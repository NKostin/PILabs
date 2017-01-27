package serverSimulation;

public class Listener {
	
	public static String listenString(String readString){
		String shifrString = Encryption.shifr(readString);
		return shifrString;
		
	}

}
