package ru.obvilion.mserver.net;

import ru.obvilion.mserver.utils.Log;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class TCPServer {
    private Thread server_thread;
    private ServerSocket socket;
    private LinkedList<ClientConnection> connections = new LinkedList<>();

    public void host() {
        try {
            socket = new ServerSocket(6567);
            Log.info("TCP server started on port 6567");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        server_thread = new Thread(() -> {
            while (!socket.isClosed()) {
                try {
                    Socket clientConn = socket.accept();

                    Log.debug("Client connected to TCP server: " + clientConn.toString());

                    ClientConnection con = new ClientConnection(clientConn);
                    con.setName("TCP Connection #" + connections.size());

                    connections.add(con);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "TCP Server Thread");

        server_thread.start();
    }
}
