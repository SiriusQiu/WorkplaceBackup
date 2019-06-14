package com.cqupt.sirius.nat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DaytimeUDPClient {

    private final static int PORT = 6606;
    private static final String HOSTNAME = "119.23.181.184";


    public static void main(String[] args) {
        //传入0表示让操作系统分配一个端口号
        try (DatagramSocket socket = new DatagramSocket(6606)) {
            socket.setSoTimeout(10000);
            InetAddress host = InetAddress.getByName(args[0]);
            System.out.println(host);
            //指定包要发送的目的地
            byte[] data = "hello".getBytes();
            DatagramPacket request = new DatagramPacket(data, data.length, host, Integer.parseInt(args[1]));

            //为接受的数据包创建空间
            DatagramPacket response = new DatagramPacket(new byte[1024], 1024);
            socket.send(request);
            socket.receive(response);
            String result = new String(response.getData(), 0, response.getLength(), "ASCII");
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}