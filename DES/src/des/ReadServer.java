/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package des;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author TRINH
 */
public class ReadServer extends Thread {
    
    // đây là 1 trong những client được gửi đến,
    // nó hoàn toàn có thể phân biệt từng client
    private Socket socket;
    private DataInputStream din;
    private DataOutputStream dout;
    
    public ReadServer(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // khai báo dis để đọc dữ liệu trả về
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
        
            while (true) {       
                
                String key = din.readUTF();
                System.out.println("Key: " + key);
                int len = din.readInt();
                byte[] data = new byte[len];
                
                if (len > 0) {
                    din.readFully(data);
                }
                
                String ketQua = DES.giaiMaDES(key, data);                
                System.out.println("Bản rõ: " + ketQua);
                
                
                dout.writeUTF(ketQua);
                System.out.println("Đã gửi kết quả");                
            }
        } catch (Exception e) {
            System.out.println(socket + " đã ngắt kết nối!");
            try {
                din.close();
                dout.close();
                socket.close();
                //e.printStackTrace();
            } catch (IOException ex) {
                System.out.println(socket + " ngat ket noi server");
            }

        }
    }
 
}