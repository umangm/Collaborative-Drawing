package serverdraw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import javax.swing.*;

public class MouseEventDemo extends JPanel implements Runnable, MouseListener, MouseMotionListener {

    Vector<Integer> Myvecx;
    Vector<Integer> Myvecy;
    Vector<Character> Myvecmode;
    Vector<Integer> Yourvecx;
    Vector<Integer> Yourvecy;
    Vector<Character> Yourvecmode;
    Color Mycolor;
    Color Yourcolor;
    BlankArea blankArea;
    JTextArea textArea;
    static final String NEWLINE = System.getProperty("line.separator");
    int xPressed,yPressed;
    int xReleased,yReleased;
    int xDragged,yDragged;
    boolean clearmode=false;

	
    public MouseEventDemo(Vector<Integer> Myvecx,Vector<Integer> Myvecy,Vector<Character> Myvecmode,Vector<Integer> Yourvecx,Vector<Integer> Yourvecy,Vector<Character> Yourvecmode, Color Mycolor, Color Yourcolor){
        //super(new GridLayout(3,1));
        this.Myvecx=Myvecx;
        this.Myvecy=Myvecy;
        this.Myvecmode=Myvecmode;
        this.Yourvecx=Yourvecx;
        this.Yourvecy=Yourvecy;
        this.Yourvecmode=Yourvecmode;
        this.Mycolor=Mycolor;
        this.Yourcolor=Yourcolor;
        //this.setBackGround(Color.WHITE);
        blankArea = new BlankArea(Color.WHITE);
        add(blankArea);
        add(blankArea);
        textArea = new JTextArea();
        textArea.setEditable(false);
        //JScrollPane scrollPane = new JScrollPane(textArea);
        //scrollPane.setVerticalScrollBarPolicy(
          //    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane.setPreferredSize(new Dimension(800, 100));
        //add(scrollPane);
        add(textArea);
        
        blankArea.addMouseListener(this);
        blankArea.addMouseMotionListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(800, 600));
        setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

    }
    
    @Override
    public void run() {
        /* Use an appropriate Look and Feel */
        try {
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            //UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(Myvecx,Myvecy,Myvecmode,Yourvecx,Yourvecy,Yourvecmode, Mycolor, Yourcolor);
            }
        });
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI(Vector<Integer> Myvecx,Vector<Integer> Myvecy,Vector<Character> Myvecmode,Vector<Integer> Yourvecx,Vector<Integer> Yourvecy,Vector<Character> Yourvecmode, Color Mycolor, Color Yourcolor) {
        //Create and set up the window.
        JFrame frame = new JFrame("Collaborative Drawing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Create and set up the content pane.
        JComponent newContentPane = new MouseEventDemo(Myvecx,Myvecy,Myvecmode,Yourvecx,Yourvecy,Yourvecmode,Mycolor,Yourcolor);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        //newContentPane.setBackground(Color.WHITE);
        frame.setBackground(Color.WHITE);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        (new Draw(newContentPane, Yourvecx, Yourvecy, Yourvecmode, Yourcolor)).start();
    }
    
    
    void eventOutput(String eventDescription, MouseEvent e, int x, int y) {
        //textArea.setText(eventDescription + " detected on "
          //      + e.getComponent().getClass().getName()
            //    + "." + NEWLINE);
        //textArea.setCaretPosition(textArea.getDocument().getLength());
        
        
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        
        int button=e.getButton();
        //if(button==MouseEvent.BUTTON1) eventOutput("Button1",e,1,1); //left
        //if(button==MouseEvent.BUTTON2) eventOutput("Button2",e,2,2);
        //if(button==MouseEvent.BUTTON3) eventOutput("Button3",e,3,3); //right
        
        if(button==MouseEvent.BUTTON3 && clearmode==false) clearmode=true;
    
    	xPressed = e.getX();
        yPressed = e.getY();

        eventOutput("Mouse pressed (# of clicks: "
                + e.getClickCount() +"("+xPressed+","+yPressed+")"+ ")", e, xPressed, yPressed);
                
       	Integer X=new Integer(xPressed);
        Integer Y=new Integer(yPressed);
        Character P=new Character('p');
        Myvecx.add(X);
        Myvecy.add(Y);
        Myvecmode.add(P);
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        
        int button=e.getButton();
        if(button==MouseEvent.BUTTON3 && clearmode==true) clearmode=false;
        
    	xPressed = e.getX();
        yPressed = e.getY();

    
        eventOutput("Mouse released (# of clicks: "
                + e.getClickCount() +"("+xPressed+","+yPressed+")"+ ")", e, e.getX(), e.getY());
                
        Integer X=new Integer(xPressed);
        Integer Y=new Integer(yPressed);
        Character R= new Character('r');
        Myvecx.add(X);
        Myvecy.add(Y);
        Myvecmode.add(R);
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        eventOutput("Mouse entered"+"("+e.getX()+","+e.getY()+")", e, e.getX(), e.getY());
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        eventOutput("Mouse exited"+"("+e.getX()+","+e.getY()+")", e, e.getX(), e.getY());
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        eventOutput("Mouse clicked (# of clicks: "
                + e.getClickCount() +"("+e.getX()+","+e.getY()+")"+ ")", e, e.getX(), e.getY());
    }
    
    public void mouseMoved(MouseEvent e){
    	eventOutput("Mouse clicked ("+e.getX()+","+e.getY()+")"+ ")", e, e.getX(), e.getY());
    }
    
    @Override
    public void mouseDragged(MouseEvent e){
    	int ex=e.getX();
    	int ey=e.getY();
        
        int button=e.getButton();
    
    	Graphics g = getGraphics();
        g.setColor(Mycolor);
        xDragged = ex;
        yDragged = ey;
        if(clearmode==false)
            g.drawLine(xPressed, yPressed, xDragged, yDragged);
        else
            g.clearRect(ex-10, ey-10, 20, 20);
        xPressed = ex;
        yPressed = ey;
        

    	//eventOutput("Mouse clicked ("+e.getX()+","+e.getY()+")"+ ")", e, ex, ey);
    	
    	Integer X=new Integer(xPressed);
        Integer Y=new Integer(yPressed);
        Character D= new Character('d');
        if(clearmode==true) D=new Character('c');
        Myvecx.add(X);
        Myvecy.add(Y);
        Myvecmode.add(D);
    }
}
