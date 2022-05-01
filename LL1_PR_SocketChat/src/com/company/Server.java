package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server
{
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket)
    {
        this.serverSocket = serverSocket;
    }

    public void startServer()
    {
        try {
            while(!serverSocket.isClosed())
            {
                Socket socket = serverSocket.accept();
                System.out.println("New client has connected!");
                ClientHandler clientHandler = new ClientHandler(socket);

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                sendMessage(socket, out);
                receiveMessage(socket, in);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            closeServerSocket();
        }
    }

    public void receiveMessage(Socket socket, BufferedReader bufferedReader)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromChat;

                while(socket.isConnected())
                {
                    try {
                        msgFromChat = bufferedReader.readLine();
                        System.out.println(msgFromChat);
                    } catch (IOException e) {
                        closeServerSocket();
                    }
                }
            }
        }).start();
    }

    public void sendMessage(Socket socket, BufferedWriter bufferedWriter)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Scanner scanner = new Scanner(System.in);
                    while(socket.isConnected())
                    {
                        String messageToSend = scanner.nextLine();
                        bufferedWriter.write("BigBrother: " + messageToSend);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                } catch (IOException e) {
                    closeServerSocket();
                }
            }
        }).start();
    }

    public void closeServerSocket()
    {
        try {
            if(serverSocket != null)
            {
                serverSocket.close();
            }
        } catch (IOException e) {

        }
    }

    public static void main (String [] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(6969);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
