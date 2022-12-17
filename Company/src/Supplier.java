import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a single Supplier
 * 
 * @author Abdi Ali, Janaya Thomas, Phong Chang, Yahya Mohamed, Rose Dillon.
 *
 */

public class Supplier implements Serializable {
	private static final long serialVersionUID = 1L;
	private String supplierName;
	private String supplierID;
	private static final String SUPPLIER_STRING = "SUP";
	private List componentsSupplied = new LinkedList(); // keeps track of one or more component a supplier

	/**
	 * Creates a supplier with the given id and name
	 * 
	 * @param name
	 *            supplier name
	 * 
	 */
	public Supplier(String name) {
		this.supplierName = name;
		this.supplierID = SUPPLIER_STRING + (IDServer.instance()).getSupplierID();
	}

	/**
	 * Getter for supplier
	 * 
	 * @return supplier name
	 */
	public String getSupplierName() {
		return supplierName;
	}

	/**
	 * Setter for supplier
	 * 
	 */

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	/**
	 * Getter for id
	 * 
	 * @return supplier id
	 */
	public String getSupplierID() {
		return supplierID;
	}

	/**
	 * Stores components supplied by a supplier
	 * 
	 * @param component
	 *            the component to be supplied
	 * @return true iff the component could be marked as supplied. always true
	 *         currently
	 */
	public boolean assignComponent(Component component) {
		if (componentsSupplied.add(component)) {// true
			return true;
		}
		return false;
	}

	/**
	 * Gets an iterator to the components to be supplied
	 * 
	 * @return Iterator to the collection of components supplied
	 */
	public Iterator getSuppliedComponents() {
		return (componentsSupplied.listIterator());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((supplierID == null) ? 0 : supplierID.hashCode());
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
		Supplier other = (Supplier) object;
		if (supplierID == null) {
			if (other.supplierID != null) {
				return false;
			}
		} else if (!supplierID.equals(other.supplierID)) {
			return false;
		}
		return true;
	}

	/**
	 * String form of the supplier
	 * 
	 */
	@Override
	public String toString() {
		String displaySupplierInformation = "";
		for (Iterator iterator = componentsSupplied.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();
			displaySupplierInformation += "Supplier name=" + supplierName + ", Supplier ID=" + supplierID
					+ ", Component Names=" + component.getComponentName() + "\n";
		}
		return displaySupplierInformation;

	}
}
