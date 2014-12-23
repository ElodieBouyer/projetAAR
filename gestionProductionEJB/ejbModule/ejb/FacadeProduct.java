package ejb;

import java.util.Collection;
import java.util.List;

import javax.ejb.Local;

import valueObjects.MissingProductsVO;
import beans.Component;
import beans.Product;

@Local
public interface FacadeProduct {

	/**
	 * Retourne les informations concernant le produit.
	 * 
	 * @param idProduct
	 * @return
	 */
	Product getInfos(Integer idProduct);

	/**
	 * Renvoi la liste des composants.
	 * 
	 * @return
	 */
	List<Component> listComponents();

	/**
	 * Increases the stock of a component.
	 * 
	 * @param id
	 *            Component id.
	 * @param quantity
	 *            Quantity to increase.
	 * @return The new quantity. -1 if component doesn't exist.
	 */
	int addComponent(int id, int quantity);

	/**
	 * Decreases the stock of a component
	 * 
	 * @param comp
	 *            Component id.
	 * @param quantity
	 *            Quantity to decrease.
	 * @return The new quantity removed.
	 */
	int removeComponent(Integer comp, int quantity);

	/**
	 * List of missing products for orders
	 * 
	 * @return
	 */
	Collection<MissingProductsVO> missingProducts();

	/**
	 * Renvoie la liste des noms de produits.
	 * 
	 * @return
	 */
	Collection<String> listProduct();
}
