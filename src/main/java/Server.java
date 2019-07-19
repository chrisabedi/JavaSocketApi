import java.net.*;
import java.io.*;


public class Server implements Runnable{

    private ServerSocket serverSocket;
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }

    @Override
    public void run() {
        while(true) {
            try {
                System.out.println("waiting for client on Port " + serverSocket.getLocalPort() + "... ");
                Socket s = serverSocket.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());

                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                out.writeUTF("Thanks for connecting to server.java at " + s.getLocalSocketAddress() + "\n !!!!");

                s.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
        public static void main(String [] args){
            int port = Integer.parseInt(args[0]);
            try{
                Server serverThread = new Server(port);
                serverThread.run();

        } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
