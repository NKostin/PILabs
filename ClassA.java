import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class ClassA {
	int grade;
	int modyl;
	
public void makefistkey(String filename,int pbgenerate,int pbmodyl) throws IOException{
	grade = (int)((Math.random()*10)+1);
	modyl = pbmodyl;
	long result=(long)Math.pow(pbgenerate,grade)%pbmodyl;
	FileWriter writer = new FileWriter(filename,false);
	String writeresult = Objects.toString(result, null);
	writer.write(writeresult);
	writer.write(System.lineSeparator());
	writer.close();
}
public int makesecondkey(String filename) throws IOException {
	Path path = Paths.get(filename);
	Scanner scanner = new Scanner(path);
	scanner.nextLine();
	String key = scanner.nextLine();
	int keya= Integer.parseInt(key);
	int resultA=(int)Math.pow(keya,grade)%modyl;
	return resultA;
}
}
