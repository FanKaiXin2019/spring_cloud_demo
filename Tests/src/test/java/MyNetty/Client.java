package MyNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.util.Scanner;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/31 14:11
 */
public class Client {
    static CHandler c = new CHandler();

    static EventLoopGroup e = new NioEventLoopGroup(2);

    public static void main(String[] args) throws InterruptedException {
        Bootstrap client = new Bootstrap();
        client.group(e);
        client.channel(NioSocketChannel.class);
        client.option(ChannelOption.SO_KEEPALIVE, true);
        client.handler(new ChannelInitializer<SocketChannel>() {
             protected void initChannel(SocketChannel socketChannel) throws Exception {
                 socketChannel.pipeline().addLast(new ChannelHandler[]{c});
                 socketChannel.pipeline().addLast(new HttpResponseEncoder());
                 socketChannel.pipeline().addLast(new HttpRequestDecoder());
             }
         });
        ChannelFuture connect = client.connect("127.0.0.1", 9999).sync();
        Scanner scan = new Scanner(System.in);
        for (;;){
            String next = scan.next();
            connect.channel().writeAndFlush(Unpooled.copiedBuffer(next.getBytes()));
        }
    }
}
