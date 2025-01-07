import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static final String PATH="./a.txt";
    static public void  main(String[] args) throws IOException {
        Server server=new Server();
        try{
            server.run();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void run() throws IOException {
        int port=3001;
        ServerSocket socket=new ServerSocket(port);
        socket.setSoTimeout(10*1000);

        while (true){
            try{
                System.out.println("Server is listening on port:"+port);
                Socket acceptedConnection=socket.accept();

                System.out.println("Connection accepted from the client "+acceptedConnection.getRemoteSocketAddress());
                PrintWriter toClient=new PrintWriter(acceptedConnection.getOutputStream());
                BufferedReader fromClinet=new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));

                File file=new File(PATH);
                Scanner sc=new Scanner(file);
                StringBuilder content=new StringBuilder();
                while(sc.hasNextLine()){
                    content.append(sc.nextLine());
                }
                String str=content.toString();
                toClient.println(str);
                System.out.println("Response from client is: "+fromClinet.readLine());
                toClient.close();
                fromClinet.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
