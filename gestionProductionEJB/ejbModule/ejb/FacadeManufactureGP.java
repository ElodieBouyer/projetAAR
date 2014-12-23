package ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import valueObjects.ComponentDetailCustomVO;
import valueObjects.ProductManufacturingVO;
import beans.Component;
import beans.ComponentDetail;
import beans.Manufacture;
import beans.Product;
import beans.User;

@Stateless
public class FacadeManufactureGP implements FacadeManufacture {

	@PersistenceContext(name = "gestionProductionUnit")
	EntityManager em;

	@EJB
	FacadeProduct facProd;

	private FacadeProduct getFacProd() {

		return facProd;
	}

	@Override
	public Integer newManufacture(String productName, String login,
			Integer quantity) {

		Manufacture man = new Manufacture();
		man.setState("En cours");

		Query q1 = em.createQuery("from Product p where p.name = :name");
		q1.setParameter("name", productName);
		Product prod = (Product) q1.getSingleResult();
		man.setProduct(prod);

		Collection<Component> components = new ArrayList<Component>();
		man.setComponentCheckList(components);

		Query q3 = em.createQuery("from User u where u.login = :login");
		q3.setParameter("login", login);
		User u = (User) q3.getSingleResult();
		man.setOwner(u);

		man.setQuantity(quantity);

		em.persist(man);
		return man.getIdManufacture();
	}

	@Override
	public Integer takeComponents(Integer idManufacture, Integer component,
			int quantity) {
		Integer removed = Integer.valueOf(getFacProd().removeComponent(
				component, quantity));

		if (removed > 0) {
			Manufacture m = em.find(Manufacture.class, idManufacture);
			List<Component> components = (List<Component>) m
					.getComponentCheckList();
			Component c = em.find(Component.class, component);
			for (Integer i = 0; i < removed; i++) {
				components.add(c);
			}
			m.setComponentCheckList(components);
		}

		return removed;
	}

	@Override
	public void rollbackComponents(Integer idManufacture, int idComponent,
			int quantity) {
		getFacProd().addComponent(idComponent, quantity);
	}

	private void validateNewProduct(Integer idManufacture) {
		Manufacture man = em.find(Manufacture.class, idManufacture);

		Product p = man.getProduct();
		List<Component> components = (List<Component>) man
				.getComponentCheckList();
		Integer nbOccurences = 0;
		Integer needed = 0;
		for (ComponentDetail c : p.getComponentDetailList()) {
			needed = c.getQuantity();
			nbOccurences = 0;
			for (int i = 0; i < components.size(); i++) {
				if (components.get(i).getIdComponent() == c.getComponent()
						.getIdComponent() && nbOccurences < needed) {
					components.remove(i);
					nbOccurences++;
				}
			}
		}
		man.setComponentCheckList(components);
		Integer remaining = man.getQuantity();
		man.setQuantity(remaining - 1);
		p.setQuantityStock(p.getQuantityStock() + 1);
		if (remaining - 1 == 0) {
			man = em.find(Manufacture.class, idManufacture);
			man.setState("Terminée");
		}
	}

	@Override
	public Integer getManufactureByUser(Integer UserId) {
		Query q = em
				.createQuery("from Manufacture m where m.owner.idUser=:user AND m.state = 'En cours'");
		q.setParameter("user", UserId);
		try {
			Manufacture m = (Manufacture) q.getSingleResult();
			return m.getIdManufacture();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public ProductManufacturingVO getMakingProduct(Integer manufatureId) {
		Manufacture m = em.find(Manufacture.class, manufatureId);
		ProductManufacturingVO res = new ProductManufacturingVO();
		Product manufacturedProduct = m.getProduct();

		res.setProductName(manufacturedProduct.getName());
		res.setQuantity(m.getQuantity());
		res.setComponentTaken(m.getComponentCheckList());

		List<ComponentDetail> componentsDetail = (List<ComponentDetail>) manufacturedProduct
				.getComponentDetailList();
		List<ComponentDetailCustomVO> componentsNeeded = new ArrayList<ComponentDetailCustomVO>();
		ComponentDetailCustomVO composant = null;

		for (ComponentDetail d : componentsDetail) {
			composant = new ComponentDetailCustomVO();
			composant.setComponent(d.getComponent());
			composant.setQuantity(d.getQuantity() * m.getQuantity());
			componentsNeeded.add(composant);
		}

		for (Component c : m.getComponentCheckList()) {
			for (int i = 0; i < componentsNeeded.size(); i++) {
				if (componentsNeeded.get(i).getComponent().getIdComponent() == c
						.getIdComponent()) {
					componentsNeeded.get(i).setQuantity(
							componentsNeeded.get(i).getQuantity() - 1);
					if (componentsNeeded.get(i).getQuantity() == 0) {
						componentsNeeded.remove(i);
					}
				}
			}
		}

		res.setComponentRemaining(componentsNeeded);

		return res;
	}

	@Override
	public Boolean validManufacture(Integer idManufacture, Integer quantity) {
		Manufacture m = em.find(Manufacture.class, idManufacture);
		Product p = m.getProduct();
		List<Component> components = (List<Component>) m
				.getComponentCheckList();
		Integer nbOccurences = 0;
		Integer needed = 0;
		for (ComponentDetail c : p.getComponentDetailList()) {
			needed = c.getQuantity() * quantity;
			nbOccurences = 0;
			for (int i = 0; i < components.size(); i++) {
				if (components.get(i).getIdComponent() == c.getComponent()
						.getIdComponent()) {
					nbOccurences++;
				}
			}
			if (nbOccurences < needed)
				return false;
		}
		validateNewProduct(idManufacture);
		return true;
	}
}
