package clientdraw;

import java.awt.Color;
import java.util.Vector;

public class CollaborativeClient{

	public CollaborativeClient(int port, String IP ){
	
		Vector<Integer> Myvecx=new Vector<Integer>(200,200);
		Vector<Integer> Myvecy=new Vector<Integer>(200,200);
		Vector<Character> Myvecmode=new Vector<Character>(200,200);
		
		Vector<Integer> Yourvecx=new Vector<Integer>();
		Vector<Integer> Yourvecy=new Vector<Integer>();
		Vector<Character> Yourvecmode=new Vector<Character>(200,200);
		
		(new MouseEventDemo(Myvecx,Myvecy,Myvecmode,Yourvecx,Yourvecy,Yourvecmode, Color.RED, Color.BLACK)).run();
		new Client(port, IP, Myvecx,Myvecy,Myvecmode,Yourvecx,Yourvecy,Yourvecmode);
		
	}
}
