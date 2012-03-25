package serverdraw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server extends Thread {

	Vector<Integer> Myvecx,Myvecy,Yourvecx,Yourvecy;
	Vector<Character> Myvecmode,Yourvecmode;
	ServerSocket serverSocket = null;
	Socket clientSocket = null;
        int port;
        String clientIP;
	
	public Server(int port, String clientIP,Vector<Integer> Myvecx,Vector<Integer> Myvecy,Vector<Character> Myvecmode,Vector<Integer> Yourvecx,Vector<Integer> Yourvecy,Vector<Character> Yourvecmode){
		this.Myvecx=Myvecx;
		this.Myvecy=Myvecy;
		this.Myvecmode=Myvecmode;
		this.Yourvecx=Yourvecx;
		this.Yourvecy=Yourvecy;
		this.Yourvecmode=Yourvecmode;
                this.port=port;
                this.clientIP=clientIP;
		this.start();
	}
	
    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: "+port+".");
            System.exit(1);
        }

        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        
        PrintWriter out=null;
        BufferedReader in=null; 

        try{
        	out = new PrintWriter(clientSocket.getOutputStream(), true);
        	in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        
        (new Output(out, Myvecx, Myvecy, Myvecmode)).start();
        (new Input(in, Yourvecx, Yourvecy, Yourvecmode)).start();
        
	}
	
     public void close(){   
       try{
		    clientSocket.close();
		    serverSocket.close();
        }catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
    }
}
