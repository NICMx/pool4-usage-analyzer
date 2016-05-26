package mx.nic.jool.pool4.analyzer;

/**
 * Pool4Entry Pool4Entry is an instance of a Pool4 Database containing all the
 * corresponding attributes of it.
 */
public class Pool4Entry {

	public String mark;
	public String protocol;
	public String address;
	public int min_port;
	public int max_port;

	/**
	 * Constructor method that can receive all the Pool4Entry attributes
	 * 
	 * @param mark
	 *            Mark of the Pool4 entry
	 * @param protocol
	 *            Protocol of the Pool4 entry
	 * @param address
	 *            Address of the Pool4 entry
	 * @param min_port
	 *            Minimum Port of the Pool4 entry
	 * @param max_port
	 *            Maximum Port of the Pool4 entry
	 */
	public Pool4Entry(String mark, String protocol, String address, int min_port, int max_port) {
		this.mark = mark;
		this.protocol = protocol;
		this.address = address;
		this.min_port = min_port;
		this.max_port = max_port;
	}

	/**
	 * Constructor method that receives the read line from the command line and
	 * it's split and stored within a String array. Each element of the array is
	 * a corresponding attribute of the Pool4Entry. They are assigned to each
	 * corresponding attribute.
	 * 
	 * @param currentRow
	 *            String from the Command Line
	 */
	public Pool4Entry(String currentRow) {
		String splitCsvBy = ",";
		String[] pool4Row = currentRow.split(splitCsvBy);
		this.mark = pool4Row[0];
		this.protocol = pool4Row[1];
		this.address = pool4Row[2];
		this.min_port = Integer.parseInt(pool4Row[3]);
		this.max_port = Integer.parseInt(pool4Row[4]);
	}

	/**
	 * Gets the Mark of the Pool4Entry
	 * 
	 * @return this pool4Entry Mark
	 */
	public String getMark() {
		return mark;
	}

	/**
	 * Sets the Mark of the Pool4Entry
	 * 
	 * @param mark
	 *            new mark value
	 */
	public void setMark(String mark) {
		this.mark = mark;
	}

	/**
	 * Gets the Protocol of the Pool4Entry
	 * 
	 * @return this pool4Entry protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Sets the Protocol of the Pool4Entry
	 * 
	 * @param protocol
	 *            new protocol value
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * Gets the Address of the Pool4Entry
	 * 
	 * @return this pool4Entry address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the Address of the Pool4Entry
	 * 
	 * @param mark
	 *            new address value
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Gets the Minimum Port of the Pool4Entry
	 * 
	 * @return this pool4Entry minimum Port
	 */
	public int getMin_port() {
		return min_port;
	}

	/**
	 * Sets the Minimum Port of the Pool4Entry
	 * 
	 * @param mark
	 *            new min_port value
	 */
	public void setMin_port(int min_port) {
		this.min_port = min_port;
	}

	/**
	 * Gets the Max_port of the Pool4Entry
	 * 
	 * @return this pool4Entry max_port
	 */
	public int getMax_port() {
		return max_port;
	}

	/**
	 * Sets the Max_Port of the Pool4Entry
	 * 
	 * @param mark
	 *            new max_port value
	 */
	public void setMax_port(int max_port) {
		this.max_port = max_port;
	}
}
