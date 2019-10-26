package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            while (true){
                Socket socket = serverSocket.accept();
                Thread thread = new Thread(new SampleThread(socket));
                thread.start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
