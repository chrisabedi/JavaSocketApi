import java.net.*;
import java.io.*;


public class Server implements Runnable{

    public class SocketWorker implements Runnable {

        Socket socket;
        public SocketWorker(Socket socket)
        {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {

                DataInputStream in = new DataInputStream(socket.getInputStream());
                System.out.println("waiting for client on Port " + serverSocket.getLocalPort() + "... ");

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                out.writeUTF("Thanks for connecting to server.java at " + socket.getLocalSocketAddress() + "\n !!!!");

                //socket.close();
            }
            catch (IOException ex){
                ex.printStackTrace();
            }
        }
    }

    private ServerSocket serverSocket;

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        //serverSocket.setSoTimeout(100000);
    }

    @Override
    public void run() {
        while(true) {
            try {
                Socket s = serverSocket.accept();
                SocketWorker newSock = new SocketWorker(s);
                Thread t = new Thread(newSock);
                t.start();
            }
            catch(IOException ex){
                ex.printStackTrace();
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
