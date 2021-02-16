
import java.net.*;
import java.io.*;

public class Receiver implements Runnable{

    Socket socket;
    DataInputStream in;
    String name;
    User user = new User();
    Filter filter = new Filter(); // 전송된 메세지 필터링
    String weather; // 불러온 날씨

    public Receiver(User user,Socket socket) throws Exception
    {
        this.user = user;
        this.socket = socket;
        //접속한 Client로부터 데이터를 읽어들이기 위한 DataInputStream 생성
        in = new DataInputStream(socket.getInputStream());
        //최초 사용자로부터 닉네임을 읽어들임
        this.name = in.readUTF();
        //사용자 추가해줍니다.
        user.AddClient(name, socket);
    }

    public void run()
    {
        try
        {
            
            while(true)
            {
                String msg = in.readUTF();   // 뿌려줄 날씨 정보 나오게
                weather = filter.total(msg);
                weather = weather.replace("null", "결과값 없음");
                user.sendMsg(weather , name);
                
            }
        }catch(Exception e) {
            
            user.RemoveClient(this.name);
        }        
    }
}