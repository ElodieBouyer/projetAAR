package ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import valueObjects.UserVO;
import beans.Access;
import beans.Role;
import beans.User;

@Stateless
public class FacadeUserGP implements FacadeUser {
	@PersistenceContext(name = "gestionProductionUnit")
	EntityManager em;

	@Override
	public void add(String login, String fullName, String password) {
		Query q = em.createQuery("from Role q where q.name=:name");
		q.setParameter("name", "Nill");
		Role r = (Role) q.getSingleResult();
		User u = new User();
		u.setLogin(login);
		u.setName(fullName);
		u.setPassword(password);
		u.setRole(r);
		em.persist(u);
	}

	@Override
	public Integer connect(String login, String password) {
		Query q = em
				.createQuery("from User u where u.login = :login and u.password = :password");
		q.setParameter("login", login);
		q.setParameter("password", password);
		try {
			User u = (User) q.getSingleResult();
			if (u != null)
				return u.getIdUser();
			return null;
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public Boolean checkAccess(int intUser, String pageName, String accessNeeded) {
		User u = em.find(User.class, intUser);
		if (u != null) {
			List<Access> accesses = (List<Access>) u.getRole().getAccessList();
			for (Access access : accesses) {
				if (access.getPageName().equals(pageName)) {
					return access.getRights().contains(accessNeeded);
				}
			}
		}

		return false;
	}

	@Override
	public UserVO getUserInfos(Integer id) {
		UserVO u = new UserVO();
		User user = em.find(User.class, id);
		u.setIdUser(id);
		u.setName(user.getName());
		u.setLogin(user.getLogin());
		u.setRole(user.getRole().getName());
		return u;
	}

	@Override
	public List<Role> getAccesses() {
		Query q = em.createQuery("From Role");
		List<Role> roles = q.getResultList();
		for (Role r : roles) {
			r.getAccessList(); // chargement des accès.
		}
		return roles;
	}

}
