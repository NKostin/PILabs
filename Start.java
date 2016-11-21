import java.io.IOException;

public class Start {
public static void main(String args[]) throws IOException{
	String filename = "E:/a/java/RSA/src/key";
	String textname = "E:/a/java/RSA/src/Text";
	int generate = 15;
	int modyl = 26;
	ClassA a = new ClassA();
	ClassB b = new ClassB();
	a.makefistkey(filename,generate, modyl);
	b.makefistkey(filename,generate, modyl);
	int secretCodeA = a.makesecondkey(filename);
	int secretCodeB = b.makesecondkey(filename);
	if(secretCodeA!=secretCodeB){
		System.out.print("Ошибка! Секретные коды не совпадают");
	}else {
		System.out.println("Шифруем часть А");
		Shifr shifr = new Shifr();
		shifr.shifrText(textname,secretCodeA);
		shifr.rasshifrText(secretCodeB);
		
	}
	System.out.println();
	System.out.println("**********************************");
	System.out.println("Шифруем часть B");
	RSAHelper vz = new RSAHelper();
	
	vz.rsaShifr();
	vz.shifr(textname);
	vz.rasshifr();
}
}
