package beans;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue
	private int idProduct;
	private String name;
	private int quantityStock;
	@OneToMany(mappedBy = "product", cascade = { CascadeType.ALL })
	private Collection<ComponentDetail> componentDetailList;

	public Product() {
		this("");
	}

	public Product(String name) {
		super();
		this.name = name;
		this.quantityStock = 0;
		this.componentDetailList = new HashSet<ComponentDetail>();
	}

	/**
	 * @return the idProduct
	 */
	public int getIdProduct() {
		return idProduct;
	}

	/**
	 * @param idProduct
	 *            the idProduct to set
	 */
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantityStock() {
		return quantityStock;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantityStock(int quantity) {
		if (quantity < 0)
			this.quantityStock = 0;
		else
			this.quantityStock = quantity;
	}

	/**
	 * @return the componentDetailList
	 */
	public Collection<ComponentDetail> getComponentDetailList() {
		return componentDetailList;
	}

	/**
	 * @param componentDetailList
	 *            the componentDetailList to set
	 */
	public void setComponentDetailList(
			Collection<ComponentDetail> componentDetailList) {
		this.componentDetailList = componentDetailList;
	}

}
