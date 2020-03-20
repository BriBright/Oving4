import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebSocket {
    public static void main (String[]args){
        final int PORTNUM = 8080;

        try{
            ServerSocket serverSocket= new ServerSocket(PORTNUM);
            System.out.println("Server starting");
            Socket socket = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String list = "";

            String header = reader.readLine();
            while(!header.equals("")) {
                list += "<ul>" + header + "</ul>";
                header = reader.readLine();
            }

            System.out.println(list);

            String http = "HTTP/1.0 200 OK\nContent-Type: text/html; charset=utf-8\n\n\n" +
                    "<HTML><BODY>" +
                    "<H1> Welcome! </h1>\n" +
                    "<ul>" +
                    list
                    +
                    "</ul>" +
                    "</BODY></HTML>";
            socket.getOutputStream().write(http.getBytes("UTF-8"));
            reader.close();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
