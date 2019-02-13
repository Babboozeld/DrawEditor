package draweditor.figures;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Group implements IFigure {

    public final static String FigureType = "group";

    // public int count;
    private List<IFigure> figures;

    public Group(int count) {
        // this.count = count;
        figures = new ArrayList();
    }

    public void add(IFigure figure) {
        figures.add(figure);
    }

    public void remove(IFigure figure) {
        figures.remove(figure);
    }

    public List<IFigure> getFigures(){
        return figures;
    }

    public List<String> Serialize() {
        // return List.ArrayList(){LayerType, this.count};new ArrayList<String>();
        return null;
    }

    public void draw(Graphics g) {
        for(IFigure figure : figures) {
            figure.draw(g);
        }
    }

    public void move(int dx, int dy) {
        for (IFigure figure : figures) {
            figure.move(dx, dy);
        }
    }

    public Group findGroup(IFigure figure) {
        for (IFigure fig : figures) {
            if (fig.equals(figure)) return this;
            if (fig instanceof Group){
                Group result = ((Group)fig).findGroup(figure);
                if (result != null) return result; 
            }
        }
        return null;
    }
    
}