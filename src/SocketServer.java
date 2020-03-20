import java.io.*;
import java.net.*;


class SocketServer {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1250;
        Socket connection = null;

        try {
            ServerSocket serverSocket = new ServerSocket(PORTNR);
            System.out.println("Waiting...");
            while (true) {
                connection = serverSocket.accept(); //Waiting for a connection
                Thread clientThread = new ThreadHandlerClient(connection);
                clientThread.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
                connection.close();
        }
    }
}
