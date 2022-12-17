import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The collection class for Order objects
 * 
 * @author Abdi Ali, Janaya Thomas, Phong Chang, Yahya Mohamed, Rose Dillon.
 *
 */
public class OrderList implements Serializable {

	private List orders = new LinkedList();
	private static OrderList orderList;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private OrderList() {

	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static OrderList instance() {
		if (orderList == null) {
			return (orderList = new OrderList());
		} else {
			return orderList;
		}
	}

	/**
	 * Inserts an order into the collection
	 * 
	 * @param order
	 *            the order to be inserted
	 * @return true iff the order could be inserted. Currently always true
	 */
	public boolean insertOrder(Order order) {
		orders.add(order);
		return true;
	}

	/**
	 * Checks whether an order with a given order id exists.
	 * 
	 * @param orderID
	 *            the id of the order
	 * @return true iff order exists
	 * 
	 */
	public Order searchForOrder(String orderID) {
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order = (Order) iterator.next();
			if (order.getOrderID().equals(orderID)) {
				return order;
			}
		}
		return null;
	}

	/**
	 * Checks whether an order is fulfilled
	 * 
	 * @param fulfilled
	 * @return the status of a fulfilled order
	 */
	public List orderStatusSearch(boolean fulfilled) {
		List<Order> orderStatus = new LinkedList();
		for (Iterator iterator = orders.iterator(); iterator.hasNext();) {
			Order order = (Order) iterator.next();
			if (order.isFulfilled() == fulfilled) {
				orderStatus.add(order);
			}
		}
		return orderStatus;
	}

	/**
	 * String form of the collection
	 * 
	 */
	@Override
	public String toString() {
		return orders.toString();
	}
}
