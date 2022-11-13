import java.net.*;
import java.io.*;

public class clientLogic {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public clientLogic(){}

    public void startConnection(String ip, int port) {
        try{
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void sendMessage(String msg) {
        try{
            out.println(msg);
            String resp = in.readLine();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void stopConnection() {

        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
