import java.util.*;
import java.io.*;
public class PhotoToTex() {
	
	public static final String[] OPERTIONS = {"+", "-", "x", "*", "÷"};
	public static final String[] RELATIONS = {"=", "≤", "≥"};
	public static final String[] SETS =	{"∩", "∪", "∈", "∉"};
	
	private String math;
	private String newLineWithSeparation;
	private ArrayList<String> list;
	
	public static void main(String[] args) {
		String dir = "This PC\Welson P20 Pro\Internal shared storage\Download";
        String tex_file_name = "new_tex";
        list = new ArrayList<String>;
        for (String[] str1: OPERATIONS) {
        	list.add(str1);
        }
        for (String[] str2: RELATIONS) {
             list.add(str2);
        }
		for (String[] str3: SETS) {
             list.add(str3);
        }
        
       	newLineWithSeparation = System.getProperty("line.separator")+System.getProperty("line.separator");
        math = "\\documentclass{article}" + newLineWithSeparation;
        math += "\\usepackage{amsfonts}" + newLineWithSeparation;
        math += "\\usepackage{amssymb}" + newLineWithSeparation;
        math += "\\usepackage{amsmath}" + newLineWithSeparation;
        math += "\\usepackage{mathtools}" + newLineWithSeparation;
        math += "\\begin{document}" + newLineWithSeparation;
        readFile(args[0]);
        math += "\\end{document}";
        
        writeFile(dir, tex_file_name);
	}
	
	public void readFile(String fileName) {
        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                convertToLatex(line);
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" + fileName + "'");                  
            // Or we could just do this: ex.printStackTrace();
        }
	}
	
	public void writeFile(String dir, String tex_file_name) {
		FileWriter writer = null;
        try {
            writer = new FileWriter(dir + "\\" + tex_file_name + ".tex", false);
            writer.write(math, 0, math.length());
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	
	public void convertToLatex(String line) {
		String[] splited = line.trim().split("\\s+");
		for (String str: splited) {
			if (list.contains(str)) {
				switch (str) {
					case "+":  math += "\\plus" + " ";
								break;
					case "-": math += "\\minus" + " ";
								break;
					case "x": math += "\\times" + " ";
								break;		
					case "*": math += "\\ast" + " ";
								break;
					case "÷": math += "\\div" + " ";
								break;
					case "=": math += "\\equals" + " ";
								break;	
					case "≤": math += "\\leq" + " ";
								break;
					case "≥": math += "\\geq" + " ";
								break;	
					case "∩": math += "\\cap" + " ";
								break;
					case "∪": math += "\\cup" + " ";
								break;
					case "∈": math += "\\in" + " ";
								break;
					case "∉": math += "\\notin" + " ";
							break;																				
				}
			} else {
				math += str + " ";
			}
		}
		math = math.trim() + newLineWithSeparation;
	}

}