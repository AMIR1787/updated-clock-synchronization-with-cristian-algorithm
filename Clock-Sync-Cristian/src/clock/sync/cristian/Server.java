/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clock.sync.cristian;

import java.net.*;
import java.io.*;

/**
 *
 * @author user
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(4444);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(1);
        }

        Socket clientSocket = null;

        try {
            System.out.println("Waiting for connection...");
            clientSocket = serverSocket.accept();
            System.out.println("Connection successful.");
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Received message: " + inputLine);

            if (inputLine.equals("GET_TIME")) {
                // Get the current time on the server
                long currentTime = System.currentTimeMillis();

                // Send the current time to the client
                out.println(currentTime);
            }
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}


