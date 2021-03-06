package serverdraw;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;

public class Input extends Thread{
	
	BufferedReader in;
	Vector<Integer> Yourvecx, Yourvecy;
	Vector<Character> Yourvecmode;
	
	public Input(BufferedReader in, Vector<Integer> Yourvecx, Vector<Integer> Yourvecy, Vector<Character> Yourvecmode){
		this.in=in;
		this.Yourvecx=Yourvecx;
		this.Yourvecy=Yourvecy;
		this.Yourvecmode=Yourvecmode;
	}
	
    @Override
	public void run(){
		while(true){
			String s=null;
			try{
				s=in.readLine();
				if(s==null) continue;
			} catch(IOException io){
				io.printStackTrace();
				System.err.println(io.getMessage());
			}
			if(!(s==null)){
				StringTokenizer st = new StringTokenizer(s);
				Integer X=new Integer(st.nextToken());
				Integer Y=new Integer(st.nextToken());
				Character M=new Character(st.nextToken().charAt(0));
				if(X==null) System.err.println("X is null");
				if(Y==null) System.err.println("Y is null");
				if(M==null) System.err.println("M is null");
				System.err.print(X.toString()+" "+Y.toString()+M.charValue());
				try{
					Yourvecx.add(X);
					Yourvecy.add(Y);
					Yourvecmode.add(M);
					System.err.println("  sizes: "+Yourvecx.size()+" "+Yourvecy.size()+" "+M.charValue());
				} catch(Exception e){
					e.printStackTrace();
					System.err.println(e.getMessage());
				}
				/*if(!Yourvecx.isEmpty() && !Yourvecy.isEmpty())
					try{
						System.err.println("Input: ("+Yourvecx.firstElement().toString()+","+Yourvecy.firstElement().toString()+")");
					} catch(Exception e){
						e.printStackTrace();
						System.err.println(e.getMessage());
					}*/
				//if(X.intValue()<0 || Y.intValue()<0) break;
			}
		}
		/*try{
			in.close();
		} catch(IOException io){
			io.printStackTrace();
			System.err.println(io.getMessage());
		}*/
	}
}
