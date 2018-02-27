import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Chat_Client {

    public static void main(String[] args) {
        // TODO code application logic here
        DataOutputStream out = null;
        Socket socket = null;
        Scanner scan = new Scanner(System.in);
        try {
            socket = new Socket("127.0.0.1", 2999);
            final DataInputStream in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream((new BufferedOutputStream(socket.getOutputStream())));
            //String t;
            new Thread(() -> {
                while (true) {
                    String t = null;
                    try {
                        t = in.readUTF();
                    } catch (IOException ex) {
                        Logger.getLogger(Chat_Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println(t);
                }
            }).start();
            while (true) {
                String s = scan.nextLine();
                out.writeUTF(s);
                out.flush();
            }
        } catch (IOException ex) {
        }
    }

}
