package draweditor.figures;

import java.awt.Color;

import draweditor.components.IComponent;

public abstract class AbstractFigure implements IComponent {

    public int left;
    public int top;
    public int width;
    public int height;
    public Color color;

    @Override
    public IComponent findSelected(int x, int y) {
        return (left <= x && x <= left+width && top <= y && y <= top+height) ? this : null;
    }
}