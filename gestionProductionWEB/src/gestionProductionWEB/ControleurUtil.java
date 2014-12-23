package gestionProductionWEB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class ControleurUtil {
	/**
	 * Cette fonction renvoi l'id de la personne connectée ou null si cette
	 * personne n'est pas connectée.
	 * 
	 * @param request
	 * @return
	 */
	protected static Integer connectedPeople(HttpServletRequest request) {
		HttpSession session = request.getSession();
		return (Integer) session.getAttribute("userConnected");
	}

	/**
	 * Spécifie l'id de la personne connectée.
	 * 
	 * @param request
	 * @param userId
	 * @return
	 */
	protected static HttpServletRequest setConnectedPeople(
			HttpServletRequest request, Integer userId) {
		HttpSession session = request.getSession();
		session.setAttribute("userConnected", userId);
		return request;
	}
}
