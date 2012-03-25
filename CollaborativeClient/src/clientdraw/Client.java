package clientdraw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

public class Client extends Thread {

    Vector<Integer> Myvecx,Myvecy,Yourvecx,Yourvecy;
    Vector<Character> Myvecmode,Yourvecmode;
    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;
    int port;
    String IP;

	
	public Client(int port, String IP, Vector<Integer> Myvecx,Vector<Integer> Myvecy,Vector<Character> Myvecmode,Vector<Integer> Yourvecx,Vector<Integer> Yourvecy,Vector<Character> Yourvecmode){
		this.Myvecx=Myvecx;
		this.Myvecy=Myvecy;
		this.Myvecmode=Myvecmode;
		this.Yourvecx=Yourvecx;
		this.Yourvecy=Yourvecy;
		this.Yourvecmode=Yourvecmode;
                this.port=port;
                this.IP=IP;
		this.start();
	}
	
    @Override
    public void run() {

        try {
            socket = new Socket(IP, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: "+IP+".");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: "+IP+".");
            System.exit(1);
        }
        
       	(new Output(out, Myvecx, Myvecy, Myvecmode)).start();
        (new Input(in, Yourvecx, Yourvecy, Yourvecmode)).start();
        
	}
	
	public void close(){
        
        try{
        	socket.close();
        } catch(IOException io){
        	io.printStackTrace();
        	System.err.println(io.getMessage());
        }
    }
}
