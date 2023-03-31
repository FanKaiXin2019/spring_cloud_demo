package MyNetty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.util.Date;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/31 14:12
 */
@ChannelHandler.Sharable
public class SHandler  extends ChannelInboundHandlerAdapter {


    /**
     * 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //当有客户端链接后，添加到channelGroup通信组
        ServerHandler.channelGroup.add((SocketChannel)ctx.channel());
        //日志信息
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告开始");
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        //通知客户端链接建立成功
        String str = "通知客户端链接建立成功" + " " + new Date() + " " + channel.localAddress().getHostString() + "\r\n";
        ctx.writeAndFlush(str);
    }

    /**
     * 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端的关闭了通信通道并且不可以传输数据
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开链接" + ctx.channel().localAddress().toString());
        // 当有客户端退出后，从channelGroup中移除。
        ServerHandler.channelGroup.remove(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String msg_str = new String(bytes,"UTF-8");
        System.out.println("===========>接收客户端消息:"+msg_str);
        System.out.println("===========>向客户端发送消息："+msg_str);
        ServerHandler.channelGroup.writeAndFlush(Unpooled.copiedBuffer(msg_str.getBytes()));
        //ctx.writeAndFlush(Unpooled.copiedBuffer(msg_str.getBytes()));
    }

}
