package beans;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Manufacture {

	@Id
	@GeneratedValue
	private int idManufacture;
	private String state;
	@ManyToOne
	private Product product;
	private Integer quantity;
	@ManyToMany
	private Collection<Component> componentCheckList;
	@ManyToOne
	private User owner;

	public Manufacture() {
		this("",null,0,new HashSet<Component>() ,null);
	}


	public Manufacture(String state, Product product, Integer quantity,
			Collection<Component> componentCheckList, User owner) {
		super();
		this.state = state;
		this.product = product;
		this.quantity = quantity;
		this.componentCheckList = componentCheckList;
		this.owner = owner;
	}


	/**
	 * @return the idManufacture
	 */

	public int getIdManufacture() {
		return idManufacture;
	}

	/**
	 * @param idManufacture
	 *            the idManufacture to set
	 */
	public void setIdManufacture(int idManufacture) {
		this.idManufacture = idManufacture;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
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
	 * 
	 * @return
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * 
	 * @param owner
	 */
	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * 
	 * @return
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * 
	 * @param quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * 
	 * @return
	 */
	public Collection<Component> getComponentCheckList() {
		return componentCheckList;
	}

	/**
	 * 
	 * @param componentCheckList
	 */
	public void setComponentCheckList(Collection<Component> componentCheckList) {
		this.componentCheckList = componentCheckList;
	}

}
