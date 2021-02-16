
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
	        //������ Client�� ����ϱ� ���� Socket
	        Socket socket = null;    
	        //ä�ù濡 ������ �ִ� Client ���� ��ü                
	        User user = new User();        
	        //Client ������ �ޱ� ���� ServerSocket            
	        ServerSocket server_socket=null;    
	        
	        
	        
	        int count = 0;                            
	        Thread thread[]= new Thread[10];             
	        
	        try {
	            server_socket = new ServerSocket();
	            //Server�� ���ξ������ �Լ��ؼ� ������� ������ ����
	            
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