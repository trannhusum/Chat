package Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int PORT = 12345;

        try {
            Socket socket = new Socket(SERVER_ADDRESS, PORT);

            BufferedReader serverIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter serverOut = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            String userMessage, serverMessage;

            while (true) {
                System.out.print("Client: ");
                userMessage = userInput.readLine();
                serverOut.println(userMessage);

                if (userMessage.equalsIgnoreCase("bye")) {
                    break;
                }

                serverMessage = serverIn.readLine();
                System.out.println("Server: " + serverMessage);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
