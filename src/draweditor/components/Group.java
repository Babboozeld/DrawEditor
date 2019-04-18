package draweditor.components;

import java.util.ArrayList;
import java.util.List;

import draweditor.decorators.AbstractDecorator;
import draweditor.visitors.IComponentVisitor;

public class Group implements IComponent {

    //public final static String FigureType = "group";

    private List<IComponent> figures;

    public Group() {
        figures = new ArrayList<IComponent>();
    }

    public void add(IComponent figure) {
        figures.add(figure);
    }

    public void remove(IComponent figure) {
        figures.remove(figure);
    }

    public List<IComponent> getFigures() {
        return figures;
    }

    public int getSize() {
        return figures.size();
    }

    public List<String> Serialize() {
        // return List.ArrayList(){LayerType, this.count};new ArrayList<String>();
        return null;
    }

    public Group findGroup(IComponent figure) {
        for (IComponent fig : figures) {
            if (fig.equals(figure)) {
                return this;
            }
            if (fig instanceof AbstractDecorator) {
                fig = ((AbstractDecorator)fig).component;
            } 
            if (fig instanceof Group) {
                Group result = ((Group)fig).findGroup(figure);
                if (result != null)
                    return result;
            }
        }
        return null;
    }

    @Override
    public void accept(IComponentVisitor iComponentVisitor) {
        iComponentVisitor.visit(this);
    }

    @Override
    public IComponent findSelected(int x, int y) {
        for (IComponent figure : figures) {
            IComponent selected = figure.findSelected(x, y);
            if (selected != null) return figure;
        }
        return null;
    }
}