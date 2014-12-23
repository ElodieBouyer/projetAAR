package beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class User {

	@GeneratedValue
	@Id
	private Integer idUser;
	private String name;
	private String login;
	private String password;
	@ManyToOne
	private Role role;

	public User() {
		this("","","",null);
	}

	public User(String login, String name, String password, Role role) {
		this.login = login;
		this.name = name;
		this.password = password;
		this.role = role;
	}

	/**
	 * @return the id
	 */
	public Integer getIdUser() {
		return idUser;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setIdUser(int id) {
		this.idUser = id;
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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role
	 *            the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

}
