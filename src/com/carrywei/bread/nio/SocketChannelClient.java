package com.carrywei.bread.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @Author wushuwei
 * @Description
 * @Date 2021/8/20
 **/
public class SocketChannelClient {
    public static final int BUFFER_BYTE_COUNT = 8;

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1",9999));
        while (true) {
            String s = scanner.nextLine();
            ByteBuffer buffer = ByteBuffer.allocate(BUFFER_BYTE_COUNT);
            byte[] strBytes = s.getBytes();
            for (int i = 0; i < strBytes.length;) {
                byte[] bufferByte = new byte[BUFFER_BYTE_COUNT];
                int length = strBytes.length - i < BUFFER_BYTE_COUNT ? strBytes.length - i : BUFFER_BYTE_COUNT;
                System.arraycopy(strBytes, i, bufferByte, 0, length);
                buffer.put(bufferByte);
                buffer.flip();
                socketChannel.write(buffer);
                i = (i + 1) * BUFFER_BYTE_COUNT;
                buffer.clear();
            }
        }

    }
}
