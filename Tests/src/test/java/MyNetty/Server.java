package MyNetty;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/31 14:11
 */
public class Server {
    static EventLoopGroup boss = new NioEventLoopGroup(2);
    static EventLoopGroup work = new NioEventLoopGroup(2);
    static SHandler s = new SHandler();
    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap server = new ServerBootstrap();
        server.group(boss,work);
        server.channel(NioServerSocketChannel.class);
        server.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) {
                // 数据解码操作
                ch.pipeline().addLast(new HttpResponseEncoder());
                // 数据编码操作
                ch.pipeline().addLast(new HttpRequestDecoder());
                ch.pipeline().addLast(new ChunkedWriteHandler());
                ch.pipeline().addLast(s);
            }
        });
        ChannelFuture bind = server.bind("127.0.0.1", 9999).sync();
        if (bind.isSuccess()) {
            System.out.println("started....");
        }else{
            System.err.println("error....");
        }
        try {
            bind.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
