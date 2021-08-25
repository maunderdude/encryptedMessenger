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

        char [] encrypt; // array for encryption
        int key = 5; // encryption key

        try {
            socket = new Socket("192.168.0.21", 6774);

            // Read and write
            inputStreamReader = new InputStreamReader(socket.getInputStream());
            outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

            bufferedReader = new BufferedReader(inputStreamReader);
            bufferedWriter = new BufferedWriter(outputStreamWriter);

            Scanner scan = new Scanner(System.in);

            // While loop to listen for response
            while(true) {

                String msgToSend = scan.nextLine();
                // Assigning user input to char array
                encrypt = msgToSend.toCharArray();
                // encrypting the char array
                for(char c : encrypt){
                    c += 5;
                    bufferedWriter.write(c);
                }
                bufferedWriter.newLine();
                bufferedWriter.flush();

                // Reading server message
                System.out.println("Anonymous: " + bufferedReader.readLine());


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
