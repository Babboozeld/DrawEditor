package draweditor.helpers;

import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import draweditor.figures.*;

//https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
public class FileHandler {

    public static List<String> LoadFile(String path){
        List<String> lines = new ArrayList<String>();
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                lines.add(line);
            }   
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");                
        }
        catch(IOException ex) {
            System.out.println("Error reading file '" + path + "'");                  
            //ex.printStackTrace();
        }
        return lines;
    }

    public static List<IFigure> ReadFile(String path){
        List<IFigure> figures = new ArrayList<IFigure>();
        List<String> FileTextLines = LoadFile(path);
        for (String line : FileTextLines) {
            String[] arguments = line.split(" ");
            switch (arguments[0]){
                case "group":
                    figures.add(new Group(Integer.parseInt(arguments[1])));
                    break;
                case "rectangle":
                    figures.add((IFigure)new Rectangle(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4])));
                    break;
            }
        }

        return figures;
    }

    public static void SaveFile(List<IFigure> figures, String path){
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));

            for (IFigure figure : figures) {
                bufferedWriter.write(String.join(" ", figure.Serialize()));
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + path + "'");
            // ex.printStackTrace();
        }
    }

}