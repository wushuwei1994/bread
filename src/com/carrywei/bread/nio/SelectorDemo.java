package com.carrywei.bread.nio;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author wushuwei
 * @Description
 * @Date 2021/8/19
 **/
public class SelectorDemo {
    public static void main(String[] args) throws IOException {
        //Selector（选择器）是Java NIO中能够检测一到多个NIO通道，并能够知晓通道是否为诸如读写事件做好准备的组件。
        // 这样，一个单独的线程可以管理多个channel，从而管理多个网络连接。
        Selector selector = Selector.open();

        SocketChannel channel = SocketChannel.open();
        // Channel设置为非阻塞模式
        channel.configureBlocking(false);

        // 向Selector注册通道，并设置感兴趣事件，OP_READ表示读就绪事件，并返回SelectionKey对象
        SelectionKey selectionKey = channel.register(selector, SelectionKey.OP_READ);

        //通道触发了一个事件意思是该事件已经就绪。所以，某个channel成功连接到另一个服务器称为“连接就绪”。一个server socket channel准备好接收新进入的连接称为“接收就绪”。
        // 一个有数据可读的通道可以说是“读就绪”。等待写数据的通道可以说是“写就绪”。分别用四个常量表示：
        //SelectionKey.OP_CONNECT 连接就绪
        //SelectionKey.OP_ACCEPT 接受就绪
        //SelectionKey.OP_READ 读就绪
        //SelectionKey.OP_WRITE 写就绪

        // 监听多个就绪状态可以用「位或」操作起来，如下
        int interesSet = SelectionKey.OP_CONNECT | SelectionKey.OP_ACCEPT | SelectionKey.OP_READ | SelectionKey.OP_WRITE;


        // 获取通道在选择器选择的感兴趣的事件集合
        int interestSet = selectionKey.interestOps();
        // 通过简单的「与」操作，判断是否对相关事件感兴趣
        boolean isInterestedInAccept  = (interestSet & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT;
        boolean isInterestedInConnect = (interestSet & SelectionKey.OP_CONNECT) == SelectionKey.OP_CONNECT;
        boolean isInterestedInRead    = (interestSet & SelectionKey.OP_READ) == SelectionKey.OP_READ;
        boolean isInterestedInWrite   = (interestSet & SelectionKey.OP_WRITE) ==  SelectionKey.OP_WRITE;

        // 获取通道已准备就绪的操作的集合
        int readyOps = selectionKey.readyOps();

        // 可以像上面「与」操作来判断某个事件是否就绪。但我们可以使用以下方法直接获取相关事件是否就绪
        boolean acceptable = selectionKey.isAcceptable();
        boolean connectable = selectionKey.isConnectable();
        boolean readable = selectionKey.isReadable();
        boolean writable = selectionKey.isWritable();

        // 获取Channel和Selector
        SelectableChannel selectableChannel = selectionKey.channel();
        Selector selector1 = selectionKey.selector();

        // 附加对象设置方式1：selectionKey.attach(theObject);
        // 附加对象设置方式2：selectionKey key = channel.register(selector, SelectionKey.OP_READ, theObject);
        // 获取附加对象方式：Object attachedObj = selectionKey.attachment();


        // 获取准备就绪（自上次调用select()方法后有多少个通道变成就绪状态）的通道数。如果当前还未有就绪的通道，则会阻塞到至少一个通道在你注册的事件上就绪。
        int selectCount = selector.select();

        // int select(long timeout); 可以设置timeout毫秒阻塞事件

        // selectNow() 不会阻塞，不管什么通道就绪都立刻返回

        // 获取已就绪「键」集
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
        while (keyIterator.hasNext()) {
            SelectionKey next = keyIterator.next();
            // SelectionKey相关操作
            next.interestOps();
            next.readyOps();
            next.isAcceptable();
            next.cancel();
            // Selector不会自己从已选择键集中移除SelectionKey实例。必须在处理完通道时自己移除。下次该通道变成就绪时，Selector会再次将其放入已选择键集中
            keyIterator.remove();
        }
        // 当某个线程调用select方法阻塞时，其他线程可通过调用wakeUp()方法唤醒
//        selector.wakeup();
        // 关闭Selector,注册到该Selector上的所有SelectionKey实例无效。通道本身并不会关闭。
        selector.close();
    }
}
