import java.io.Serializable;

/**
 * Represents a single Order
 * 
 * @author Abdi Ali, Janaya Thomas, Phong Chang, Yahya Mohamed, Rose Dillon.
 *
 */

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private String orderSupplierID;
	private String orderComponentID;
	private String orderID;
	private int orderQuantity;
	private boolean isFulfilled;
	private static final String ORDER_STRING = "OR";

	/**
	 * Creates an order with the given id and name
	 * 
	 * @param supplierID
	 *            supplier id
	 * @param componentID
	 *            component id
	 * @param quantity
	 *            order quantity
	 */
	public Order(String supplierID, String componentID, int quantity) {
		this.orderSupplierID = supplierID;
		this.orderComponentID = componentID;
		this.orderQuantity = quantity;
		this.isFulfilled = false;
		this.orderID = ORDER_STRING + (IDServer.instance()).getOrderID();
	}

	/**
	 * Getter for id
	 * 
	 * @return order id
	 */
	public String getOrderID() {
		return orderID;
	}

	/**
	 * Getter for fulfillment
	 * 
	 * @return true if order is fulfilled and false if not
	 */
	public boolean isFulfilled() {
		return isFulfilled;
	}

	/**
	 * Setter for fulfillment
	 * 
	 */
	public void setFulfilled(boolean isFulfilled) {
		this.isFulfilled = isFulfilled;
	}

	/**
	 * Getter for componentID
	 * 
	 * @return component id
	 */
	public String getComponentId() {
		return orderComponentID;
	}

	/**
	 * Getter for orderQuantity
	 * 
	 * @return order quantity
	 */
	public int orderQuantity() {
		return orderQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderID == null) ? 0 : orderID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Order other = (Order) object;
		if (orderID == null) {
			if (other.orderID != null) {
				return false;
			}
		} else if (!orderID.equals(other.orderID)) {
			return false;
		}
		return true;
	}

	/**
	 * String form of the order
	 * 
	 */
	@Override
	public String toString() {
		return "Order [orderSupplierID=" + orderSupplierID + ", orderComponentID=" + orderComponentID + ", orderID="
				+ orderID + ", orderQuantity=" + orderQuantity + ", isFulfilled=" + isFulfilled + "]";
	}
}
