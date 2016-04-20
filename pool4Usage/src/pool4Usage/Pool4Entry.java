package pool4Usage;

public class Pool4Entry {

	public String mark;
	public String protocol;
	public String address;
	public int min_port;
	public int max_port;
	
	public Pool4Entry(String mark, String protocol, String address,
			int min_port, int max_port){
		this.mark = mark;
		this.protocol = protocol;
		this.address = address;
		this.min_port = min_port;
		this.max_port = max_port;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMin_port() {
		return min_port;
	}

	public void setMin_port(int min_port) {
		this.min_port = min_port;
	}

	public int getMax_port() {
		return max_port;
	}

	public void setMax_port(int max_port) {
		this.max_port = max_port;
	}
	
	
}
