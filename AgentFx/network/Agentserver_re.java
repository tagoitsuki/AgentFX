package AgentFx.network;
import java.io.*;
import java.net.*;
import java.sql.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Agentserver_re implements Runnable {
	
	private Label[] lb1 = new Label[3];
	GridPane gp = new GridPane();
	public String Hosts;
	public int Ports;
	
	public void Agentserver(String host,int port){
		
        Thread thread = new Thread();
		
		Hosts = host;
		Ports = port;
		if(port == 0){}
		else{
			lb1[0] = new Label("S;");
			lb1[1] = new Label(host + ":");
			lb1[2] = new Label(String.valueOf(Ports));
			for(int i = 0; i < 3; i++){
				gp.add(lb1[i], i, 0);
			}
			//Scene sc = new Scene(gp);
			Stage st = new Stage();
			//st.setScene(sc);
			try {
				thread.join();
				st.showAndWait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		}
	}
	public void run(){
		Socket socket = null;
        FileOutputStream fos = null;
        FileInputStream fis = null;
        try {
            System.out.println("start.");
            // 入出力ストリームを準備する
            fos = new FileOutputStream("server_recv.txt");
            fis = new FileInputStream("server_send.txt");
            // 入力ソケットを作成して、送信を待ち受ける
            ServerSocket server = new ServerSocket(Ports);
            socket = server.accept();
            // ソケットを受信する
            int ch;
            InputStream input = socket.getInputStream();
            while ((ch = input.read()) != 0) {
                fos.write(ch);
            }
            // 受信した内容をファイル出力
            OutputStream output = socket.getOutputStream();
            while ((ch = fis.read()) != -1) {
                output.write(ch);
            }
        } catch (SocketException e) {
            System.out.println("occured SocketException.");
        } catch (IOException e) {
            System.out.println("occured IOException.");
        } catch (Exception e) {
            System.out.println("occured Exception.");
        } finally {
            try {
                socket.close();
                fos.close();
                fis.close();
            } catch (IOException e) {
                System.out.println("occured IOException.");
            }
            System.out.println("end.");
        }
	}
}