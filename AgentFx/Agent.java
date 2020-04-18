package AgentFx;
import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.*;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import static javafx.scene.paint.Color.*;
import javafx.stage.*;
import AgentFx.Netaddress.*;
import AgentFx.func.*;
import AgentFx.network.*;

public class Agent extends Application{
	
	//lbA: 補助表示用
	private Label[] lbA = new Label[4];
	private String[] lbAs = {"IP:","Port:","IP:","Port:"};
		
	//実行用
	private Button[] btF = new Button[3];
	private String[] btFs = {"Server","Client","Clear"};
	
	//ipv4,port
	private Label[] lbB = new Label[5];
	private Label[] lbC = new Label[5];
	
	//ボタンを押したら、下部のテキストボックスに反映する
	private Button[] btD = new Button[5];
	
	//textfeild
	private TextField[] tfE = new TextField[2];
	//deny
	private Label denylb = new Label();
	
	//New Instance on import file.
	listaddress la = new listaddress();
	GridPane gp = new GridPane();
	
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
			if(n < 2)	tfE[n] = new TextField();
			if(n < 3)	btF[n] = new Button(btFs[n]);
			if(n < 4)	lbA[n] = new Label(lbAs[n]);
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
		for(int n = 0; n < 5; n++){
			if(n < 2)	tfE[n].setFont(new Font("Arial", 16));
			if(n < 3)	btF[n].setFont(new Font("Arial", 16));
			if(n < 4)	lbA[n].setFont(new Font("Arial", 16));
			btD[n].setFont(new Font("Arial", 16));
			lbB[n].setFont(new Font("Arial", 16));
			lbC[n].setFont(new Font("Arial", 16));
		}
		denylb.setLineSpacing(30.0);
		tfE[1].setPrefColumnCount(5);
	}
	public void set_gp(){
		gp.add(lbA[0], 1, 0);
		gp.add(lbA[1], 2, 0);
		for(int n = 0; n < 5; n++){
			gp.add(btD[n], 0, n+1);
			gp.add(lbB[n], 1, n+1);
			gp.add(lbC[n], 2, n+1);
		}
		gp.add(denylb, 0, 7,3,1);
		gp.add(lbA[2], 0, 8, 3, 1);
		gp.add(lbA[3], 2, 8);
		gp.add(tfE[0], 0, 9, 2, 1);
		gp.add(tfE[1], 2, 9);
		gp.add(btF[0], 0, 10);
		gp.add(btF[1], 1, 10);
		gp.add(btF[2], 2, 10);
	}
	public void setEventHD(){
		for (int m = 0; m < 5; m++){
			btD[m].setOnAction(new SampleEventHandler());
			if(m < 3) btF[m].setOnAction(new SampleEventHandler());
		}
	}
	public void Console(int i){
		if ( i == 0 ){
			Agentserver AS = new Agentserver();
			AS.Agentserver(tfE[0].getText(),Integer.valueOf(tfE[1].getText()));
		}
		else if ( i == 1 ){
			Agentclient AC = new Agentclient();
			AC.Agentclient(tfE[0].getText(),Integer.valueOf(tfE[1].getText()));
		}
	}
	class SampleEventHandler implements EventHandler<ActionEvent>{
		public void handle (ActionEvent e){
			if(e.getSource()==btD[0]&&0<size) this.InputBox(0);
			if(e.getSource()==btD[1]&&1<size) this.InputBox(1);
			if(e.getSource()==btD[2]&&2<size) this.InputBox(2);
			if(e.getSource()==btD[3]&&3<size) this.InputBox(3);
			if(e.getSource()==btD[4]&&4<size) this.InputBox(4);
			if(e.getSource()==btF[0]) Console(0);
			if(e.getSource()==btF[1]) Console(1);
			if(e.getSource()==btF[2]) this.Clear();
		}
		public void InputBox(int n){
			tfE[0].setText(lbB[n].getText());
			tfE[1].setText(lbC[n].getText());
		}
		public void Clear(){
			tfE[0].setText("");
			tfE[1].setText("");
		}
	}
}