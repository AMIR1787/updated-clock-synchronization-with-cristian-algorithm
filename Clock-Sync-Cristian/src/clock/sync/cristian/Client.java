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


public class Client {
    public static void main(String[] args) throws IOException {
        String hostName = "localhost";
        int portNumber = 4444;

        try (
            Socket clientSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;

            while ((userInput = stdIn.readLine()) != null) {
                // Send the GET_TIME message to the server
                out.println("GET_TIME");

                // Get the current time from the server
                long serverTime = Long.parseLong(in.readLine());

                // Calculate the round-trip time
                long RTT = System.currentTimeMillis() - serverTime;

                // Calculate the time offset
                long timeOffset = RTT / 2;

                // Set the client clock to the server time plus the time offset
                long clientTime = serverTime + timeOffset;

                // Print the synchronized time
                System.out.println("Server time: " + serverTime + ", RTT: " + RTT + ", time offset: " + timeOffset + ", synchronized time: " + clientTime);
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}
