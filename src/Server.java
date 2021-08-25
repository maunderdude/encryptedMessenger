import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {

        // Initialize objects
        Socket socket = null;
        ServerSocket serverSocket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        // Socket port number
        serverSocket = new ServerSocket(6774);

        // While loop listening for connection
        while (true) {


            try {
                // Socket connection
                socket = serverSocket.accept();
                System.out.println("Client connected -");

                // Input and output streamers
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                Scanner scan = new Scanner(System.in);

                while (true) {
                    // Receive message
                    String msgFromClient = bufferedReader.readLine();
                    System.out.println("User: " + msgFromClient);

                    // Send message
                    String msgToSend = scan.nextLine();
                    bufferedWriter.write(msgToSend);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();


                    if (msgToSend.equalsIgnoreCase("333")) {
                        break;
                    }
                }

            } catch (IOException e) {
                System.out.println("Client error " + e.getMessage());
            }
            // Close resources
            socket.close();
            inputStreamReader.close();
            outputStreamWriter.close();
            bufferedReader.close();
            bufferedWriter.close();

        }
    }

}
