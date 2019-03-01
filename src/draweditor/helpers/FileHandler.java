package draweditor.helpers;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import draweditor.figures.*;

//https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
public class FileHandler {

    public static List<String> LoadFile(String path) {
        List<String> lines = new ArrayList<String>();
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                lines.add(line);
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + path + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + path + "'");
            // ex.printStackTrace();
        }
        return lines;
    }

    //https://stackoverflow.com/questions/3527216/accessing-the-last-entry-in-a-map
    public static GroupFigure ReadFile(String path) {
        NavigableMap<GroupFigure, Integer> pandingGroups = new TreeMap<GroupFigure, Integer>();

        List<String> FileTextLines = LoadFile(path);
        for (String line : FileTextLines) {
            String[] arguments = line.split(" ");

            if (arguments[0] == "group"){
                pandingGroups.put(new GroupFigure(), Integer.parseInt(arguments[1]));
            } else {
                IFigure pandingFigure = null;
                switch (arguments[0]) {
                    case "rectangle":
                    pandingFigure = (IFigure)new RectangleFigure(Integer.parseInt(arguments[1]), Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]), Integer.parseInt(arguments[4]), Color.getColor(arguments[5]));
                        break;
                }

                Entry<GroupFigure, Integer> entry = pandingGroups.lastEntry();
                GroupFigure deepestGroup = entry.getKey();
                deepestGroup.add(pandingFigure);
                int entrysLeft = entry.getValue();
                if (entrysLeft == 1){
                    if (pandingGroups.size() == 1){
                        return deepestGroup;
                    } else {
                        pandingGroups.remove(entry.getKey());
                        pandingGroups.lastEntry().getKey().add(deepestGroup);
                    }
                } else {
                    entrysLeft--;
                    entry.setValue(entrysLeft);
                }
            }
        }

        return null;
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