import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.util.StringTokenizer;

class CompilerWindow extends javax.swing.JFrame implements ActionListener
{
    InbuiltKeywords accessFun;
    private String strOutput;
    private javax.swing.JMenu compileMenu;
    private javax.swing.JMenu debugMenu;
    private javax.swing.JMenuItem exitMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JPanel inputPannel;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JMenu logMenu;
    private javax.swing.JPanel mainPannel;
    private javax.swing.JMenuItem newMenu;
    private javax.swing.JMenuItem openMenu;
    private javax.swing.JPanel outputPnl;
    private javax.swing.JMenu runMenu;
    private javax.swing.JMenu stopMenu;
    private javax.swing.JTextArea txtInputCode;
    private javax.swing.JTextArea txtOutput;
    private javax.swing.JMenuItem itmDebugMenu;
    private javax.swing.JMenuItem itmRunMenu;
    private javax.swing.JMenuItem itmStopMenu;
    private javax.swing.JMenuItem itmCompileMenu;
    private javax.swing.JMenuItem itmLogMenu;

     public CompilerWindow() {
        accessFun=new InbuiltKeywords();
        initComponents();
    }

   private void initComponents() {

        mainPannel = new javax.swing.JPanel();
        inputPannel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInputCode = new javax.swing.JTextArea();
        outputPnl = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtOutput = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        newMenu = new javax.swing.JMenuItem();
        openMenu = new javax.swing.JMenuItem();
        exitMenu = new javax.swing.JMenuItem();
        compileMenu = new javax.swing.JMenu();
        runMenu = new javax.swing.JMenu();
        stopMenu = new javax.swing.JMenu();
        debugMenu = new javax.swing.JMenu();
        logMenu = new javax.swing.JMenu();

        itmCompileMenu = new javax.swing.JMenuItem("Compile File");
        itmRunMenu = new javax.swing.JMenuItem("Run File");
        itmStopMenu = new javax.swing.JMenuItem("Stop Execution");
        itmDebugMenu = new javax.swing.JMenuItem("Debug File");
        itmLogMenu = new javax.swing.JMenuItem("Log");

        setVisible(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Compiler4u");
        setName("frame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1000, 700));

        txtInputCode.setBackground(new java.awt.Color(245, 219, 219));//(255,255,255=>for white color)
        txtInputCode.setColumns(20);
        txtInputCode.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        txtInputCode.setRows(5);
        txtInputCode.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(txtInputCode);

        javax.swing.GroupLayout inputPannelLayout = new javax.swing.GroupLayout(inputPannel);
        inputPannel.setLayout(inputPannelLayout);
        inputPannelLayout.setHorizontalGroup(
            inputPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
        );
        inputPannelLayout.setVerticalGroup(
            inputPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
        );

        txtOutput.setEditable(false);
        txtOutput.setColumns(20);
        txtOutput.setRows(5);
        jScrollPane2.setViewportView(txtOutput);

        javax.swing.GroupLayout outputPnlLayout = new javax.swing.GroupLayout(outputPnl);
        outputPnl.setLayout(outputPnlLayout);
        outputPnlLayout.setHorizontalGroup(
            outputPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        outputPnlLayout.setVerticalGroup(
            outputPnlLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainPannelLayout = new javax.swing.GroupLayout(mainPannel);
        mainPannel.setLayout(mainPannelLayout);
        mainPannelLayout.setHorizontalGroup(
            mainPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(inputPannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(outputPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        mainPannelLayout.setVerticalGroup(
            mainPannelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPannelLayout.createSequentialGroup()
                .addComponent(inputPannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputPnl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        fileMenu.setText("File");
        fileMenu.add(jSeparator1);

        newMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newMenu.setText("New File");
       
        fileMenu.add(newMenu);

        openMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenu.setText("Open File");
        
        fileMenu.add(openMenu);

        exitMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        exitMenu.setText("Exit");
        fileMenu.add(exitMenu);

        jMenuBar1.add(fileMenu);

        compileMenu.setText("Compile");
        compileMenu.add(itmCompileMenu);
        jMenuBar1.add(compileMenu);

        runMenu.setText("Run");
        runMenu.add(itmRunMenu);
        jMenuBar1.add(runMenu);

        stopMenu.setText("Stop");
        stopMenu.add(itmStopMenu);
        jMenuBar1.add(stopMenu);

        debugMenu.setText("Debug");
        debugMenu.add(itmDebugMenu);
        jMenuBar1.add(debugMenu);

        logMenu.setText("Log");
        logMenu.add(itmLogMenu);
        jMenuBar1.add(logMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPannel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();

        //*******adding action listener:************
        newMenu.addActionListener(this);
        openMenu.addActionListener(this);
        exitMenu.addActionListener(this);
        
        itmDebugMenu.addActionListener(this);
        itmRunMenu.addActionListener(this);
        itmCompileMenu.addActionListener(this);
        itmLogMenu.addActionListener(this);
        itmStopMenu.addActionListener(this);

        //*****************ending*************************

    }// </editor-fold>//GEN-END:initComponents

    public void actionPerformed(ActionEvent evt)
    {
        Object clicked=evt.getSource(); 
        if(clicked==newMenu)
        {
            
        }

        //compiling code-----------
        if(clicked==itmCompileMenu)
        {
            strOutput=accessFun.startCompilation(txtInputCode.getText().toString(),this);   
                txtOutput.setText("\n"+strOutput);    
            
            
        }
        //end compling-------------
    }//end of action listener
  
 
    
}