import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * The Collection class for Component objects
 * 
 * @author Abdi Ali, Janaya Thomas, Phong Chang, Yahya Mohamed, Rose Dillon.
 *
 */
public class ComponentList implements Serializable {

	private static final long serialVersionUID = 1L;
	private List components = new LinkedList();
	private static ComponentList componentList;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private ComponentList() {

	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static ComponentList instance() {
		if (componentList == null) {
			return (componentList = new ComponentList());
		} else {
			return componentList;
		}
	}

	/**
	 * Inserts a component into the collection
	 * 
	 * @param component
	 *            the component to be inserted
	 * @return true if the component could be inserted. Currently always true
	 */
	public boolean insertComponent(Component component) {
		components.add(component);
		return true;
	}

	/**
	 * Checks whether a component with a given component id exists.
	 * 
	 * @param componentID
	 *            the id of the component
	 * @return true iff the component exists
	 * 
	 */
	public Component searchForComponent(String componentID) {
		for (Iterator iterator = components.iterator(); iterator.hasNext();) {
			Component component = (Component) iterator.next();
			if (component.getComponentID().equals(componentID)) {
				return component;
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
		return components.toString();
	}
}
