import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run() throws IOException {
        int port = 3001;
        InetAddress address = InetAddress.getByName("localhost");
        Socket socket = new Socket(address, port);

        try (PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send a message to the server
            System.out.println("output from server is:"+fromSocket);
            toSocket.println("Hello From Client...");

            // Receive response from the server
            System.out.println("Response from socket is: " + fromSocket.readLine());
        } finally {
            socket.close();
        }
    }
}
