package ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import valueObjects.MissingProductsVO;
import beans.Component;
import beans.Order;
import beans.OrderDetail;
import beans.Product;

@Stateless
public class FacadeProductGP implements FacadeProduct {
	@PersistenceContext(name = "gestionProductionUnit")
	EntityManager em;

	@EJB
	FacadeOrder facOrder;

	private FacadeOrder getFacOrder() {

		return facOrder;
	}

	@Override
	public int addComponent(int id, int quantity) {
		Component c = em.find(Component.class, id);

		if (c == null)
			return -1;
		c.setQuantity(c.getQuantity() + quantity);
		return c.getQuantity();
	}

	@Override
	public int removeComponent(Integer comp, int quantity) {
		Component c = null;
		try {
			c = em.find(Component.class, comp);
		} catch (NullPointerException e) {
			System.out.println("erreur : " + e.getMessage());
			return -1;
		}

		if (c == null)
			return -1;
		if (c.getQuantity() < quantity)
			quantity = c.getQuantity();
		c.setQuantity(c.getQuantity() - quantity);
		return quantity;
	}

	@Override
	public Collection<MissingProductsVO> missingProducts() {
		List<MissingProductsVO> productsMissing = new ArrayList<MissingProductsVO>();
		List<Order> orders = (List<Order>) getFacOrder().currentOrdersList();
		List<OrderDetail> details = null;
		Product produit = null;
		Integer quantity = null;

		for (Order order : orders) {
			details = (List<OrderDetail>) order.getDetails();
			for (OrderDetail detailInfo : details) {
				produit = detailInfo.getProduct();
				produit = em.find(Product.class, produit.getIdProduct());
				quantity = detailInfo.getQuantity();
				addMissingProduct(productsMissing, produit, quantity);
			}
		}

		List<Product> prods = em.createQuery("From Product p").getResultList();
		System.out.println("Nb prods :" + prods.size());
		for (Product p : prods) {
			addMissingProduct(productsMissing, p, -1 * p.getQuantityStock());
		}
		return productsMissing;
	}

	private void addMissingProduct(List<MissingProductsVO> productsMissing,
			Product prod, Integer quantity) {
		System.out.println("Adding " + quantity + " " + prod.getName());
		for (MissingProductsVO produit : productsMissing) {
			if (produit.getProduct().getIdProduct() == prod.getIdProduct()) {
				if (produit.getQuantity() + quantity <= 0) {
					productsMissing.remove(produit);
				}
				produit.setQuantity(produit.getQuantity() + quantity);

				return;
			}
		}
		if (quantity <= 0) {
			return;
		}

		MissingProductsVO produit = new MissingProductsVO(prod, quantity);
		productsMissing.add(produit);
	}

	@Override
	public Product getInfos(Integer idProduct) {
		return em.find(Product.class, idProduct);
	}

	public Collection<String> listProduct() {
		Query q = em.createQuery("select p.name from Product p");
		return (Collection<String>) q.getResultList();
	}

	public Collection<Product> listProducts() {
		Query q = em.createQuery("from Product p");
		return (Collection<Product>) q.getResultList();
	}

	@Override
	public List<Component> listComponents() {
		Query q = em.createQuery("from Component c");

		return q.getResultList();
	}
}
