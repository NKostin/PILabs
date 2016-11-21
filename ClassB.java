import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class ClassB {
	int grade;
	int modyl;
	
public void makefistkey(String filename,int pbgenerate,int pbmodyl) throws IOException{
	grade = (int)((Math.random()*10)+1);
	modyl = pbmodyl;
	long result=(long)Math.pow(pbgenerate,grade)%pbmodyl;
	FileWriter writer = new FileWriter(filename,true);
	String writeresult = Objects.toString(result, null);
	writer.write(writeresult);
	writer.write(System.lineSeparator());
	writer.close();
}
public int makesecondkey(String filename) throws IOException {
	Path path = Paths.get(filename);
	Scanner scanner = new Scanner(path);
	String key = scanner.nextLine();
	int keyb= Integer.parseInt(key);
	int resultB=(int)Math.pow(keyb,grade)%modyl;
	return resultB;
}
}
