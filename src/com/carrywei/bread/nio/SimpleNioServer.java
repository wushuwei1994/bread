package com.carrywei.bread.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author wushuwei
 * @Description
 * @Date 2021/8/19
 **/
public class SimpleNioServer {

    public static class NioServerTask implements Runnable{
        private SocketChannel channel;

        public NioServerTask(SocketChannel channel) {
            this.channel = channel;
        }

        @Override
        public void run() {
            if (channel != null) {
                System.out.println("接受到新的socketChannel," + System.currentTimeMillis());
//                channel.configureBlocking(false);
                ByteBuffer buffer = ByteBuffer.allocate(8);
                try {
                    int read = channel.read(buffer);
//                    System.out.println("read = " + read);
                    while (read != -1) {
//                        System.out.println("after read：" + buffer);
                        buffer.flip();
                        System.out.println("after flip：" + buffer);
                        byte[] result = new byte[8];
                        buffer.get(result);
//                        System.out.println("after get：" + buffer);
                        System.out.print(new String(result));
                        buffer.clear();
//                        System.out.println("after clear：" + buffer);
                        read = channel.read(buffer);
//                        System.out.println("read = " + read);
                    }
                } catch (Exception e) {
                    System.out.println("异常了！");
                    e.printStackTrace();
                }

            } else {
                System.out.println("未收到新的socketChannel");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
//        serverSocketChannel.configureBlocking(false);
        while (true) {
            SocketChannel channel = serverSocketChannel.accept();
            NioServerTask nioServerTask = new SimpleNioServer.NioServerTask(channel);
            new Thread(nioServerTask).start();

        }

    }
}
