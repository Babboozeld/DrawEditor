package draweditor.frame.handlers;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import draweditor.components.Group;
import draweditor.components.IComponent;
import draweditor.decorators.BottomTextDecorator;
import draweditor.decorators.LeftTextDecorator;
import draweditor.decorators.RightTextDecorator;
import draweditor.decorators.TopTextDecorator;
import draweditor.figures.BasicFigure;
import draweditor.figures.EllipseFigure;
import draweditor.figures.RectangleFigure;
import draweditor.visitors.IComponentSerializeVisitor;

//https://www.caveofprogramming.com/java/java-file-reading-and-writing-files-in-java.html
public class FileHandler {

    public static List<String> LoadFile(String path) {
        List<String> lines = new ArrayList<String>();
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

            while ((line = bufferedReader.readLine()) != null) {
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
    public static Group ReadFile(String path) {
        List<PendingGroup> pandingGroups = new ArrayList<PendingGroup>();    
        List<String> FileTextLines = LoadFile(path);
        for (String line : FileTextLines) {
            String[] arguments = line.split(" ");
            switch (arguments[0]) {
                case "group":
                    pandingGroups.add(new PendingGroup(new Group(), Integer.parseInt(arguments[1])));
                    break;
                case "shape":
                    PendingGroup entry = pandingGroups.get(pandingGroups.size() - 1);
                    //check if list is filled
                    if (entry.getValue() == 0) {
                        Group filledGroup = entry.getGroup();
                        pandingGroups.remove(entry);
                        entry = pandingGroups.get(pandingGroups.size() - 1);
                        entry.getGroup().add(filledGroup);
                    }
                    //add shape to group
                    switch (arguments[1]) {
                        case "basicfigure":
                            switch (arguments[2]) {
                                case "rectangle":
                                    entry.fillGroup(BasicFigure.GetInstanceRectangle().SetAttributes(Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]), 
                                        Integer.parseInt(arguments[4]), Integer.parseInt(arguments[5]), new Color(Integer.parseInt(arguments[6]))));
                                    break;
                                case "ellipse":
                                    entry.fillGroup(BasicFigure.GetInstanceEllipse().SetAttributes(Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]), 
                                        Integer.parseInt(arguments[4]), Integer.parseInt(arguments[5]), new Color(Integer.parseInt(arguments[6]))));
                                    break;
                            }
                        case "rectangle":
                            entry.fillGroup(new RectangleFigure(Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]), 
                                Integer.parseInt(arguments[4]), Integer.parseInt(arguments[5]), new Color(Integer.parseInt(arguments[6]))));
                            break;
                        case "ellipse":
                            entry.fillGroup(new EllipseFigure(Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3]), 
                                Integer.parseInt(arguments[4]), Integer.parseInt(arguments[5]), new Color(Integer.parseInt(arguments[6]))));
                            break;
                    }
                    break;
                case "ornament":
                    List<IComponent> figures = pandingGroups.get(pandingGroups.size() - 1).getGroup().getFigures();
                    int lastIndex = figures.size() - 1;
                    switch (arguments[1]) {
                        case "bottom":
                            figures.set(lastIndex, new BottomTextDecorator(figures.get(lastIndex)));
                            break;
                        case "left":
                            figures.set(lastIndex, new LeftTextDecorator(figures.get(lastIndex)));
                            break;
                        case "right":
                            figures.set(lastIndex, new RightTextDecorator(figures.get(lastIndex)));
                            break;
                        case "top":
                            figures.set(lastIndex, new TopTextDecorator(figures.get(lastIndex)));
                            break;
                    }
                    break;
            }
           
        }
        while (pandingGroups.size() > 1) {
            PendingGroup filledGroup = pandingGroups.get(pandingGroups.size() - 1);
            pandingGroups.remove(filledGroup);
            PendingGroup entry = pandingGroups.get(pandingGroups.size() - 1);
            entry.getGroup().add(filledGroup.getGroup());
        }
        return pandingGroups.get(0).getGroup();
    }

    public static void SaveFile(IComponent figures, String path){
        try {
            List<String> serialized = new IComponentSerializeVisitor(figures).getSerialized();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path));

            for (String component : serialized) {
                bufferedWriter.write(component);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
            System.out.println("Succes writing to file '" + path + "'");
        }
        catch(IOException ex) {
            System.out.println("Error writing to file '" + path + "'");
            // ex.printStackTrace();
        }
    }
}