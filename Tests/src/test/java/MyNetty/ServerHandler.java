package MyNetty;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.ArrayList;

/**
 * @author wbfancy
 * @version 1.0
 * @description: TODO
 * @date 2023/3/31 14:54
 */
public class ServerHandler {
    //用于存放用户Channel信息，也可以建立map结构模拟不同的消息群
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
