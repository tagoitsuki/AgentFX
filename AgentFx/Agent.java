package AgentFx;
import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.*;
import javafx.event.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import static javafx.scene.paint.Color.*;
import javafx.stage.*;
import AgentFx.Netaddress.*;
import AgentFx.func.*;

public class Agent extends Application{
	
	//lb-A*: 補助表示用
	private Label lbA1 = new Label("IP:");
	private Label lbA2 = new Label("Port:");
	private Label lbA3 = new Label("Please input the box and press the enter.");
	private Label lbA4 = new Label("IP:");
	private Label lbA5 = new Label("Port:");
		
	//実行用
	private Button btB1 = new Button("Enter");
	private Button btB2 = new Button("Clear");
	
	//ipv4
	private Label[] lbB = new Label[5];
	
	//port
	private Label[] lbC = new Label[5];
	
	//ボタンを押したら、下部のテキストボックスに反映する
	private Button[] btD = new Button[5];
	
	//deny
	private Label denylb1 = new Label();
	private Label denylb2 = new Label();
	
	//textfeild
	private TextField tfD1 = new TextField();
	private TextField tfD2 = new TextField();
	
	//New Instance on import file.
	listaddress la = new listaddress();
	GridPane gp = new GridPane();
	
	public int begin = 0;
	public int size = 0;
	
	public static void Agent(String[] args){
		launch(args);
	}
	public void start(Stage stage) throws Exception{
		this.Initialize();
		ArrayList<String> add = new ArrayList<String>();
		ArrayList<String> por = new ArrayList<String>();
		la.listaddress(add,por);		
		size = add.size();
		String[] address = new String[size];
		String[] port = new String[size];
		address = Array2List.A2Ls(add, size);
		port = Array2List.A2Ls(por, size);
		this.address_view(address,port);
		this.add_prop();
		this.set_gp();
		this.setEventHD();
		Scene sc = new Scene(gp);
		stage.setScene(sc);
		stage.setTitle("AgentFX");
		stage.show();
	}
	public void Initialize(){
		for(int n = 0; n < 5; n++){
			btD[n] = new Button("No."+(n+1));
			lbB[n] = new Label("000.000.000.000");
			lbC[n] = new Label("00000");
		}
	}
	public void address_view(String[] address, String[] port){
		for(int i = 0; i < 5; i++){
			if( i < size ){
				lbB[i].setText(address[i]);
				lbC[i].setText(port[i]);
			}
		}
	}
	public void add_prop(){
		lbA1.setFont(new Font("Arial", 16));
		lbA2.setFont(new Font("Arial", 16));
		lbA3.setFont(new Font("Arial", 14));
		lbA4.setFont(new Font("Arial", 16));
		lbA5.setFont(new Font("Arial", 16));
		btB1.setFont(new Font("Arial", 16));
		btB2.setFont(new Font("Arial", 16));
		for(int n = 0; n < 5; n++){
			btD[n].setFont(new Font("Arial", 16));
			lbB[n].setFont(new Font("Arial", 16));
			lbC[n].setFont(new Font("Arial", 16));
		}
		tfD1.setFont(new Font("Arial", 16));
		tfD2.setFont(new Font("Arial", 16));
		denylb1.setLineSpacing(30.0);
		denylb2.setLineSpacing(30.0);
		tfD2.setPrefColumnCount(5);
		lbA3.setTextFill(RED);
	}
	public void set_gp(){
		gp.add(lbA1, 1, 0);
		gp.add(lbA2, 2, 0);
		for(int n = 0; n < 5; n++){
			gp.add(btD[n], 0, n+1);
			gp.add(lbB[n], 1, n+1);
			gp.add(lbC[n], 2, n+1);
		}		
		gp.add(denylb1, 0, 6,3,1);
		gp.add(lbA3, 0, 7, 3, 1);
		gp.add(denylb2, 0, 8,3,1);
		gp.add(lbA4, 0, 9, 3, 1);
		gp.add(lbA5, 2, 9);
		gp.add(tfD1, 0, 10, 2, 1);
		gp.add(tfD2, 2, 10);
		gp.add(btB1, 0, 11);
		gp.add(btB2, 2, 11);
	}
	public void setEventHD(){
		for (int m = 1; m < 5; m++){
			btD[m].setOnAction(new SampleEventHandler());
		}
	}
	class SampleEventHandler implements EventHandler<ActionEvent>{
		public void handle (ActionEvent e){
			if(e.getSource()==btD[0]&&0<size) this.InputBox(0);
			if(e.getSource()==btD[1]&&1<size) this.InputBox(1);
			if(e.getSource()==btD[2]&&2<size) this.InputBox(2);
			if(e.getSource()==btD[3]&&3<size) this.InputBox(3);
			if(e.getSource()==btD[4]&&4<size) this.InputBox(4);
		}
		public void InputBox(int n){
			tfD1.setText("1");
			tfD2.setText("1");
		}
	}
}