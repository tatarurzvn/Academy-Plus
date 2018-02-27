import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Chat_Server {
    
    public static void main(String[] args) throws IOException {
        List<ServerSide> lst = new ArrayList<ServerSide>();
        ServerSocket serverSocket = new ServerSocket(2999);
        while (true) {
            Socket s = serverSocket.accept();
            ServerSide ss = new ServerSide(s, lst);
            lst.add(ss);
            ss.start();
        }
    }
    
}
