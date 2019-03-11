package draweditor.frame.handlers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import draweditor.DrawEditor;

public class KeyHandler implements KeyListener {

    private ArrayList<Integer> keysDown = new ArrayList<Integer>();
    private DrawEditor draweditor = DrawEditor.getInstance();
    public boolean free = true;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!keysDown.contains(e.getKeyCode())){
            keysDown.add(new Integer(e.getKeyCode()));
            checkShortCuts();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysDown.remove(new Integer(e.getKeyCode()));
    }

    private void checkShortCuts(){
        // for (Integer var : keysDown) {
        //      System.out.println(var);
        // }
        if (free == true) {
            if (keysDown.contains(17)) {
                if (keysDown.contains(90)){
                    //free = false;
                    if (keysDown.contains(16)) {
                        draweditor.reverseExecute();
                        // SwingUtilities.invokeLater(new Runnable() {
                        //     public void run() {
                        //         free = true;
                        //     }
                        // });  
                    } else {
                        draweditor.unexecute(); 
                        // SwingUtilities.invokeLater(new Runnable() {
                        //    public void run() {
                        //        free = true;
                        //    }
                        // });  
                    }
                }
            }
        }  
    }
}