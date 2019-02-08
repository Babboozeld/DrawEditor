package draweditor.figures;

import java.util.List;

public class Group implements IFigure {

    public final static String FigureType = "group";

    public int count;
    public Group(int count){
        this.count = count;
    }

    public List<String> Serialize() {
        //return List.ArrayList(){LayerType, this.count};new ArrayList<String>();
        return null;
    }

}