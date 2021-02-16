
import java.net.*;
import java.io.*;

public class Receiver implements Runnable{

    Socket socket;
    DataInputStream in;
    String name;
    User user = new User();
    Filter filter = new Filter(); // ���۵� �޼��� ���͸�
    String weather; // �ҷ��� ����

    public Receiver(User user,Socket socket) throws Exception
    {
        this.user = user;
        this.socket = socket;
        //������ Client�κ��� �����͸� �о���̱� ���� DataInputStream ����
        in = new DataInputStream(socket.getInputStream());
        //���� ����ڷκ��� �г����� �о����
        this.name = in.readUTF();
        //����� �߰����ݴϴ�.
        user.AddClient(name, socket);
    }

    public void run()
    {
        try
        {
            
            while(true)
            {
                String msg = in.readUTF();   // �ѷ��� ���� ���� ������
                weather = filter.total(msg);
                weather = weather.replace("null", "����� ����");
                user.sendMsg(weather , name);
                
            }
        }catch(Exception e) {
            
            user.RemoveClient(this.name);
        }        
    }
}