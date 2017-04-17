package texteditor;

//imports
import java.awt.Color;
import java.awt.FileDialog;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import javax.swing.text.JTextComponent;

public class textEditorGui extends javax.swing.JFrame {
    
    private javax.swing.JMenuItem copyText;
    private javax.swing.JMenuItem cutText;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exit;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newFile;
    private javax.swing.JMenuItem openFile;
    private javax.swing.JMenuItem pasteText;
    private javax.swing.JMenuItem saveFile;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextArea textArea;
    private javax.swing.JPopupMenu popUpMenu;
    //booleans vars to check status of the file
    private boolean fileSaved = false;
    private boolean toExit = false;
    private boolean toNewFile = false;
    String fileName;
    
    Clipboard clipboard = getToolkit().getSystemClipboard();
    
    public textEditorGui() {
        initComponents();
    }
    // Yes and No buttons' actions
    private void yesButtonActionPerformed(java.awt.event.ActionEvent evt){
        saveFileActionPerformed(evt);
        popUpMenu.setVisible(false);
        fileSaved=true;
    }
    private void noButtonActionPerformed(java.awt.event.ActionEvent evt){
        newFileActionPerformed(evt);
        popUpMenu.setVisible(false);
        if(toExit){
            System.exit(0);
            toExit=false;
        }
        if(toNewFile){
            textArea.setText("");
            setTitle("TextEditor");
            toNewFile = false;
            fileSaved = false;
        }
    }
    //creating popupMenu method
    private JPopupMenu createPopUpMenu(){
        popUpMenu = new JPopupMenu();
        
        JMenuItem text = new JMenuItem();
        text.setText("Would you like to save this file?");
        popUpMenu.add(text);
        JMenuItem yes = new JMenuItem("Yes");
        yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesButtonActionPerformed(evt);
            }
        });
        popUpMenu.add(yes);
        JMenuItem no = new JMenuItem("No");
        no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noButtonActionPerformed(evt);
            }
        });
        popUpMenu.add(no);
        
        
        popUpMenu.setSize(300, 400);
        
        popUpMenu.setLocation(menuBar.getLocation().x+500, menuBar.getLocation().y+200);
       
        
        setVisible(false);
        
        return popUpMenu;
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        searchButton = new javax.swing.JButton();
        searchField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newFile = new javax.swing.JMenuItem();
        openFile = new javax.swing.JMenuItem();
        saveFile = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutText = new javax.swing.JMenuItem();
        copyText = new javax.swing.JMenuItem();
        pasteText = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        popUpMenu = createPopUpMenu();
        
        
        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(searchButton)
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
                .addContainerGap())
        );

        fileMenu.setText("File");

        newFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newFile.setText("New");
        newFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newFileActionPerformed(evt);
            }
        });
        fileMenu.add(newFile);

        openFile.setText("Open");
        openFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileActionPerformed(evt);
            }
        });
        fileMenu.add(openFile);

        saveFile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveFile.setText("Save");
        saveFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFileActionPerformed(evt);
            }
        });
        fileMenu.add(saveFile);

        exit.setText("Exit");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        fileMenu.addSeparator();
        fileMenu.add(exit);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        cutText.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        cutText.setText("Cut");
        cutText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutTextActionPerformed(evt);
            }
        });
        editMenu.add(cutText);

        copyText.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        copyText.setText("Copy");
        copyText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyTextActionPerformed(evt);
            }
        });
        editMenu.add(copyText);

        pasteText.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        pasteText.setText("Paste");
        pasteText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasteTextActionPerformed(evt);
            }
        });
        editMenu.add(pasteText);

        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    //Action for buttons from File Menu
    private void newFileActionPerformed(java.awt.event.ActionEvent evt) {
        toNewFile= true;
        if(!fileSaved){
            popUpMenu.setVisible(true);
        }else{
            textArea.setText("");
            setTitle("TextEditor");
            fileSaved = false;
        }
        
    }
    private void openFileActionPerformed(java.awt.event.ActionEvent evt) {
        FileDialog fileDialog = new FileDialog(textEditorGui.this,"Open File", FileDialog.LOAD);
        fileDialog.setVisible(true);
        
        if(fileDialog.getFile() != null){
            fileName = fileDialog.getDirectory() + fileDialog.getFile();
            setTitle(fileName);
        }
        try{
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            StringBuilder sb = new StringBuilder();
            String line = null;
            
            while((line = reader.readLine())!=null){
                sb.append(line + "\n");
                textArea.setText(sb.toString());
            }
            reader.close();
        }catch(IOException e){
            System.out.println("File not found");
        }
    }
    private void exitActionPerformed(java.awt.event.ActionEvent evt) {
        toExit = true;
        if(!fileSaved){
            popUpMenu.setVisible(true);
        }else{
         System.exit(0);
        }

    }
    private void saveFileActionPerformed(java.awt.event.ActionEvent evt) {
        FileDialog fileDialog = new FileDialog(textEditorGui.this,"Save File",FileDialog.SAVE);
        fileDialog.setVisible(true);
        
        if(fileDialog.getFile()!=null){
            fileName = fileDialog.getDirectory() + fileDialog.getFile();
            setTitle(fileName);
        }
        try{
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(textArea.getText());
            setTitle(fileName);
            fileWriter.close();
            fileSaved=true;
        }catch(IOException e){
            System.out.println("File not found");
        }
    }
    //Action for buttons from Edit Menu
    private void cutTextActionPerformed(java.awt.event.ActionEvent evt) {
        String cutString = textArea.getSelectedText();
        StringSelection cutSelection = new StringSelection(cutString);
        clipboard.setContents(cutSelection, cutSelection);
        textArea.replaceRange("", textArea.getSelectionStart(), textArea.getSelectionEnd());
        fileSaved=false;
    }
    private void copyTextActionPerformed(java.awt.event.ActionEvent evt) {
        String copyText = textArea.getSelectedText();
        StringSelection copySelection = new StringSelection(copyText);
        clipboard.setContents(copySelection, copySelection);
        fileSaved=false;
    }
    private void pasteTextActionPerformed(java.awt.event.ActionEvent evt) {
        try{
            Transferable pasteText = clipboard.getContents(textEditorGui.this);
            String sel = (String) pasteText.getTransferData(DataFlavor.stringFlavor);
            textArea.replaceRange(sel, textArea.getSelectionStart(), textArea.getSelectionEnd());
            fileSaved=false;
        }catch(Exception e){
            System.out.println("Paste function's gone wrong!");
        }
    }
    // search button's action
    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        if(textArea.getText().equals("")){
        System.out.println("Nothing to search!");
        }else{
            if(!searchField.getText().equals("")){
                searchTextArea(textArea,searchField.getText());
            }else removeHighLight(textArea);
        }
    }
    //highlighter class and method to remove highlighted text 
    class myHighlighter extends DefaultHighlighter.DefaultHighlightPainter{
        
        public myHighlighter(Color color) {
            super(color);
        }
        
    }
    DefaultHighlighter.HighlightPainter highlighter = new myHighlighter(Color.yellow);
    public void removeHighLight(JTextComponent textComp){
        Highlighter removeHighlighter = textComp.getHighlighter();
        Highlighter.Highlight[] remove = removeHighlighter.getHighlights();
        
        for(int i=0;i<remove.length;i++){
            if(remove[i].getPainter() instanceof myHighlighter){
                removeHighlighter.removeHighlight(remove[i]);
            }
            
        }
    }
    //search method
    public void searchTextArea(JTextComponent textComp, String textString){
        removeHighLight(textComp);
        try{
            Highlighter hilight = textComp.getHighlighter();
            Document doc = textComp.getDocument();
            String text = doc.getText(0, doc.getLength());
            
            int pos = 0;
            
            while((pos = text.toUpperCase().indexOf(textString.toUpperCase(),pos))>=0){
                hilight.addHighlight(pos, pos+textString.length(), highlighter);
                pos+=textString.length();
            }
        }catch(Exception e){
            
        }
    }
}
