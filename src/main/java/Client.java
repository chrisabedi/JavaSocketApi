import java.net.*;
import java.io.*;


public class Client {

    public static void main (String [] args){
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        try {
            System.out.println(String.format("Connecting to Server %s} on Port %d",serverName,port));
           Socket client = new Socket(serverName, port);

           OutputStream outToServer = client.getOutputStream();
           DataOutput out = new DataOutputStream(outToServer);

           out.writeUTF("Hello from " + client.getLocalSocketAddress());
           InputStream inFromServer = client.getInputStream();
           DataInput in = new DataInputStream(inFromServer);

           System.out.println("Server responds with " + in.readUTF());
           client.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}
