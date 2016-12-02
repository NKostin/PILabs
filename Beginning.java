import java.io.IOException;

public class Beginning extends Thread {
	
	public static void main(String args[]) throws IOException{
		Module modules = new Module();
		Reader read = new Reader(modules);
		//Thread readThread = new Thread(read);
		//Thread modulesThread = new Thread(modules);
		read.start();
		modules.start();
	//	modulesThread.start();
	}

}
