package com.carrywei.bread.nio;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Author wushuwei
 * @Description
 * @Date 2021/8/18
 **/
public class ChannelDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\test.txt", "rw");
        // 建立文件通道
        FileChannel channel = randomAccessFile.getChannel();
        // 分配16字节大小的字节缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 从通道中读取内容到缓冲区，并返回读取的字节数
        int bytesRead = channel.read(buffer);
        while (bytesRead != -1) {
            System.out.println("Read:" + buffer);
            // flip操作，将缓冲区的pos重置为0
            buffer.flip();
            System.out.println("Read:" + buffer);
            while (buffer.hasRemaining()) {
                // 获取单个字节内容，并单个移动pos
                System.out.println((char) buffer.get());
                System.out.println(buffer);
            }
            buffer.clear();

            bytesRead = channel.read(buffer);
        }

        randomAccessFile.close();
    }
}
