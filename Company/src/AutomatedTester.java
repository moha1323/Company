/**
 * This class generates sample automated tests for the library system using
 * asserts.
 * 
 * @author Abdi Ali, Janaya Thomas, Phong Chang, Yahya Mohamed, Rose Dillon.
 *
 */
public class AutomatedTester {

	private Company company;

	/**
	 * Stores the Library object and invokes the test() method to test Member
	 * creation.
	 * 
	 * @param library the Library object
	 */
	public AutomatedTester(Company company) {
		this.company = company;
		addComponent();
		addSupplier();
		orderComponent();
	}

	/**
	 * Tests Component creation.
	 */
	public void addComponent() {
		String[] names = { "COM1", "COM2", "COM3" };
		Component[] components = new Component[3];
		for (int count = 0; count < names.length; count++) {
			components[count] = company.addComponent(names[count]);
			assert components[count].getComponentName().equals(names[count]);
		}
	}

	/**
	 * Tests Supplier creation.
	 */
	public void addSupplier() {
		String[] names = { "SUP1", "SUP2", "SUP3" };
		Supplier[] suppliers = new Supplier[3];
		for (int count = 0; count < names.length; count++) {
			suppliers[count] = company.addSupplier(names[count]);
			assert suppliers[count].getSupplierName().equals(names[count]);
		}
	}

	/**
	 * Tests Order creation.
	 */

	public void orderComponent() {
		String[] componentNames = { "COM1", "COM2", "COM3" };
		String[] supplierNames = { "SUP1", "SUP2", "SUP3" };
		String[] orderNames = { "OR1", "OR2", "OR3" };
		Order[] orders = new Order[3];
		for (int count = 0; count < componentNames.length; count++) {
			int rand = (int) Math.random() * 20;
			orders[count] = company.orderAComponent(supplierNames[count], componentNames[count], rand);
			assert orders[count].getOrderID().equals(orderNames[count]);
		}
	}

	/**
	 * Add Component to Supplier
	 */
	public void assignComponentToASupplier() {
		String[] componentNames = { "COM1", "COM2", "COM3" };
		String[] supplierNames = { "SUP1", "SUP2", "SUP3" };
		int[] comSupplier = new int[3];
		for (int count = 0; count < componentNames.length; count++) {
			comSupplier[count] = company.addComponentToSupplier(supplierNames[count], componentNames[count]);
			assert comSupplier[count] == company.OPERATION_COMPLETED;
		}
	}

}
