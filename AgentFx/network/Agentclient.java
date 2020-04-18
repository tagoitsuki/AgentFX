package AgentFx.network;
import java.io.*;
import java.net.*;
import java.sql.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Agentclient implements Runnable {
	
	protected ThreadGroup threadGroup = null;
	private Label[] lb1 = new Label[3];
	GridPane gp = new GridPane();
	public String Hosts;
	public int Ports;
	public void Agentclient(String host,int port){
		Hosts = host;
		Ports = port;
		if(port == 0){}
		else{
			lb1[0] = new Label("C;");
			lb1[1] = new Label(host + ":");
			lb1[2] = new Label(String.valueOf(Ports));
			for(int i = 0; i < 3; i++){
				gp.add(lb1[i], i, 0);
			}
			Scene sc = new Scene(gp);
			Stage st = new Stage();
			st.setScene(sc);
			st.showAndWait();
			this.socketclient();
		}
	}
	public void socketclient(){
		Socket socket = null;
		FileOutputStream fos = null;
		FileInputStream fis = null;
		try{
			 System.out.println("start.");
            // 出力ソケットを作成
            socket = new Socket(Hosts, Ports);
            // 入出力ストリームを準備
            fis = new FileInputStream("client_send.txt");
            fos = new FileOutputStream("client_recv.txt");
            // 入力ストリームの内容を全て送信
            int ch;
            OutputStream output = socket.getOutputStream();
            while ((ch = fis.read()) != -1) {
                output.write(ch);
            }
            output.write(0);
            // サーバからのレスポンスを受信し、ファイルに出力
            InputStream input = socket.getInputStream();
            while ((ch = input.read()) != -1) {
                fos.write(ch);
            }
        } catch (SocketException e) {
            System.out.println("occured SocketException.");
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