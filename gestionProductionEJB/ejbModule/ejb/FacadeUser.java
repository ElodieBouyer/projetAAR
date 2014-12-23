package ejb;

import java.util.List;

import javax.ejb.Local;

import valueObjects.UserVO;
import beans.Role;

@Local
public interface FacadeUser {

	/**
	 * Permet d'ajouter un nouvel utilisateur.
	 * 
	 * @param login
	 * @param fullName
	 * @param password
	 */
	void add(String login, String fullName, String password);

	/**
	 * Cette fonction vérifie si le coupe login/mot de passe est valide et
	 * renvoie l'id de la personne si le couple est bon, null sinon.
	 * 
	 * @param login
	 * @param passwordr
	 * @return
	 */
	Integer connect(String login, String password);

	/**
	 * Vérifie si l'utilisateur à accès à la page demandée avec l'accès
	 * souhaité. exemple : checkAccess("alice", "ajouterUtilisateur", "W"); //
	 * "W" = write, donc écrire un nouvel utilisateur. "R" n'aurait pas de
	 * sens...
	 * 
	 * @param userID
	 * @param pageName
	 * @param accessNeeded
	 * @return
	 */
	Boolean checkAccess(int intUser, String pageName, String accessNeeded);

	/**
	 * Retourne les infos de l'utilisateur sans le mot de passe.
	 * 
	 * @param id
	 * @return
	 */
	UserVO getUserInfos(Integer id);

	/**
	 * Retourne la liste des roles ainsi que des accès.
	 * 
	 * @return
	 */
	List<Role> getAccesses();

}
