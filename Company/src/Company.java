import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * This class using the Facade Pattern Company project. The class receives user
 * issue requests from the UserInterface class and invokes the method in the
 * appropriate class and returns the results to the interface class.
 * 
 * @author Abdi Ali, Janaya Thomas, Phong Chang, Yahya Mohamed, Rose Dillon.
 */

public class Company implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int COMPONENT_NOT_FOUND = 1;
	public static final int OPERATION_COMPLETED = 2;
	public static final int OPERATION_FAILED = 3;
	public static final int NO_SUCH_SUPPLIER = 4;
	public static final int COMPONENT_HAS_SUPPLIER = 5;
	public static final int BELOW_MINIMUM_QUANTITY = 6;

	private ComponentList componentList;
	private SupplierList supplierList;
	private OrderList orderList;
	private static Company company;

	/**
	 * Private constructor for singleton pattern
	 */
	private Company() {
		componentList = componentList.instance();
		supplierList = supplierList.instance();
		orderList = orderList.instance();
	}

	/**
	 * this supports the singleton pattern, makes user can only create one object of
	 * company list
	 * 
	 * @return component list object
	 */

	public static Company instance() {
		if (company == null) {
			return (company = new Company());
		} else {
			return company;
		}
	}

	/**
	 * Organizes the operations for adding a component
	 * 
	 * @param name name of the component
	 * @return the Component object created
	 */
	public Component addComponent(String name) {
		Component component = new Component(name);
		if (componentList.insertComponent(component)) {
			return (component);
		}
		return null;
	}

	/**
	 * Organizes the operations for adding a supplier
	 * 
	 * @param name name of the supplier
	 * @return the Supplier object created
	 */
	public Supplier addSupplier(String name) {
		Supplier supplier = new Supplier(name);
		if (supplierList.insertSupplier(supplier)) {
			return (supplier);
		}
		return null;
	}

	/**
	 * Searches for a given supplier
	 * 
	 * @param supplierID id of the supplier
	 * @return true iff the supplier is in the supplier list collection
	 */
	public Supplier searchSupplierList(String supplierID) {
		return supplierList.searchForSupplier(supplierID);
	}

	/**
	 * Organizes the adding of a component to a Supplier
	 * 
	 * @param supplierID  supplier id
	 * @param componentID component id
	 * @return if the operation failed or succeed and/or why the operation failed
	 */
	public int addComponentToSupplier(String supplierID, String componentID) {
		Supplier supplier = supplierList.searchForSupplier(supplierID);
		if (supplier == null) {
			return NO_SUCH_SUPPLIER;
		}
		Component component = componentList.searchForComponent(componentID);
		if (component == null) {
			return COMPONENT_NOT_FOUND;
		}
		if (component.getSuppliedBy() != null) {
			return COMPONENT_HAS_SUPPLIER;
		}
		if (!(component.assignSupplier(supplier) && supplier.assignComponent(component))) {
			return OPERATION_FAILED;
		}
		return OPERATION_COMPLETED;
	}

	/**
	 * Searches for a given component
	 * 
	 * @param componentID id of the component
	 * @return true iff the component is in the component list collection
	 */
	public Component searchComponentList(String componentID) {
		return componentList.searchForComponent(componentID);
	}

	/**
	 * Searches for a given order
	 * 
	 * @param orderID id of the order
	 * @return true iff the order is in the order list collection
	 */
	public Order searchOrderList(String orderID) {
		return orderList.searchForOrder(orderID);
	}

	/**
	 * Organizes the oder of a component
	 * 
	 * @param supplierID supplier id
	 * @param orderID    order id
	 * @param quantity   order quantity
	 * @return the order that is added to the supplier
	 */
	public Order orderAComponent(String supplierID, String componentID, int quantity) {
		Order order = new Order(supplierID, componentID, quantity);
		if (order != null) {
			orderList.insertOrder(order);
		}
		return (order);
	}

	/**
	 * Organizes the increment of a product component
	 * 
	 * @param componentID component id
	 * @param quantity    component quantity
	 * @return the quantity the component is decreased by or the reason why the
	 *         operation failed
	 */
	public int assignUnitsToProduct(String componentID, int quantity) {
		if (quantity > 0) {
			Component component = componentList.searchForComponent(componentID);
			if (component != null) {
				return component.decreaseOnStock(quantity);
			} else {
				return COMPONENT_NOT_FOUND;
			}
		} else {
			return BELOW_MINIMUM_QUANTITY;
		}
	}

	/**
	 * Organizes the displaying of information related to the component
	 * 
	 * @param componentID component id
	 * 
	 * @return the string object of the component
	 */
	public String displayComponent(String componentID) {
		Component component = componentList.searchForComponent(componentID);
		if (component != null) {
			return component.toString();
		}
		return null;
	}

	/**
	 * Organizes the displaying of information related to the supplier
	 * 
	 * @param supplierID supplier id
	 * 
	 * @return the string object of the component
	 */
	public String displaySupplier(String supplierID) {
		Supplier supplier = supplierList.searchForSupplier(supplierID);
		if (supplier != null) {
			return supplier.toString();
		}
		return null;
	}

	/**
	 * Organizes the fulfillment of an order
	 * 
	 * @param orderID order id
	 * @return the order that is fulfilled
	 */
	public Order fulfillOrder(String orderID) {
		Order order = orderList.searchForOrder(orderID);
		if (order.isFulfilled()) {
			return null;
		} else {
			Component component = componentList.searchForComponent(order.getComponentId());
			order.setFulfilled(true);
			component.increaseOnStock(order.orderQuantity());
			return order;
		}
	}

	/**
	 * Serializes the Company object
	 * 
	 * @return true iff the data could be saved
	 */
	public static boolean save() {
		try {
			FileOutputStream file = new FileOutputStream("CompanyData");
			ObjectOutputStream output = new ObjectOutputStream(file);
			output.writeObject(company);
			output.writeObject(IDServer.instance());
			file.close();
			return true;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves a deserialized version of the company from disk
	 * 
	 * @return a company object
	 */
	public static Company retrieve() {
		try {
			FileInputStream file = new FileInputStream("CompanyData");
			ObjectInputStream input = new ObjectInputStream(file);
			company = (Company) input.readObject();
			IDServer.retrieve(input);
			return company;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}

	/**
	 * Organizes the displaying of outstanding orders
	 * 
	 * @return String object of orders
	 */
	public String displayOutstandingOrders() {
		List<Order> outstandingOrders = orderList.orderStatusSearch(false);
		String result = "";
		for (Iterator iterator = outstandingOrders.iterator(); iterator.hasNext();) {
			Order order = (Order) iterator.next();
			result += order.toString() + "\n";
		}
		return result;
	}

	/**
	 * Organizes the displaying of a list of components
	 * 
	 * @return String object of a list of components
	 */
	public String displayAllComponents() {
		return componentList.toString();
	}

	/**
	 * Organizes the displaying of a list of suppliers
	 * 
	 * @return String object of a list of suppliers
	 */
	public String displayAllSuppliers() {
		return supplierList.toString();
	}
}
