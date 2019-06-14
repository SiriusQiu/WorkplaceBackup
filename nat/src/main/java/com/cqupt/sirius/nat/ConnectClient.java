package com.cqupt.sirius.nat;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ConnectClient {
    /**
     * 参数约定：
     * args[0]：连接类型，UDP或者TCP
     * args[1]:目的ip
     * args[2]：目的端口号
     * args[3]:发送端端口号
     * args[4]：发送端绑定的ip
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String type = args[0]==null?null:args[0].toLowerCase();

        if(type == null){
            System.err.println("请输入建立连接的类型");
            Scanner scanner = new Scanner(System.in);
            type = scanner.next().toLowerCase();
        }
        if(type.equals("udp")){
            connetInUDP(args);
        }else if(type.equals("tcp")){
            connetInTCP(args);
        }else {
            System.err.println("所输入的类型无法识别:" + type );
        }
    }

    /**
     * 进行一次UDP连接，需要目的ip和目的端口号和发送端的端口号
     */
    public static void connetInUDP(String[] args) throws UnknownHostException {
        String host = args[1]==null?null:args[1];
        if(host == null){
            System.err.println("未输入访问的ip地址，请输入：");
            Scanner scanner = new Scanner(System.in);
            host = scanner.next();
        }
        InetAddress remoteAddr = InetAddress.getByName(host);
        int port = args[2]==null?-1:Integer.parseInt(args[2]);
        if(port == -1){
            System.err.println("未输入访问的端口，请输入：");
            Scanner scanner = new Scanner(System.in);
            port = scanner.nextInt();
        }
        System.out.println("使用UDP访问主机：" + remoteAddr + " ，端口号：" + port);
        int localPort = -1;
        if(args.length==4){
            localPort = Integer.parseInt(args[3]);
        }
        if(localPort == -1){
            localPort = 0;
            System.out.println("未输入绑定的本地端口，默认设为0");
        }
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(localPort);
            String datas = "hello from " + socket.getLocalAddress();
            byte[] data = datas.getBytes("UTF-8");
            DatagramPacket request = new DatagramPacket(data, data.length, remoteAddr, port);
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            socket.send(request);
            socket.receive(response);
            String result = new String(response.getData(), 0, response.getLength(), "UTF-8");
            System.out.println(result);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(socket!=null){
                socket.close();
            }
        }
    }

    /**
     * 进行一次TCP连接，需要目的ip和目的端口号还有发送端的ip和发送端的端口号
     */
    public static void connetInTCP(String[] args) throws IOException {
        String host = args[1]==null?null:args[1];
        if(host == null){
            System.err.println("未输入访问的ip地址，请输入：");
            Scanner scanner = new Scanner(System.in);
            host = scanner.next();
        }
        InetAddress remoteAddr = InetAddress.getByName(host);
        int port = args.length<3?-1:Integer.parseInt(args[2]);
        if(port == -1){
            System.err.println("未输入访问的端口，请输入：");
            Scanner scanner = new Scanner(System.in);
            port = scanner.nextInt();
        }
        System.out.println("使用TCP访问主机：" + remoteAddr + " ，端口号：" + port);
        Socket socket = null;
        try{
            String localhost = null;
            int localPort = 0;
            if(args.length == 5){
                localhost = args[3];
                localPort = Integer.parseInt(args[4]);
            }
            if(localhost == null || localPort == 0){
                System.out.println("未绑定的本地ip或本地端口号");
                SocketAddress socketAddress = new InetSocketAddress(host, port);
                socket = new Socket();
                socket.connect(socketAddress);
            }else {
                InetAddress localaddr = InetAddress.getByName(localhost);
                socket = new Socket(remoteAddr, port, localaddr, localPort);
            }
            OutputStream outToServer = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);
            String data = "Hello from " + socket.getLocalSocketAddress();
            out.writeUTF(data);
            InputStream inFromServer = socket.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("服务器响应： " + in.readUTF());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(socket!=null){
                socket.close();
            }
        }
    }
}
