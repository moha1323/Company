import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * This class implements the user interface for the Company project. The
 * commands are encoded as integers using a number of static final variables. A
 * number of utility methods exist to make it easier to parse the input.
 * 
 * @author Abdi Ali, Janaya Thomas, Phong Chang, Yahya Mohamed, Rose Dillon.
 */

public class UserInterface {

	private static UserInterface userInterface;
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static Company company;
	private static final int EXIT = 0;
	private static final int ADD_COMPONENT = 1;
	private static final int ADD_SUPPLIER = 2;
	private static final int ADD_COMPONENT_SUPPLIER = 3;
	private static final int ASSIGN_COMPONENT_TO_PRODUCT = 4;
	private static final int ORDER_COMPONENT = 5;
	private static final int ORDER_FULFILLMENT = 6;
	private static final int LIST_COMPONENT = 7;
	private static final int LIST_SUPPLIER = 8;
	private static final int GET_OUTSTANDING_ORDERS = 9;
	private static final int GET_ALL_COMPONENTS = 10;
	private static final int GET_ALL_SUPPLIERS = 11;
	private static final int SAVE = 12;
	private static final int HELP = 13;

	/**
	 * Made private for singleton pattern. Conditionally looks for any saved data.
	 * Otherwise, it gets a singleton Library object.
	 */

	private UserInterface() {
		if (yesOrNo("Look for saved data and  use it?")) {
			retrieve();
		} else {
			company = Company.instance();
			if (yesOrNo("Do you want to generate a test bed and invoke the functionality using asserts?")) {
				new AutomatedTester(company);
			}
		}

	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static UserInterface instance() {
		if (userInterface == null) {
			return userInterface = new UserInterface();
		} else {
			return userInterface;
		}
	}

	/**
	 * Gets a token after prompting
	 * 
	 * @param prompt - whatever the user wants as prompt
	 * @return - the token from the keyboard
	 * 
	 */
	public String getToken(String prompt) {
		do {
			try {
				System.out.println(prompt);
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line, "\n\r\f");
				if (tokenizer.hasMoreTokens()) {
					return tokenizer.nextToken();
				}
			} catch (IOException ioe) {
				System.exit(0);
			}
		} while (true);
	}

	/**
	 * Queries for a yes or no and returns true for yes and false for no
	 * 
	 * @param prompt The string to be prepended to the yes/no prompt
	 * @return true for yes and false for no
	 * 
	 */
	private boolean yesOrNo(String prompt) {
		String more = getToken(prompt + " (Y|y)[es] or anything else for no");
		if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
			return false;
		}
		return true;
	}

	/**
	 * Converts the string to a number
	 * 
	 * @param prompt the string for prompting
	 * @return the integer corresponding to the string
	 * 
	 */
	public int getNumber(String prompt) {
		do {
			try {
				String item = getToken(prompt);
				Integer number = Integer.valueOf(item);
				return number.intValue();
			} catch (NumberFormatException nfe) {
				System.out.println("Please input a number ");
			}
		} while (true);
	}

	/**
	 * Prompts for a command from the keyboard
	 * 
	 * @return a valid command
	 * 
	 */
	public int getCommand() {
		do {
			try {
				int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
				if (value >= EXIT && value <= HELP) {
					return value;
				}
			} catch (NumberFormatException nfe) {
				System.out.println("Enter a number");
			}
		} while (true);
	}

	/**
	 * Displays the help screen
	 * 
	 */
	public void help() {
		System.out.println("Enter a number between 0 and 13 as explained below:");
		System.out.println(EXIT + " to Exit\n");
		System.out.println(ADD_COMPONENT + " to add a component");
		System.out.println(ADD_SUPPLIER + " to  add suppliers");
		System.out.println(ADD_COMPONENT_SUPPLIER + " to  assign component to supplier");
		System.out.println(ASSIGN_COMPONENT_TO_PRODUCT + " to  assign component to product ");
		System.out.println(ORDER_COMPONENT + " to  order a component ");
		System.out.println(ORDER_FULFILLMENT + " to  retrieve filfulled orders");
		System.out.println(LIST_COMPONENT + " to  display a component");
		System.out.println(LIST_SUPPLIER + " to  display a supplier");
		System.out.println(GET_OUTSTANDING_ORDERS + " to  process outstanding orders");
		System.out.println(GET_ALL_COMPONENTS + " to  display a list of all components");
		System.out.println(GET_ALL_SUPPLIERS + " to  display a list of all suppliers");
		System.out.println(SAVE + " to  save data");
		System.out.println(HELP + " for help");
	}

	/**
	 * Method to be called for adding a component. Prompts the user for the
	 * appropriate values and uses the appropriate Company method for adding the
	 * component.
	 * 
	 */
	public void addComponent() {
		Component result;
		do {
			String name = getToken("Enter component name");
			result = company.addComponent(name);
			if (result != null) {
				System.out.println("Added component " + result.getComponentName() + " " + result.getComponentID());
			} else {
				System.out.println("Component could not be added");
			}
		} while (yesOrNo("Add another component?"));
	}

	/**
	 * Method to be called for adding a supplier. Prompts the user for the
	 * appropriate values and uses the appropriate Company method for adding the
	 * supplier.
	 * 
	 */
	public void addSupplier() {
		Supplier result;
		do {
			String name = getToken("Enter supplier name");
			result = company.addSupplier(name);
			if (result != null) {
				System.out.println(result.getSupplierName() + " " + result.getSupplierID());
			} else {
				System.out.println("Supplier could not be added");
			}
		} while (yesOrNo("Add another supplier?"));
	}

	/**
	 * Method to be called for adding a component supplier. Prompts the user for the
	 * appropriate values and uses the appropriate Company method for adding a
	 * component supplier.
	 * 
	 */
	public void addComponentSupplier() {
		String supplierID = getToken("Enter supplier id");
		String componentID = getToken("Enter component id");
		int result;
		do {
			result = company.addComponentToSupplier(supplierID, componentID);
			switch (result) {
			case Company.NO_SUCH_SUPPLIER:
				System.out.println("No such supplier exist.");
				break;
			case Company.COMPONENT_NOT_FOUND:
				System.out.println("No such component exist.");
				break;
			case Company.COMPONENT_HAS_SUPPLIER:
				System.out.println("The component already is supplied by the supplier.");
				break;
			case Company.OPERATION_COMPLETED:
				System.out.println("The assignment succeeded.");
				break;
			case Company.OPERATION_FAILED:
				System.out.println("The assignment failed.");
				break;
			default:
				System.out.println("An error has occurred.");
			}
		} while (yesOrNo("Add more component?"));

	}

	/**
	 * Method to be called for assigning number of units of component to a product.
	 * Prompts the user for the appropriate values and uses the appropriate Company
	 * method for assigning components to a product.
	 * 
	 */
	public void assignComponentToProduct() {
		String componentID = getToken("Enter component id");
		int quantity = Integer.parseInt(getToken("Enter component quantity"));
		int result;
		result = company.assignUnitsToProduct(componentID, quantity);
		switch (result) {
		case Company.BELOW_MINIMUM_QUANTITY:
			System.out.println("Quantity isn't greater than zero.");
			break;
		case Company.COMPONENT_NOT_FOUND:
			System.out.println("No such component.");
			break;
		default:
			System.out.println("Component left in stock " + result + " units.");
		}

	}

	/**
	 * Method to be called for ordering components. Prompts the user for the
	 * appropriate values and uses the appropriate Company method for returning
	 * books.
	 * 
	 */
	public void orderComponent() {
		Order result;
		do {
			String componentID = getToken("Enter component id");
			String supplierID = getToken("Enter supplier id");
			int quantity = Integer.parseInt(getToken("Enter component quantity"));
			if (quantity < 0) {
				System.out.println("Quantity Can't Be Negivate: " + quantity);
			} else {
				if (company.searchSupplierList(supplierID) == null) {
					System.out.println("No such supplier");
					return;
				}
				if (company.searchComponentList(componentID) == null) {
					System.out.println("No such component");
					return;
				}

				result = company.orderAComponent(supplierID, componentID, quantity);
				if (result != null) {
					System.out.println(result.getOrderID() + "   " + result.toString());
				} else {
					System.out.println("Order could not be created");
				}
			}
		} while (yesOrNo("Add another?"));
	}

	/**
	 * Method to be called for fulfilling an order. Prompts the user for the
	 * appropriate values and uses the appropriate company method for fulfilling
	 * orders.
	 * 
	 */
	public void orderFulfillment() {
		Order result;
		do {
			String orderID = getToken("Enter order id");
			if (company.searchOrderList(orderID) == null) {
				System.out.println("Order ID is invalid");
			}
			result = company.fulfillOrder(orderID);
			if (result != null) {
				System.out.println(result.toString());
			} else {
				System.out.println("Order was previously fulfilled");
			}
		} while (yesOrNo("Verify another?"));

	}

	/**
	 * Method to be called for listing a component. Prompts the user for the
	 * appropriate values and uses the appropriate company method for listing a
	 * component.
	 * 
	 */
	public void listComponent() {
		String componentID = getToken("Enter component id");
		if (company.searchComponentList(componentID) == null) {
			System.out.println("No such component");
		} else {
			System.out.println(company.displayComponent(componentID));
		}
	}

	/**
	 * Method to be called for listing a supplier. Prompts the user for the
	 * appropriate values and uses the appropriate company method for listing a
	 * supplier.
	 * 
	 */
	public void listSupplier() {
		String result;
		String supplierID = getToken("Enter supplier id");
		if (company.searchSupplierList(supplierID) == null) {
			System.out.println("No such supplier");
			return;
		} else {
			result = company.displaySupplier(supplierID);
			if (result != null) {
				System.out.println(result);
			}
		}
	}

	/**
	 * Method to be called for displaying outstanding orders.
	 * 
	 */
	public void displayOutstandingOrders() {
		String displayOutstandingOrders = company.displayOutstandingOrders();
		if (!displayOutstandingOrders.equals("")) {
			System.out.println(displayOutstandingOrders);
		} else {
			System.out.println("No outstanding orders.");
		}
	}

	/**
	 * Method to be called for displaying all components.
	 * 
	 */
	public void displayAllComponents() {
		System.out.println(company.displayAllComponents());
	}

	/**
	 * Method to be called for displaying all suppliers.
	 * 
	 */
	public void displayAllSuppliers() {
		System.out.println(company.displayAllSuppliers());
	}

	/**
	 * Method to be called for saving the Company object. Uses the appropriate
	 * Company method for saving.
	 * 
	 */
	private void save() {
		if (company.save()) {
			System.out.println(" The library has been successfully saved in the file LibraryData \n");
		} else {
			System.out.println(" There has been an error in saving \n");
		}
	}

	/**
	 * Method to be called for retrieving saved data. Uses the appropriate Company
	 * method for retrieval.
	 * 
	 */
	private void retrieve() {
		try {
			if (company == null) {
				company = Company.retrieve();
				if (company != null) {
					System.out.println(" The company has been successfully retrieved from the file CompanyData \n");
				} else {
					System.out.println("File doesnt exist; creating new company");
					company = Company.instance();
				}
			}
		} catch (Exception cnfe) {
			cnfe.printStackTrace();
		}
	}

	/**
	 * Orchestrates the whole process. Calls the appropriate method for the
	 * different functionalities.
	 * 
	 */
	public void process() {
		int command;
		help();
		while ((command = getCommand()) != EXIT) {
			switch (command) {
			case ADD_COMPONENT:
				addComponent();
				break;
			case ADD_SUPPLIER:
				addSupplier();
				break;
			case ADD_COMPONENT_SUPPLIER:
				addComponentSupplier();
				break;
			case ASSIGN_COMPONENT_TO_PRODUCT:
				assignComponentToProduct();
				break;
			case ORDER_COMPONENT:
				orderComponent();
				break;
			case ORDER_FULFILLMENT:
				orderFulfillment();
				break;
			case LIST_COMPONENT:
				listComponent();
				break;
			case LIST_SUPPLIER:
				listSupplier();
				break;
			case GET_OUTSTANDING_ORDERS:
				displayOutstandingOrders();
				break;
			case GET_ALL_COMPONENTS:
				displayAllComponents();
				break;
			case GET_ALL_SUPPLIERS:
				displayAllSuppliers();
				break;
			case SAVE:
				save();
				break;
			case HELP:
				help();
				break;
			}
		}
	}

	/**
	 * The method to start the application. Simply calls process().
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		UserInterface.instance().process();

	}

}
