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
                // Accept and acknowledge connection
                socket = serverSocket.accept();
                System.out.println("Client connected -");

                // Read & write
                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                bufferedWriter = new BufferedWriter(outputStreamWriter);

                Scanner scan = new Scanner(System.in);

                // While loop to listen for response
                while (true) {

                    // Read and display client message
                    System.out.println("User: " + bufferedReader.readLine());

                    // Write and send next line
                    String msgToSend = scan.nextLine();
                    bufferedWriter.write(msgToSend);
                    bufferedWriter.newLine();
                    bufferedWriter.flush(); // flush writer

                    // Break condition
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
