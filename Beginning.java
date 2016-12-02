import java.io.IOException;

public class Beginning extends Thread {
	
	public static void main(String args[]) throws IOException{
		Module modules = new Module();
		Reader read = new Reader(modules);
		read.start();
		modules.start();
	}

}
