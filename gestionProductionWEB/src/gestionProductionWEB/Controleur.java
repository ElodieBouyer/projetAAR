package gestionProductionWEB;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import valueObjects.ProductManufacturingVO;
import valueObjects.UserVO;
import ejb.FacadeManufacture;
import ejb.FacadeOrder;
import ejb.FacadeProduct;
import ejb.FacadeUser;

@WebServlet("/Controleur")
public class Controleur extends HttpServlet {

	@Resource(lookup = "java:global/gestionProduction/gestionProductionEJB/FacadeUserGP")
	FacadeUser facUser;

	@Resource(lookup = "java:global/gestionProduction/gestionProductionEJB/FacadeManufactureGP")
	FacadeManufacture facManufacture;

	@Resource(lookup = "java:global/gestionProduction/gestionProductionEJB/FacadeOrderGP")
	FacadeOrder facOrder;

	@Resource(lookup = "java:global/gestionProduction/gestionProductionEJB/FacadeProductGP")
	FacadeProduct facProduct;

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doIt(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doIt(request, response);
	}

	private void doIt(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer connected = ControleurUtil.connectedPeople(request);
		String action = request.getParameter("action");

		if (action == null)
			action = "home";

		if (connected != null) {
			UserVO user = facUser.getUserInfos(connected);
			request.setAttribute("name", user.getName());
			request.setAttribute("role", user.getRole());
		}

		if (connected == null) {
			performConnection(request, response, action);
		} else if (action.equals("disconnect")) {

			performDisconnect(request, response);

		} else if (action.equals("home")
				&& facUser.checkAccess(connected, "main", "R")) {

			displayHome(request, response);

		} else if (action.equals("addOrder")
				&& facUser.checkAccess(connected, "order", "W")) {
			displayOrderView(request, response);

		} else if (action.equals("addProduct")
				&& facUser.checkAccess(connected, "product", "W")) {
			actionAddProduct(request, response);

		} else if (action.equals("saveOrder")
				&& facUser.checkAccess(connected, "order", "W")) {
			actionSaveOrder(request, response);
		} else if (action.equals("missingComponents")
				&& facUser.checkAccess(connected, "component", "W")) {
			displayMissingComponents(request, response);

		} else if (action.equals("newComponent")
				&& facUser.checkAccess(connected, "component", "W")) {
			performNewComponent(request, response);

		} else if (action.equals("waitingOrders")
				&& facUser.checkAccess(connected, "order", "R")) {
			displayDeliveryView(request, response);

		} else if (action.equals("takeComponent")
				&& facUser.checkAccess(connected, "component", "W")) {
			performTakeComponent(request, response);

		} else if (action.equals("validOneProduct")
				&& facUser.checkAccess(connected, "product", "R")) {
			performValidOneProduct(request, response);

		} else if (action.equals("waitingProducts")
				&& facUser.checkAccess(connected, "product", "R")) {
			displayWaitingProducts(request, response);

		} else if (action.equals("accesses")
				&& facUser.checkAccess(connected, "users", "R")) {
			displayAccesses(request, response);

		} else if (action.equals("makeProduct")
				&& facUser.checkAccess(connected, "product", "W")) {
			performMakeProduct(request, response);
		} else if (action.equals("sent")
				&& facUser.checkAccess(connected, "order", "W")) {
			actionSentOrder(request, response);
		} else {
			request.setAttribute("connected", connected != null);
			request.getRequestDispatcher("Common/error.jsp").forward(request,
					response);
		}
	}

	/**
	 * Effectue l'ajout d'un nouveau composant. La vue affiché est celle de
	 * "missingComponents"
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void performNewComponent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Integer id = Integer
					.parseInt(request.getParameter("id").toString());
			Integer quantity = Integer.parseInt(request
					.getParameter("quantity").toString());
			facProduct.addComponent(id, quantity);
		} catch (Exception e) {
		}
		displayMissingComponents(request, response);
	}

	/**
	 * Valide la fabrication d'un nouveau composant. Affiche la vue
	 * "waitingProducts"
	 * 
	 * @category Manufature
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void performValidOneProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Integer connected = ControleurUtil.connectedPeople(request);
		Integer manufacture = facManufacture.getManufactureByUser(connected);

		if (manufacture == null) {
			displayHome(request, response);
			return;
		}
		if (facManufacture.validManufacture(manufacture, 1)) {
			request.setAttribute("message", "Produit ajouté au stock.");
		} else {
			request.setAttribute("message",
					"Aucun produit ne peut etre fabriqué avec les composants que vous avez.");
		}
		displayMakingProduct(request, response);
	}

	/**
	 * Cette fonction se charge de prendre les composants demandés pour
	 * assembler un certain produit. Appele la vue "waitingProducts"
	 * 
	 * @category Manufature
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void performTakeComponent(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer connected = ControleurUtil.connectedPeople(request);
		Integer manufacture = facManufacture.getManufactureByUser(connected);
		if (manufacture == null) {
			displayHome(request, response);
			return;
		}
		try {
			Integer idComponent = Integer.parseInt(request.getParameter(
					"componentId").toString());
			Integer quantity = Integer.parseInt(request
					.getParameter("quantity").toString());
			facManufacture.takeComponents(manufacture, idComponent, quantity);
		} catch (Exception e) {
		}
		displayMakingProduct(request, response);

	}

	/**
	 * Cette page permet, à partir de l'id du produit et d'une quantité, de
	 * lancer la frabrication d'un produit.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void performMakeProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer productId = null;
		Integer quantity = null;
		Integer connected = ControleurUtil.connectedPeople(request);

		if (request.getParameter("productId") != null) {
			try {
				productId = Integer.parseInt(request.getParameter("productId")
						.toString());
				quantity = Integer.parseInt(request.getParameter("quantity")
						.toString());
			} catch (Exception e) {
				productId = null;
				quantity = null;
			}
		}

		Integer manufacture = facManufacture.getManufactureByUser(connected);
		if (manufacture == null) {
			if (productId != null) {
				manufacture = facManufacture.newManufacture(facProduct
						.getInfos(productId).getName(),
						facUser.getUserInfos(connected).getLogin(), quantity);
			}
		}

		displayMakingProduct(request, response);
	}

	/**
	 * Affiche la page de fabrication d'un produit. On y retrouve les composants
	 * nécessaires ainsi que la possibilité d'incrémenter le stock du produit si
	 * les composants nécessaires ont été pris par l'assembleur.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayMakingProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer connected = ControleurUtil.connectedPeople(request);
		Integer manufacture = facManufacture.getManufactureByUser(connected);
		if (manufacture == null) {
			displayWaitingProducts(request, response);
			return;
		}
		ProductManufacturingVO productManufacturing = facManufacture
				.getMakingProduct(manufacture);
		request.setAttribute("productManufacturing", productManufacturing);
		request.getRequestDispatcher("Products/makingProduct.jsp").forward(
				request, response);
	}

	/**
	 * Permet de visualiser les produits manquants pour les commandes et d'en
	 * lancer la production.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayWaitingProducts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Integer connected = ControleurUtil.connectedPeople(request);

		Integer manufacture = facManufacture.getManufactureByUser(connected);
		if (manufacture != null)
			displayMakingProduct(request, response);
		else {
			request.setAttribute("missing", facProduct.missingProducts());

			request.getRequestDispatcher("Products/waitingProducts.jsp")
					.forward(request, response);
		}

	}

	/**
	 * Page pour visualiser rapidement les accès des personnes en fonction de
	 * leur role.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayAccesses(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("roles", facUser.getAccesses());
		request.getRequestDispatcher("Admin/accesses.jsp").forward(request,
				response);
	}

	/**
	 * Cette page affiche les composants, leurs stocks et les seuils minimaux.
	 * Le gestionnaire des stocks à la possibilité d'ajouter les composants
	 * nouvellement reçus.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayMissingComponents(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("components", facProduct.listComponents());
		request.getRequestDispatcher("Components/missing.jsp").forward(request,
				response);
	}

	/**
	 * Affiche la page principale.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayHome(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("Common/home.jsp").forward(request,
				response);
	}

	/**
	 * Affiche la page pour le service livraison.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayDeliveryView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		facOrder.isTerminate(); // On met à jour le status des commandes.
		request.setAttribute("currentOrders", facOrder.currentOrdersList());
		request.setAttribute("completedOrders", facOrder.waitingOrdersList());
		request.setAttribute("sentOrders", facOrder.sentOrdersList());

		request.getRequestDispatcher("Orders/delivery.jsp").forward(request,
				response);
	}

	/**
	 * Affiche la page du service commercial.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void displayOrderView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		int idOrder = facOrder
				.newOrder(ControleurUtil.connectedPeople(request));

		request.setAttribute("orderId", idOrder);
		request.setAttribute("orderState", FacadeOrder.STATE_1);
		request.setAttribute("orderOwner",
				facUser.getUserInfos(ControleurUtil.connectedPeople(request))
						.getName());
		request.setAttribute("allProduct", facProduct.listProduct());
		request.setAttribute("productToOrder", facOrder.productToOrder(idOrder));
		System.out.println("Nb produit=" + facOrder.productToOrder(idOrder));
		request.getRequestDispatcher("Orders/order.jsp").forward(request,
				response);
	}

	/**
	 * Effectue une déconnection de l'utilisateur et le redirige vers la page de
	 * login.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void performDisconnect(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ControleurUtil.setConnectedPeople(request, null);
		request.getRequestDispatcher("Common/login.jsp").forward(request,
				response);
	}

	/**
	 * Cette fonction gère la connexion et l'inscription de nouvelles personnes.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void performConnection(HttpServletRequest request,
			HttpServletResponse response, String action)
			throws ServletException, IOException {

		String type = request.getParameter("type");
		String login, password, fullName = null;

		if (type != null) { // type présent = formulaire rempli
			login = request.getParameter("login");
			password = request.getParameter("password");
			if (type.equals("login")) {
				Integer userId = facUser.connect(login, password);
				if (userId != null)
					ControleurUtil.setConnectedPeople(request, userId);
				response.setIntHeader("Refresh", 0); // La page sera rechargée
				// lorsqu'elle
				// s'affichera pour
				// prendre en compte la
				// personne connectée.

			} else {
				fullName = request.getParameter("fullName");
				facUser.add(login, fullName, password);
			}
		}
		if (action.equals("signin")) {
			request.getRequestDispatcher("Common/signin.jsp").forward(request,
					response);
		} else
			request.getRequestDispatcher("Common/login.jsp").forward(request,
					response);

	}

	/**
	 * Envoie d'une commande.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void actionSentOrder(HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String idOrder = request.getParameter("idorder");

			facOrder.sendOrder(Integer.parseInt(idOrder));
			displayDeliveryView(request, response);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ajout d'un produit à une commande.
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void actionAddProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String productName = new String(request.getParameter("name").getBytes(
				StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
		System.out.println("Name= " + productName);
		String quantity = request.getParameter("quantity");
		String orderid = request.getParameter("orderId");

		facOrder.addProduct(Integer.parseInt(orderid), productName,
				Integer.parseInt(quantity));
		displayOrderView(request, response);
	}

	/**
	 * Passe une commande du mode "En attente" à "En cours".
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void actionSaveOrder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String orderId = request.getParameter("orderId");
		facOrder.saveOrder(Integer.parseInt(orderId));
		displayHome(request, response);
	}

}
