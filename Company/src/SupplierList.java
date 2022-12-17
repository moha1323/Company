import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The Collection class for Supplier objects
 * 
 * @author Abdi Ali, Janaya Thomas, Phong Chang, Yahya Mohamed, Rose Dillon.
 *
 */

public class SupplierList implements Serializable {

	private static final long serialVersionUID = 1L;
	private List suppliers = new LinkedList();
	private static SupplierList supplierList;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private SupplierList() {

	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static SupplierList instance() {
		if (supplierList == null) {
			return (supplierList = new SupplierList());
		} else {
			return supplierList;
		}
	}

	/**
	 * Inserts a supplier into the collection
	 * 
	 * @param supplier
	 *            the supplier to be inserted
	 * @return true iff the supplier could be inserted. Currently always true
	 */
	public boolean insertSupplier(Supplier supplier) {
		suppliers.add(supplier);
		return true;
	}

	/**
	 * Checks whether a supplier with a given supplier id exists.
	 * 
	 * @param supplierID
	 *            the id of the supplier
	 * @return true iff supplier exists
	 * 
	 */
	public Supplier searchForSupplier(String supplierID) {
		for (Iterator iterator = suppliers.iterator(); iterator.hasNext();) {
			Supplier supplier = (Supplier) iterator.next();
			if (supplier.getSupplierID().equals(supplierID)) {
				return supplier;
			}
		}
		return null;
	}

	/**
	 * String form of the collection
	 * 
	 */
	@Override
	public String toString() {
		return suppliers.toString();
	}
}
