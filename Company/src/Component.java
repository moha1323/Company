import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a single Component
 * 
 * @author Abdi Ali, Janaya Thomas, Phong Chang, Yahya Mohamed, Rose Dillon.
 *
 */

public class Component implements Serializable {

	private static final long serialVersionUID = 1L;
	private String componentName;
	private String componentID;
	private int quantityInStock;
	private static final String COMPONENT_STRING = "COM";
	private List listOfSuppliersOfComponents = new LinkedList();// suppliers that supplies one or more component
	private Supplier suppliedBy;

	/**
	 * Creates a component with the given id and name
	 * 
	 * @param name
	 *            supplier name
	 * 
	 */
	public Component(String name) {
		this.componentName = name;
		this.quantityInStock = 0;
		this.componentID = COMPONENT_STRING + (IDServer.instance()).getComponentID();
	}

	/**
	 * Getter for component
	 * 
	 * @return component name
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * Getter for id
	 * 
	 * @return component id
	 */
	public String getComponentID() {
		return componentID;
	}

	/**
	 * Getter for quantity in stock
	 * 
	 * @return quantity
	 */
	public int getQuantityInStock() {
		return quantityInStock;
	}

	/**
	 * Getter for supplier
	 * 
	 * @return the supplier that supplied the component
	 */
	public Supplier getSuppliedBy() {

		for (Iterator iterator = listOfSuppliersOfComponents.iterator(); iterator.hasNext();) {
			Supplier supplier = (Supplier) iterator.next();

			if (supplier.getSupplierID().equals(this.componentID)) {

				suppliedBy = supplier;
			}

		}

		return suppliedBy;
	}

	/**
	 * Stores suppliers of a component
	 * 
	 * @param supplier
	 *            the supplier of the component
	 * @return true iff the component could be supplied. True currently
	 */
	public boolean assignSupplier(Supplier supplier) {
		if (listOfSuppliersOfComponents.add(supplier)) {
			suppliedBy = supplier;
			return true;
		}
		return false;
	}

	/**
	 * Method to increase quantity on stock
	 * 
	 * @param quantity
	 * @return quantityInStock
	 * @throws Exception
	 */
	public int increaseOnStock(int quantity) {
		if (quantity > 0) {
			return quantityInStock += quantity;
		} else {
			return -1;
		}
	}

	/**
	 * Method to decrease onStock quantity
	 * 
	 * @param quantity
	 * @return quantityInStock
	 * @throws Exception
	 */
	public int decreaseOnStock(int quantity) {
		if (quantityInStock > quantity) {
			return quantityInStock -= quantity;
		} else {
			return -1;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((componentID == null) ? 0 : componentID.hashCode());
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
		Component other = (Component) object;
		if (componentID == null) {
			if (other.componentID != null) {
				return false;
			}
		} else if (!componentID.equals(other.componentID)) {
			return false;
		}
		return true;
	}

	/**
	 * String form of the component
	 * 
	 */
	@Override
	public String toString() {
		String displayComponentInformation = "";
		for (Iterator iterator = listOfSuppliersOfComponents.iterator(); iterator.hasNext();) {
			Supplier supplier = (Supplier) iterator.next();
			displayComponentInformation += "Component name=" + componentName + ", Component ID=" + componentID
					+ ", Quantity=" + quantityInStock + ", Supplier Name=" + this.suppliedBy + "\n";
		}
		return displayComponentInformation;
	}
}
