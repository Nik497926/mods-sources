package ru.obvilion.mserver.net;

import ru.obvilion.mserver.net.packets.out.ConfirmConnect;
import ru.obvilion.mserver.utils.Log;

import java.io.*;
import java.net.Socket;

public class ClientConnection extends Thread {
    private Socket socket;

    private InputStream in;
    private OutputStream out;

    public ClientConnection(Socket socket) throws IOException {
        this.socket = socket;

        in = socket.getInputStream();
        out = socket.getOutputStream();

        out.write(ConfirmConnect.getBytes());
        out.flush();

        start();
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                int result = in.read();

                if (result == -1) {
                    break;
                }

                Log.debug("Client sent " + result + " byte");
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
