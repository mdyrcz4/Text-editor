package texteditor;

import javax.swing.JFrame;

public class TextEditor {

    public static void main(String[] args){
        
        textEditorGui obj = new textEditorGui();
        obj.setBounds(0, 0, 800, 600);
        obj.setTitle("TextEditor");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
