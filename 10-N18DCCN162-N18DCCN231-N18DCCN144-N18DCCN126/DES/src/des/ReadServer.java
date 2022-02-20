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
                System.out.println("Đang đọc key từ client...");
                String key = din.readUTF();
                System.out.println("Key: " + key);
                int len = din.readInt();
                byte[] data = new byte[len];
                System.out.println("Đang đọc bản mã từ client...");
                if (len > 0) {
                    din.readFully(data);
                }
                System.out.println("Đang giải mã...");
                String ketQua = DES.giaiMaDES(key, data);                
                System.out.println("Bản rõ: " + ketQua);
                
                System.out.println("Gửi kết quả đến client...");
                dout.writeUTF(ketQua);
                System.out.println("Đã gửi kết quả");                
            }
        } catch (Exception e) {
            System.out.println(socket + " đã ngắt kết nối!");
            try {
                din.close();
                socket.close();
                //e.printStackTrace();
            } catch (IOException ex) {
                System.out.println(socket + " ngat ket noi server");
            }

        }
    }
 
}