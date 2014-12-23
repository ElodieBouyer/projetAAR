package beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity 
public class Access {

	@Id
	@GeneratedValue
	private int idAccess;
	private String pageName;
	private String rights;
	
	public Access() {
		this("", "");
	}
	
	public Access(String page, String rights) {
		super();
		this.pageName = page;
		this.rights = rights;
	}
	
	/**
	 * @return the id
	 */
	public int getIdAccess() {
		return idAccess;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setIdAccess(int id) {
		this.idAccess = id;
	}
	
	/**
	 * @return the pageName
	 */
	public String getPageName() {
		return pageName;
	}
	
	/**
	 * @param pageName the pageName to set
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	
	/**
	 * @return the rights
	 */
	public String getRights() {
		return rights;
	}
	
	/**
	 * @param rights the rights to set
	 */
	public void setRights(String rights) {
		this.rights = rights;
	}
	
	
	
}
