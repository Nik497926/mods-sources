package ru.obvilion.mserver;

import ru.obvilion.mserver.net.TCPServer;
import ru.obvilion.mserver.net.UDPServer;
import ru.obvilion.mserver.utils.Log;

import java.io.IOException;

public class LauncherWrapper {
    public static void main(String[] args) throws IOException {
        Log.info("Starting server on port 6567");

        UDPServer udpServer = new UDPServer();
        udpServer.host();

        TCPServer tcpServer = new TCPServer();
        tcpServer.host();
    }
}
