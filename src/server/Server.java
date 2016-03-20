package server;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
	
	public final static int SOCKET_PORT = 4444;  // you may change this
//	public final static int BUFFER_SIZE = 65536;
	public final static String FILE_TO_SEND = "";
	
	  public static void main (String [] args ) throws IOException {
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;
	    OutputStream os = null;
	    ServerSocket servsock = null;
	    Socket sock = null;
	    Date start;
	    Date end;
	    
	    try {
	    	servsock = new ServerSocket(SOCKET_PORT);
	    	while (true) {
	        System.out.println("Waiting...");
		        try {
		        	while(true){
		        		
		        	sock = servsock.accept();
		        	System.out.println("Accepted connection : " + sock);
		        	// send file
		        	start = new Date();
		        	File myFile = new File(FILE_TO_SEND);
		        	byte [] mybytearray = new byte [(int)myFile.length()];
			        fis = new FileInputStream(myFile);
			        bis = new BufferedInputStream(fis);
			        bis.read(mybytearray, 0, mybytearray.length);
			        os = sock.getOutputStream();
			        os.write(mybytearray, 0, mybytearray.length);
			        os.flush();
			        end = new Date();
			        System.out.println("Done. Started at: "+start+"\nEnded at: "+end);
			        
		        	}
		        }
		        finally {
		        	if (fis != null) fis.close();
		        	if (bis != null) bis.close();
		        	if (os != null) os.close();
		        	if (sock!=null) sock.close();
		        }
		        
		    }
	    }
	    
	    finally {
//	      if (servsock != null) servsock.close();
	    	servsock.close();
	    }
	    
	  }
}
