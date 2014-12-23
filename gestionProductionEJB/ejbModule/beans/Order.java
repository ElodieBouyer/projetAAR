package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OrderTable")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer idOrder;
	@ManyToOne
	private User owner;
	private String state;
	@OneToMany(mappedBy = "order", cascade = { CascadeType.ALL })
	private Collection<OrderDetail> details;


	public Order() {
		this(null, "");
	}

	/**
	 * 
	 * @param owner
	 * @param state
	 */
	public Order(User owner, String state) {
		super();
		this.owner = owner;
		this.state = state;
		details = new ArrayList<OrderDetail>();
	}

	/**
	 * 
	 * @param detail
	 */
	public void addDetail(OrderDetail detail) {
		details.add(detail);
	}

	/**
	 * @return the id
	 */
	public Integer getIdOrder() {
		return idOrder;
	}

	/**
	 * @return the owner
	 */
	public User getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(User owner) {
		this.owner = owner;
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
	 * @return the detail
	 */
	public Collection<OrderDetail> getDetails() {
		return details;
	}

	/**
	 * @param detail
	 *            the detail to set
	 */
	public void setDetails(Collection<OrderDetail> details) {
		this.details = details;
	}

}
