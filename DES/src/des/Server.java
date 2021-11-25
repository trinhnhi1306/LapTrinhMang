/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package des;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Quan Dau
 */
public class Server {
  
    private int port;
    // danh sách client
    public static ArrayList<Socket> listSK;

    public Server(int port) {
        this.port = port;
    }
    
    private void execute() throws IOException {
        // cho server chạy song song với tiến trình ghi dữ liệu
        ServerSocket server = new ServerSocket(port);
        System.out.println("Server started");
        while (true) {

            // đợi một thằng kết nối tới và add vào list
            Socket socket = server.accept();            
            System.out.println("Server đã kết nối với " + socket);
            Server.listSK.add(socket);
            // đợi đọc
            ReadServer read = new ReadServer(socket);
            read.start();
        }

    }
    
    public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        
        // khai báo mảng
        Server.listSK = new ArrayList<>();
        // khai báo server
        Server server = new Server(15797);
        server.execute();
        
    }
}
