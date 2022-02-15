package com.alipay.webservice;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class BEISServerBootstrap {

    public static void main(String[] args) throws InterruptedException {
        startSocketService(7766);
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
                    if (input.readableBytes() < 128) {
                        break;
                    }

                    byte[] headerBytes = new byte[128];
                    input.readBytes(headerBytes);
                    String header = new String(headerBytes);
                    // 解析socket头部长度
                    // 8字节长度，不足前缀是0
                    String length = header.substring(18,26);
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

                    System.out.println(new String(payload));

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

    @ChannelHandler.Sharable
    static class NettyServerHandler extends ChannelDuplexHandler {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            // 把<Request> 转成<Response>
            // 把</Request> 转成</Response>
            String body = String.valueOf("{BOBXML:CBT001    000006401000000000000001                                        {BOBXML:                                     }DQ1LWVkWQV1DQVpbWwsVCR8CERRQWFRXVVtdUwgUYmx3HwsWCgg9BHVdUEFYU1lMEUpeWFtFChpWV11YGwcFCAEcAwQFBxkIARwDBRcIPQRiS0B8UFdTBjsOYVFBdEJLWHZSQFAIBQgDAAMFBAcLF2NXR3ZARV58UEZWCj8KZV1FYUpHcVdDXQ8AAwYHBgYJAA4cZlBCZEFCdlJAUAg9BGNXR2dMRWNRXFcNBQAGBgwHAQ8bZ1NDa0hBZ11YUwkyDWBWQGZTRnZeDHJ6fGMHCAACAwQFBgcJBwcFDQUKGGpURmBRRHhYBjsOYVFBCD0EY1dHd1pSUgZzBwMFCRllXUVxXFBQCD0EY1dHeUZRCQ8BAlBbWBhETV9LUkZRGFJAUldDQFxZWRZiR11xdntyQFJXQ0BcWVkCEdW5gtO2tt6Rtdubs9mLonQCAwcZ0Li32Y2D24ms0bGI1J+V0YmW3rCd14y/0ouY1JaC3IGTG920hdeJptOxvdScituJrN+XlNS6jdOalt2GgNeMv9KLmNScv9K9ptiEvdqcg9C9iNG2v9aQuNChiNSQrQgaZFJMfEFUCj8KGGpURg0+CRlkQUJ6VlVRCD0EcEJDfFBXUxcPOA8bcVlUTVxXXUALPA==");

            System.out.println("ready response: " + body);

            ctx.channel().writeAndFlush(body);
        }
    }
}
