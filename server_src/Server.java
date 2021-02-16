
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.ParseException;


public class Server {

	 public static void main(String arg[]) throws ParseException
	    {
		 
		
		 final int SERVER_PORT = 5000;
		 String localHostAddress="1.209.180.149";
	        //접속한 Client와 통신하기 위한 Socket
	        Socket socket = null;    
	        //채팅방에 접속해 있는 Client 관리 객체                
	        User user = new User();        
	        //Client 접속을 받기 위한 ServerSocket            
	        ServerSocket server_socket=null;    
	        
	        
	        
	        int count = 0;                            
	        Thread thread[]= new Thread[10];             
	        
	        try {
	            server_socket = new ServerSocket();
	            //Server의 메인쓰레드는 게속해서 사용자의 접속을 받음
	            
	            server_socket.bind(new InetSocketAddress(localHostAddress, SERVER_PORT));
	            while(true)
	            {
	                socket = server_socket.accept();

	                thread[count] = new Thread(new Receiver(user,socket));
	                thread[count].start();
	                count++;
	            }
	        }catch(Exception e) {};
	    }
	 
}