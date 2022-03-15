package com.alipay.webservice;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.concurrent.DefaultThreadFactory;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SocketServerBootstrap {

    public static void main(String[] args) throws InterruptedException {
        startSocketService(9999);
    }


    public static void startSocketService(int port) throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(1);

        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("NettyServerBoss", true));
        NioEventLoopGroup workerGroup = new NioEventLoopGroup(2, new DefaultThreadFactory("NettyServerWorker", true));

        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .childOption(ChannelOption.SO_REUSEADDR, Boolean.TRUE)
                .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        NettyCodecAdapter adapter = new NettyCodecAdapter();
                        ch.pipeline()
                                .addLast("decoder", adapter.getDecoder())
                                .addLast("encoder", adapter.getEncoder())
                                .addLast("handler", new NettyServerHandler());
                    }
                });
        // bind
        ChannelFuture channelFuture = bootstrap.bind("0.0.0.0", port);
        channelFuture.syncUninterruptibly();
        Channel channel = channelFuture.channel();

        latch.await();
    }

    static class NettyCodecAdapter {

        private final ChannelHandler encoder = new InternalEncoder();

        private final ChannelHandler decoder = new InternalDecoder();

        public ChannelHandler getEncoder() {
            return encoder;
        }

        public ChannelHandler getDecoder() {
            return decoder;
        }

        private class InternalEncoder extends MessageToByteEncoder {


            @Override
            protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
                // 构造socket协议响应报文
                String packet = String.valueOf(msg);
                String lenOfPacket = String.valueOf(packet.getBytes().length);
                if (lenOfPacket.length() < 8) {
                    int remain = 8 - lenOfPacket.length();
                    String prefix = "";
                    for (int i = 0; i < remain; i++) {
                        prefix += "0";
                    }
                    lenOfPacket = prefix + lenOfPacket;
                }
                out.writeBytes(lenOfPacket.getBytes());
                out.writeBytes(packet.getBytes());
            }
        }

        private class InternalDecoder extends ByteToMessageDecoder {

            @Override
            protected void decode(ChannelHandlerContext ctx, ByteBuf input, List<Object> out) throws Exception {
                int saveReaderIndex;
                // decode object.
                do {
                    saveReaderIndex = input.readerIndex();
                    // 8字节长度
                    if (input.readableBytes() < 8) {
                        break;
                    }

                    byte[] header = new byte[8];
                    input.readBytes(header);

                    // 解析socket头部长度
                    // 8字节长度，不足前缀是0
                    String length = new String(header);
                    while (length.startsWith("0")) {
                        if (length.length() > 1) {
                            length = length.substring(1);
                        }
                    }

                    int packetLength = Integer.parseInt(length);
                    int available = input.readableBytes();
                    if (packetLength > available) {
                        // rollback buffer reader index
                        input.readerIndex(saveReaderIndex);
                        break;
                    }

                    byte[] payload = new byte[packetLength];
                    input.readBytes(payload);

                    out.add(new String(payload));

                    if (input.readableBytes() > 0) {
                        byte b = input.readByte();
                        while (b == '\r' || b == '\n') {
                            if (input.isReadable()) {
                                b = input.readByte();
                            } else {
                                break;
                            }
                        }
                        if (input.isReadable()) {
                            input.readerIndex(input.readerIndex() - 1);
                        }
                    }

                } while (input.readableBytes() > 0);
            }
        }
    }

    @io.netty.channel.ChannelHandler.Sharable
    static class NettyServerHandler extends ChannelDuplexHandler {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            // 把<Request> 转成<Response>
            // 把</Request> 转成</Response>
            String body = String.valueOf(msg);

            body = body.replaceAll("<RequestType>0</RequestType>", "<RequestType>1</RequestType>");

            body = body.replaceAll("<Request>", "<Response>");
            body = body.replaceAll("</Request>", "</Response>");

            System.out.println("ready response: " + body);

            ctx.channel().writeAndFlush(body);
        }
    }
}
