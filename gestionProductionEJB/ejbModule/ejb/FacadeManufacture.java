package ejb;

import javax.ejb.Local;

import valueObjects.ProductManufacturingVO;

@Local
public interface FacadeManufacture {
	/**
	 * Signale la création en cours d'un nouveau produit.
	 * 
	 * @param productName
	 * @param login
	 * @param quantity
	 * @return
	 */
	Integer newManufacture(String productName, String login, Integer quantity);

	/**
	 * Permet de valider "quantity" nouveaux produits si les composants
	 * nécessaires ont été pris par l'assembleur.
	 * 
	 * @param idManufacture
	 * @param quantity
	 * @return true si les produits ont pu etre fabriqués, false sinon.
	 */
	Boolean validManufacture(Integer idManufacture, Integer quantity);

	/**
	 * Permet de prendre des composants du stock pour les ajouter à la
	 * fabrication en cours.
	 * 
	 * @param idManufacture
	 * @param component
	 * @param quantity
	 * @return la quantité réellement retirée.
	 */
	Integer takeComponents(Integer idManufacture, Integer component,
			int quantity);

	/**
	 * En cas d'annulation de la fabrication on remet tous les composants dans
	 * le stock.
	 * 
	 * @param idManufacture
	 * @param nameComponent
	 * @param quantity
	 */
	void rollbackComponents(Integer idManufacture, int idComponent, int quantity);

	/**
	 * Renvoie l'ID de la manufacture en cours pour un utilisateur donnée. Null
	 * s'il n'en a pas en cours.
	 * 
	 * @param UserId
	 * @return
	 */
	Integer getManufactureByUser(Integer UserId);

	/**
	 * Cette fonction retourne toutes les informations relative au produit en
	 * cours de fabrication. Le produits, les composants prits, les composants
	 * nécessaires.
	 * 
	 * @param manufatureId
	 * @return
	 */
	ProductManufacturingVO getMakingProduct(Integer manufatureId);
}
