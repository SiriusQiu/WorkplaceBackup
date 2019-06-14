package com.cqupt.sirius.nat;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ConnectServer {
    public static void main(String[] args) {
        int port = -1;
        if (args.length < 1) {
            System.out.println("请输入要监听的端口");
            Scanner scanner = new Scanner(System.in);
            port = scanner.nextInt();
        } else {
            port = Integer.parseInt(args[0]);
        }
        Thread tcp = new Thread(new TCPListener(port));
        Thread udp = new Thread(new UDPListener(port));
        tcp.start();
        udp.start();
    }
}
class TCPListener implements Runnable{
    private int port;
    public TCPListener(int port){
        this.port = port;
    }
    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("等待tcp远程连接，端口号为:" + port);
            while (true){
                Socket socket = serverSocket.accept();
                DataInputStream in = new DataInputStream(socket.getInputStream());
                System.out.println("------------------------------------");
                System.out.println("远程tcp主机地址：" + socket.getRemoteSocketAddress());
                System.out.println(in.readUTF());
                System.out.println("------------------------------------");
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("来自：" + socket.getLocalSocketAddress() + " 的响应");
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class UDPListener implements Runnable{
    private int port;
    public UDPListener(int port){
        this.port = port;
    }
    @Override
    public void run() {
        try {
            System.out.println("等待udp远程连接，端口号为:" + port);
            DatagramSocket socket = new DatagramSocket(port);
            while (true){
                DatagramPacket request = new DatagramPacket(new byte[1024], 1024);
                socket.receive(request);
                InetAddress remoteAddr = request.getAddress();
                SocketAddress localaddr = socket.getLocalSocketAddress();
                byte[] receive = request.getData();
                String datas = "来自：" + localaddr + " 的响应";
                byte[] data = datas.getBytes("UTF-8");
                DatagramPacket response = new DatagramPacket(data, data.length, remoteAddr, request.getPort());
                socket.send(response);
                System.out.println("------------------------------------");
                System.out.println("远程udp主机地址：" + remoteAddr + " 端口号：" + request.getPort());
                System.out.println(new String(receive,"UTF-8"));
                System.out.println("------------------------------------");
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
