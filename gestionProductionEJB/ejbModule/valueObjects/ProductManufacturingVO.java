package valueObjects;

import java.util.Collection;

import beans.Component;

public class ProductManufacturingVO {
	private String productName;
	private Integer quantity;
	/**
	 * Contient les composants déjà pris dans le stock.
	 */
	private Collection<Component> componentTaken;
	/**
	 * Contient les composants pouvant être pris dans le stock mais qui ne le
	 * son pas encore.
	 */
	private Collection<ComponentDetailCustomVO> componentRemaining;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Collection<Component> getComponentTaken() {
		return componentTaken;
	}

	public void setComponentTaken(Collection<Component> componentTaken) {
		this.componentTaken = componentTaken;
	}

	public Collection<ComponentDetailCustomVO> getComponentRemaining() {
		return componentRemaining;
	}

	public void setComponentRemaining(
			Collection<ComponentDetailCustomVO> componentRemaining) {
		this.componentRemaining = componentRemaining;
	}
}
