import java.net.*;
import java.io.*;


public class Client {

    public static class ClientWorker implements Runnable {

        String serverName;
        int port;
        Socket socket;

        ClientWorker(String serverName, int port) {
            this.serverName = serverName;
            this.port = port;
            System.out.println(String.format("Connecting to Server %s on Port %d", serverName, port));
            try {
                this.socket = new Socket(serverName, port);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        @Override
        public void run() {
            try {
                OutputStream outToServer = socket.getOutputStream();
                DataOutput out = new DataOutputStream(outToServer);

                out.writeUTF("Hello from " + socket.getLocalSocketAddress());
                InputStream inFromServer = socket.getInputStream();
                DataInput in = new DataInputStream(inFromServer);

                System.out.println("Server responds with " + in.readUTF());
                socket.close();

            } catch ( IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        String serverName = (args[0]);
        int port = Integer.parseInt(args[1]);
        for (int i = 0; i < 10; i++) {
            ClientWorker clientWorker = new ClientWorker(serverName, port);
            Thread t = new Thread(clientWorker);
            t.start();
        }

    }
}

