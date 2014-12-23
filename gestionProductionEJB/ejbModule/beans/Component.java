package beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Component {

	@Id
	@GeneratedValue
	private int idComponent;
	private String name;
	private int quantity;
	private int minimalQuantity;
	
	/**
	 * Created a new component.
	 */
	public Component() {
		super();
	}

	/**
	 * Created a new component.
	 * 
	 * @param name Component name.
	 * @param quantity Quantity component in the database.
	 * @param minimalQuantity Minimal quantity to trigger an alarm.
	 */
	public Component(String name, int quantity, int minimalQuantity) {
		this.name = name;
		this.quantity = quantity;
		this.minimalQuantity = minimalQuantity;
	}
	
	/**
	 * @return the idComponent
	 */
	public int getIdComponent() {
		return idComponent;
	}
	/**
	 * @param idComponent the idComponent to set
	 */
	public void setIdComponent(int idComponent) {
		this.idComponent = idComponent;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		if( quantity < 0 ) this.quantity = 0;
		else this.quantity = quantity;
	}
	/**
	 * @return the minimalQuantity
	 */
	public int getMinimalQuantity() {
		return minimalQuantity;
	}
	/**
	 * @param minimalQuantity the minimalQuantity to set
	 */
	public void setMinimalQuantity(int minimalQuantity) {
		this.minimalQuantity = minimalQuantity;
	}
	
	
	
}
