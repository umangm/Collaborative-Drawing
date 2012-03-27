package clientdraw;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Vector;

public class Draw extends Thread{
	int prevx;
	int prevy;
	Vector<Integer> Yourvecx;
	Vector<Integer> Yourvecy;
	Vector<Character> Yourvecmode;
        Color Yourcolor;
	Component c;
	Graphics g;
	
	public Draw(Component c, Vector<Integer> Yourvecx, Vector<Integer> Yourvecy, Vector<Character> Yourvecmode, Color Yourcolor){
		this.c=c;
		g=c.getGraphics();
		this.Yourvecx=Yourvecx;
		this.Yourvecy=Yourvecy;
		this.Yourvecmode=Yourvecmode;
                this.Yourcolor=Yourcolor;
                g.setColor(Yourcolor);
	}
	
    @Override
	public void run(){
		while(true){
			while(!(Yourvecx.isEmpty()) && !(Yourvecy.isEmpty()) && !(Yourvecmode.isEmpty())){
				int newx=Yourvecx.firstElement().intValue();
		    	int newy=Yourvecy.firstElement().intValue();
		    	char newmode=Yourvecmode.firstElement().charValue();
				if(newmode=='p' || newmode=='r'){
					prevx=newx;
                                    prevy=newy;
                                    Yourvecx.removeElementAt(0);
                                    Yourvecy.removeElementAt(0);
                                    Yourvecmode.removeElementAt(0);
                                    System.err.println("Draw :"+prevx+" "+prevy);
				}
				else{
                                    Yourvecx.removeElementAt(0);
                                    Yourvecy.removeElementAt(0);
                                    Yourvecmode.removeElementAt(0);
                                    int maxx=newx-prevx+10;
                                    if(10>maxx)maxx=10;
                                    int maxy=newy-prevy+10;
                                    if(10>maxy)maxy=10;
                                    if(newmode=='d')
                                        g.drawLine(newx, newy, prevx, prevy);
                                    else
                                        g.clearRect(newx-10, newy-10, 20, 20);
                                    System.err.println("Draw2 :"+prevx+" "+prevy);
                                    prevx=newx;
                                    prevy=newy;
				}
			}
		}
	
	}
}
