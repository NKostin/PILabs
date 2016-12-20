package serverSimulation;

public class Listener {
	
	public String listenString(String readString){
		String shifrString = Encryption.shifr(readString);
		return shifrString;
		
	}

}
