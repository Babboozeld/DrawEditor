package draweditor.figures;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class GroupFigure implements IFigure {

    public final static String FigureType = "group";

    private List<IFigure> figures;

    public GroupFigure() {
        figures = new ArrayList<IFigure>();
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

    public GroupFigure findGroup(IFigure figure) {
        for (IFigure fig : figures) {
            if (fig.equals(figure)) return this;
            if (fig instanceof GroupFigure){
                GroupFigure result = ((GroupFigure)fig).findGroup(figure);
                if (result != null) return result; 
            }
        }
        return null;
    }
    
}