package valueObjects;

import beans.Component;

public class ComponentDetailCustomVO {
	private Component component;
	private Integer quantity;

	public Component getComponent() {
		return component;
	}

	public void setComponent(Component component) {
		this.component = component;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
