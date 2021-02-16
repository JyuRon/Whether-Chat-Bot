package client;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
public class Client {
	public static void main(String[] arg)
    {
        Socket socket = null;            //Server�� ����ϱ� ���� Socket
        DataInputStream in = null;        //Server�κ��� �����͸� �о���̱� ���� �Է½�Ʈ��
        BufferedReader in2 = null;        //Ű����κ��� �о���̱� ���� �Է½�Ʈ��

        DataOutputStream out = null;    
        
        try {

            socket = new Socket("1.209.180.149",5000);    //������ ����
            
            
            in = new DataInputStream(socket.getInputStream());            
            in2 = new BufferedReader(new InputStreamReader(System.in)); 
            out = new DataOutputStream(socket.getOutputStream());        

            //ä�ÿ� ��� �� �г����� �Է¹���
            System.out.print("�г����� �Է����ּ��� : ");
            String data = in2.readLine();            
            
            //������ �г����� ����
            out.writeUTF(data);               
            //����ڰ� ä�� ������ �Է� �� ������ �����ϱ� ���� ������ ���� �� ����
            Thread th = new Thread(new Send(out));
            th.start();
        }catch(IOException e) {
        	
        	System.out.println("������� �ù�");
        }
        try {
            //Ŭ���̾�Ʈ�� ���� ������� �����κ��� ������ �о���̴� �͸� �ݺ�.
            while(true)
            {
                String str2 = in.readUTF();        
                System.out.println(str2);
            }
        }catch(IOException e) {}
    }
}