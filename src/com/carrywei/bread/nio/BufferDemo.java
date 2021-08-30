package com.carrywei.bread.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * @Author wushuwei
 * @Description
 * @Date 2021/8/25
 **/
public class BufferDemo {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer);
        buffer.put((byte) 'a');
        System.out.println(buffer);
        buffer.flip();
        System.out.println(buffer);
    }
}
