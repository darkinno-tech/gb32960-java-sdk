package io.github.darkinno-tech-tech.gb32960.callback.api;

import java.net.InetSocketAddress;

public interface Session {

    String id();

    String vin();

    InetSocketAddress remoteAddress();

    boolean isConnected();

    void send(byte[] data);

    void close();
}
