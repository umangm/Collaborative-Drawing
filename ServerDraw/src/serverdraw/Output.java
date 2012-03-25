package serverdraw;

import java.io.PrintWriter;
import java.util.Vector;

public class Output extends Thread{
	
	PrintWriter out;
	Vector<Integer> Myvecx,Myvecy;
	Vector<Character> Myvecmode;
	
	public Output(PrintWriter out, Vector<Integer> Myvecx, Vector<Integer> Myvecy, Vector<Character> Myvecmode){
		this.out=out;
		this.Myvecx=Myvecx;
		this.Myvecy=Myvecy;
		this.Myvecmode=Myvecmode;
	}
	
	public void run(){
		while(true){
        	int a=0;
        	int b=0;
		    if(!Myvecx.isEmpty() && !Myvecy.isEmpty() && !Myvecmode.isEmpty()){
		    	int x=Myvecx.firstElement().intValue();
		    	int y=Myvecy.firstElement().intValue();
		    	char m=Myvecmode.firstElement().charValue();
		    	Myvecx.removeElementAt(0);
		    	Myvecy.removeElementAt(0);
		    	Myvecmode.removeElementAt(0);
		    	out.println(x+" "+y+" "+m);
		    	System.err.println("Output: ("+x+","+y+") Mode: "+m);
		    	a=x;

		    	b=y;
		    	//if(x<0 || y<0) break;
		    }
		    //if(a<0 || b<0) break;
		}
        
		/*try{
			out.close();
		} catch(Exception io){
			io.printStackTrace();
			System.err.println(io.getMessage());
		}*/
	}
}
