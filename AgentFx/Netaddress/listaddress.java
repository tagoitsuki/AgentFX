package AgentFx.Netaddress;
import java.io.*;
import java.net.*;
import java.net.UnknownHostException;
import java.util.*;

public class listaddress {
	public int SERVER_PORT = 49200;	
	public void listaddress(ArrayList<String> address, ArrayList<String> port) throws SocketException {
		for(NetworkInterface n: Collections.list(NetworkInterface.getNetworkInterfaces()) ) {
			for (InetAddress addr : Collections.list(n.getInetAddresses()))  {
				if( addr instanceof Inet4Address && !addr.isLoopbackAddress() ){
					address.add(String.valueOf(addr.getHostAddress()));
					this.listport(addr.getHostAddress());
					port.add(String.valueOf(SERVER_PORT));
				}
			}
		}
	}
	public void listport(String host){
		//System.out.println(host);
		try{
			for(SERVER_PORT+=1;SERVER_PORT<50000;SERVER_PORT++){
				//System.out.println(SERVER_PORT);
				Socket sc = new Socket(host, SERVER_PORT);
				//System.out.println(sc.getIndex());
				sc.close();
			}
		}
		catch(UnknownHostException ukhe){
			System.err.println(ukhe);
		}
		catch(IOException ioe){
			;
		}
	}
}