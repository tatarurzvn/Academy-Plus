import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerSide extends Thread {

    Socket s;
    List<ServerSide> sSideList;
    public DataOutputStream out = null;
    
    public ServerSide(Socket s, List<ServerSide> lst) {
        this.s = s;
        this.sSideList = lst;
    }

    public void run() {

        try {
            final DataInputStream in = new DataInputStream(new BufferedInputStream(s.getInputStream()));
            out = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));
            //String incoming;
            new Thread(() -> {
                while (true) {
                    String t = null;
                    try {
                        t = in.readUTF();
                        for (ServerSide s : sSideList) {
                            s.myWrite(t);
                        }
                        System.out.println(t);
                    } catch (Exception e) {
                    }
                }
            }).start();
            
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerSide.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
                s.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void myWrite(String t) throws IOException {
        out.writeUTF(t);
        out.flush();
    }
}
