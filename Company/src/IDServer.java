import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Generates member ids.
 * 
 * @author Abdi Ali, Janaya Thomas, Phong Chang, Yahya Mohamed, Rose Dillon.
 *
 */

public class IDServer implements Serializable {

	private int componentID;
	private int supplierID;
	private int orderID;
	private static IDServer idServer;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private IDServer() {
		this.componentID = 1;
		this.supplierID = 1;
		this.orderID = 1;
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static IDServer instance() {

		if (idServer == null) {
			return (idServer = new IDServer());
		} else {
			return idServer;
		}
	}

	/**
	 * Getter for id
	 * 
	 * @return id of the component
	 */
	public int getComponentID() {
		return componentID++;
	}

	/**
	 * Getter for id
	 * 
	 * @return id of the supplier
	 */
	public int getSupplierID() {
		return supplierID++;
	}

	/**
	 * Getter for id
	 * 
	 * @return id of the order
	 */
	public int getOrderID() {
		return orderID++;
	}

	/**
	 * String form of the collection
	 * 
	 */
	@Override
	public String toString() {
		return ("IDServer" + componentID + " " + supplierID + " " + orderID);
	}

	/**
	 * Retrieves the server object
	 * 
	 * @param input
	 *            inputstream for deserialization
	 */
	public static void retrieve(ObjectInputStream input) {
		try {
			idServer = (IDServer) input.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

}
