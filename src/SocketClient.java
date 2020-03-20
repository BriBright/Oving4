import java.net.*;
import  java.io.*;
import java.util.Scanner;


public class SocketClient {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1250;

        Scanner readFromCmd = new Scanner(System.in);
        String machineServer = "Brigitts-MacBook-Air.local";

        /*Connecting to server*/
        Socket socketConnection = new Socket(machineServer, PORTNR);
        System.out.println("connection successful 1");

        /*Opening up for communication with the server*/
        BufferedReader reader = new BufferedReader(new InputStreamReader(socketConnection.getInputStream()));
        PrintWriter writer = new PrintWriter(socketConnection.getOutputStream(), true);

        /*Reading in message from the server*/
        String first = readFromCmd.nextLine();
        String second = readFromCmd.nextLine();
        String operator = readFromCmd.nextLine();

        /*Reading in from the cmd window*, and sending in tekst to the server*/
        while (!first.equals("") || !second.equals("")|| !operator.equals("")) {
            writer.println(first);
            writer.println(second);
            writer.println(operator);
            System.out.println("Answr :" + reader.readLine());

            first = readFromCmd.nextLine();
            second = readFromCmd.nextLine();
            operator = readFromCmd.nextLine();
        }

        /*Closing the socket connection*/
        reader.close();
        writer.close();
        socketConnection.close();

    }
}

