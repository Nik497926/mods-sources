package ru.obvilion.mserver.net;

import ru.obvilion.mserver.utils.Bytes;
import ru.obvilion.mserver.utils.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class UDPServer {
    private Thread server_thread;
    private DatagramSocket socket;

    public void host() {
        try {
            socket = new DatagramSocket(6567);
            Log.info("UDP server started on port 6567");
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        server_thread = new Thread(() -> {
            byte[] receivingDataBuffer = new byte[512];

            while (!socket.isClosed()) {
                DatagramPacket inputPacket = new DatagramPacket(receivingDataBuffer, receivingDataBuffer.length);

                try {
                    socket.receive(inputPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.debugw("Client sent " + inputPacket.getLength() + " bytes");
                Log.debug(Bytes.toString(inputPacket.getData(), inputPacket.getLength()));



                DatagramPacket outputPacket = new DatagramPacket(
                        buffer.array(), buffer.capacity(),
                        inputPacket.getAddress(), inputPacket.getPort()
                );

                try {
                    socket.send(outputPacket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "UDP Server Thread");

        server_thread.start();
    }
}
