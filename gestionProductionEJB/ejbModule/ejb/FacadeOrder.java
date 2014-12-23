package ejb;

import java.util.Collection;

import javax.ejb.Local;

import beans.Order;
import beans.OrderDetail;

@Local
public interface FacadeOrder {

	public final String STATE_1 = "En attente";
	public final String STATE_2 = "En cours";
	public final String STATE_3 = "Terminée";
	public final String STATE_4 = "Envoyée";

	/**
	 * Permet d'ajouter un nouvelle commande.
	 * 
	 * @param products
	 * @param id
	 *            de celui qui créait la commande.
	 * @return idCommande
	 */
	Integer newOrder(Integer id);

	/**
	 * Ajoute un produit à une commande.
	 * 
	 * @param idOrder
	 * @param quantity
	 */
	void addProduct(Integer idOrder, String name, Integer quantity);

	/**
	 * Change le status d'une commande en "Envoyées".
	 * 
	 * @param idOrder
	 */
	void sendOrder(Integer idOrder);

	/**
	 * Liste des commandes terminées, en attente d'être envoyés.
	 * 
	 * @return
	 */
	Collection<Order> waitingOrdersList();

	/**
	 * Liste des commandes en cours.
	 * 
	 * @return
	 */
	Collection<Order> currentOrdersList();

	/**
	 * Liste des commandes terminées et expediées.
	 * 
	 * @return
	 */
	Collection<Order> sentOrdersList();

	/**
	 * Donne les détails d'une commande.
	 * @param id
	 * @return
	 */
	Collection<OrderDetail> productToOrder(Integer id);

	/**
	 * Passe une commande de l'état "En attente" à l'état "En cours".
	 * 
	 * @param id
	 */
	void saveOrder(Integer id);

	/**
	 * Passe une commande en cours en terminée. Décrémente le stock de produit.
	 * 
	 * @param idOrder
	 */
	void terminatesOrder(Order order, Collection<OrderDetail> od);

	/**
	 * Regarde si les commandes sont complettes ou non.
	 */
	void isTerminate();
}
