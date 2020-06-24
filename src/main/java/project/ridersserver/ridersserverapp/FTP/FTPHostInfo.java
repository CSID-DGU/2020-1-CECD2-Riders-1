package project.ridersserver.ridersserverapp.FTP;

import org.springframework.boot.context.properties.ConfigurationProperties;

//application.properties로부터 정보를 받도록함
@ConfigurationProperties("ftphostinfo")
public class FTPHostInfo {
	private String hostIP;
	private int port;
	private String ID;
	private String PW;

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String PW) {
		this.PW = PW;
	}
}
