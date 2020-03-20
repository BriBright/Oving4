import java.io.*;
import java.net.*;

public class ThreadHandlerClient extends Thread{
    private Socket socket;

    public ThreadHandlerClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            /*Opening up for communication with the server*/
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            /*Server connection setup*/
            System.out.println("connection successful 2");
            System.out.println("Write in numbers you want to add or subtract, then end with your chosen operator");

            /*Reading in from the cmd window*, and sending in tekst to the server*/
            String first = reader.readLine();
            String second = reader.readLine();
            String operator = reader.readLine();

            while (!first.equals("") || !second.equals("") || !operator.equals("")) {
                Calculate calculate = new Calculate();
                int answer;
                    if (operator.equals("+")) {
                        answer = calculate.add(Integer.parseInt(first), Integer.parseInt(second));
                        writer.println(answer);
                        System.out.println(first + " " + operator + " " + second + " = " + answer);

                    } else if (operator.equals("-")) {
                        answer = calculate.subtract(Integer.parseInt(first), Integer.parseInt(second));
                        writer.println(answer);
                        System.out.println(first + " " + operator + " " + second + " = " + answer);
                    } else {
                        System.out.println("Invalid operator");
                        writer.println("Invalid operator");
                    }

                    first = reader.readLine();
                    second = reader.readLine();
                    operator = reader.readLine();
                }
            /*Closing the socket connection*/
            writer.close();

        }catch (Exception io){
            io.printStackTrace();
        }
    }
}
