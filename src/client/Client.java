package client;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public final static int FILE_SIZE = 602238600;
	
	private int port = 4444;
	private String IPAddress = "127.0.0.1";
	private String path = "./down/";
	private String filename = "downloaded";
	private String completeName;
	    
	private Socket socket;

	public void receiveFile() throws IOException {
		int bytesRead;
		int current = 0;
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		  
		try {
		    if (socket == null)
		    	createSocket();
		    
		    System.out.println("Connecting...");
		    // receive file
		    byte [] mybytearray  = new byte [FILE_SIZE];
		    InputStream is = socket.getInputStream();
		    completeName = path + filename;
		    fos = new FileOutputStream(completeName);
		    bos = new BufferedOutputStream(fos);
		    bytesRead = is.read(mybytearray,0,mybytearray.length);
		    current = bytesRead;

		    do {
		     	bytesRead = is.read(mybytearray, current, (mybytearray.length-current));
		      	if(bytesRead >= 0) current += bytesRead;
		    } while(bytesRead != -1);

		    bos.write(mybytearray, 0 , current);
		    bos.flush();
		    System.out.println("File " + filename + " downloaded (" + current + " bytes read)");
	    }
	    
		finally {
			if (fos != null) fos.close();
	    	if (bos != null) bos.close();
	    	if (socket != null) socket.close();
	    }
	  
	}
	  
	  public boolean createSocket() throws IOException{
		   try{
			  socket = new Socket(IPAddress, port);
		   } catch (UnknownHostException e1) {
			   System.err.println("Couldn't resolve host name - check IPAddress");
			   return false;
		   } catch (IllegalArgumentException e2) {
			   System.err.println("Port number outside range");
			   return false;
		   }
		   
		  return true;
	  }
	  
	  public void setIP(String address){
		  this.IPAddress = address;
	  }
	  
	  public void setPort(int port){
		  this.port = port;
	  }
	  
	  public Socket getSocket(){
		  return socket;
	  }
	  
	  public void setFilename(String filename){
		  this.filename = filename;
	  }
	  
}
