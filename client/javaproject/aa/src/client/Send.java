package client;

import java.io.*;

public class Send implements Runnable{
    DataOutputStream out;
    BufferedReader in2 = new BufferedReader(new InputStreamReader(System.in));
    public Send(DataOutputStream out)
    {
        this.out = out;
    }
    public void run()
    {
        while(true)
        {
            
            try
            {
                String msg = in2.readLine();    //Ű����κ��� �Է��� ����
                out.writeUTF(msg);                //������ ����
            }catch(Exception e) {}
        }
    }
}