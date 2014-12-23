package beans;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ComponentDetail implements Serializable {

	private static final long serialVersionUID = 3597273386704730355L;
	
	@Id
	@ManyToOne
	private Product product;
	@Id
	@ManyToOne
	private Component component;
	private int quantity;


	public ComponentDetail() {
		this(null, null, 0);
	}

	public ComponentDetail(Product product, Component component, int quantity) {
		super();
		this.product = product;
		this.component = component;
		this.quantity = quantity;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @param product
	 *            the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * @return the component
	 */
	public Component getComponent() {
		return component;
	}

	/**
	 * @param component
	 *            the component to set
	 */
	public void setComponent(Component component) {
		this.component = component;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
