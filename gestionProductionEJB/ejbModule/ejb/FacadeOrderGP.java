package ejb;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import beans.Order;
import beans.OrderDetail;
import beans.Product;
import beans.User;

@Stateless
public class FacadeOrderGP implements FacadeOrder {

	@PersistenceContext(name = "gestionProductionUnit")
	EntityManager em;

	@Override
	public Integer newOrder(Integer id) {
		User user = (User) em.find(User.class, id);

		Query q = em.createQuery("from Order o where o.state = :state ");
		q.setParameter("state", STATE_1);
		Order o = null;
		try {
			o = (Order) q.getSingleResult();
		} catch (NoResultException e) {

		} finally {

			if (o == null) {
				Collection<OrderDetail> details = new ArrayList<OrderDetail>();
				o = new Order();
				o.setDetails(details);
				o.setOwner(user);
				o.setState(STATE_1);
				em.persist(o);
			}
		}

		return o.getIdOrder();
	}

	@Override
	public Collection<OrderDetail> productToOrder(Integer id) {
		Order o = em.find(Order.class, id);
		Query q = em.createQuery("from OrderDetail o where o.order = :order");
		q.setParameter("order", o);
		Collection<OrderDetail> od = (Collection<OrderDetail>) q
				.getResultList();
		return od;
	}

	@Override
	public void addProduct(Integer idOrder, String name, Integer quantity) {
		Query q = em.createQuery("from Product p WHERE p.name=:name");
		q.setParameter("name", name);
		Product p = (Product) q.getSingleResult();

		Order o = em.find(Order.class, idOrder);
		boolean ok = true;

		try {
			Query q2 = em.createQuery("from OrderDetail od "
					+ "where od.order = :order and od.product = :product");
			q2.setParameter("order", o);
			q2.setParameter("product", p);
			OrderDetail od = (OrderDetail) q2.getSingleResult();
			od.setQuantity(od.getQuantity() + quantity);
			ok = false;
		} catch (NoResultException e) {
		} finally {
			if (!ok)
				return;
			OrderDetail od2 = new OrderDetail();
			od2.setProduct(p);
			od2.setQuantity(quantity);
			od2.setOrder(o);
			em.persist(od2);

			o.addDetail(od2);
		}
	}

	@Override
	public void sendOrder(Integer idOrder) {
		Order o = (Order) em.find(Order.class, idOrder);
		o.setState(STATE_4);
	}

	@Override
	public void terminatesOrder(Order order, Collection<OrderDetail> od) {

		// On décremente le stock de produit.
		for (OrderDetail detail : od) {
			Product p = detail.getProduct();
			p.setQuantityStock(p.getQuantityStock() - detail.getQuantity());
		}
		order.setState(STATE_3);
	}

	@Override
	public void isTerminate() {
		// On récupère les commandes en cours.
		Collection<Order> currentOrder = currentOrdersList();
		boolean complet = true;

		// On regarde si une commande est complette.
		for (Order o : currentOrder) {
			Query q = em
					.createQuery("from OrderDetail od where od.order = :order");
			q.setParameter("order", o);

			Collection<OrderDetail> od = (Collection<OrderDetail>) q
					.getResultList();
			// On regarde si il y a assez de produit.
			for (OrderDetail detail : od) {
				Product p = detail.getProduct();

				if (detail.getQuantity() > p.getQuantityStock()) {
					complet = false;
					break;
				}
			}
			if (complet)
				terminatesOrder(o, od);
			complet = true;
		}
	}

	@Override
	public Collection<Order> waitingOrdersList() {
		Query q = em.createQuery("from Order o where o.state = :state");
		q.setParameter("state", STATE_3);
		Collection<Order> lst = (Collection<Order>) q.getResultList();
		return lst;
	}

	@Override
	public Collection<Order> currentOrdersList() {
		Query q = em.createQuery("from Order o where o.state = :state");
		q.setParameter("state", STATE_2);
		Collection<Order> lst = (Collection<Order>) q.getResultList();
		return lst;
	}

	@Override
	public Collection<Order> sentOrdersList() {
		Query q = em.createQuery("from Order o where o.state = :state");
		q.setParameter("state", STATE_4);
		Collection<Order> lst = (Collection<Order>) q.getResultList();
		return lst;
	}

	@Override
	public void saveOrder(Integer id) {
		Order o = em.find(Order.class, id);
		o.setState(STATE_2);
	}
}
