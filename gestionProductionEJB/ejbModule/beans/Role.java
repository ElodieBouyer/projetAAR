package beans;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private int idRole;
	private String name;
	@ManyToMany(cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private Collection<Access> accessList; // One role, to many access.

	public Role() {
		this("",new HashSet<Access>());
	}
	
	public Role(String name, Collection<Access> accessList) {
		super();
		this.name = name;
		this.accessList = accessList;
	}

	/**
	 * @return the id
	 */

	public int getIdRole() {
		return idRole;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setIdRole(int id) {
		this.idRole = id;
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
	 * @return the accessList
	 */
	public Collection<Access> getAccessList() {
		return accessList;
	}

	/**
	 * @param accessList
	 *            the accessList to set
	 */
	public void setAccessList(Collection<Access> accessList) {
		this.accessList = accessList;
	}

}
