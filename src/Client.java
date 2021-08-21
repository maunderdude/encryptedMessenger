import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        // Initialize objects
        Socket socket = null;
        InputStreamReader inputStreamReader = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;


        try {
            // Connection socket to server IP and port
            socket = new Socket("192.168.0.21", 6774);

            // Read and write
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scan = new Scanner(System.in);

            // While loop to listen for response
            while(true) {
                // Read and display client message
                System.out.println("Anonymous: " + bufferedReader.readLine());

                // Write and send next line
                String msgToSend = scan.nextLine();
                bufferedWriter.write(msgToSend);
                bufferedWriter.newLine();
                bufferedWriter.flush(); // flush writer

                // Break condition
                if(msgToSend.equalsIgnoreCase("333")){
                    break;
                }
            }

        } catch (IOException e) {
            System.out.println("Client error " + e.getMessage());

            // Close resources
        } finally{
            try{
                if(socket != null){
                    socket.close();
                }
                if(inputStreamReader != null){
                    inputStreamReader.close();
                }
                if(outputStreamWriter != null){
                    outputStreamWriter.close();
                }
                if(bufferedReader != null){
                    bufferedReader.close();
                }
                if(bufferedWriter != null){
                    bufferedWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
