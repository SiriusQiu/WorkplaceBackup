package com.cqupt.sirius.nat;
import java.net.*;
import java.io.*;

public class GreetingClient
{
    public static void main(String [] args) throws UnknownHostException {
        //String serverName = args[0];
        String serverName = "119.23.181.184";
        InetAddress.getLocalHost();
        //int port = Integer.parseInt(args[1]);
        int port = 6606;
        try
        {
            System.out.println("连接到主机：" + serverName + " ，端口号：" + port);
            //InetAddress localaddr = InetAddress.getLocalHost();
            InetAddress localaddr = InetAddress.getByAddress(new byte[]{int2bytes(113),(byte)(251 & 0xff),(byte)(220 & 0xff),(byte)(190 & 0xff)});
            System.out.println(localaddr);
            Socket client = new Socket(serverName, port);
            System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.writeUTF("Hello from " + client.getLocalSocketAddress());
            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应： " + in.readUTF());

            client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    public static byte int2bytes(int addr){
        return (byte) (addr & 0xff);
    }
}