import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Translator {
	String line;
	String[] splitArr = new String[2];
	int lineCount;
	public Translator() {
		try {
			BufferedReader br;
			PrintWriter p = new PrintWriter("64_bit.asm", "UTF-8");
			br = new BufferedReader(new FileReader(
					"/home/sameer/3_sem/Cloud_Computing/Assignment2/Translator/32_bit.asm"));
			
			lineCount=0;
			p.println(".Ltext0:");
			p.println("\t.globl	main");
			p.println("\tmain:");
			p.println("\t.LFB0:");
			
			while ((line = br.readLine()) != null) {
				if (lineCount < 4) {
					lineCount = lineCount + 1;
					continue;

				}
				splitArr = line.trim().split(" ");
				if (splitArr[0].equals("mov") || splitArr[0].equals("add")
						|| splitArr[0].equals("add")) {
					p.print(splitArr[0]+"q ");

					
					
					splitArr[1]=splitArr[1].replace("eax", "%rax");
					splitArr[1]=splitArr[1].replace("ebx", "%rcx");
					p.println(splitArr[1]);
				} else {
					
					line = line.replace("eax", "%rax");
					line = line.replace("push", "pushq");
					line = line.replace("ebx", "%rcx");
					p.println(line);
				}

			}
			br.close();
			p.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {
		new Translator();
	}

}