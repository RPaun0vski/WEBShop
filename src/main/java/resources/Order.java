package resources;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.DAOOrder;
import model.PO;

@Path("/webshop")
public class Order {
	DAOOrder dAOOrder = new DAOOrder();

	//umesto switch ide @HTML metoda, @Path ako smo metodi predali argument, @Produces ako samo saljemo + @Consumes ako imamo povratnu info
	// usmeto switch koji smo radili, metoda @get proizvodi rezultat json, a kao rezultat vraca dao.getAllStudent i kovertuje iz Java u JSON
	@GET
	@Path ("/{id}")
	@Produces (MediaType.APPLICATION_JSON)
	public PO getOrder (@PathParam ("id")int id){
		return dAOOrder.getOrder(id);
	}
	// saljemo studenta i vraca JSON i prihvata JSON (anotacije @Produces i @Consumes)
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createOrder(PO po) {   
		dAOOrder.createOrder(po);;
	}

	@PUT
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateOrder(PO po) {
		dAOOrder.updateOrder(po);
	}

	@DELETE
	@Path("/{id}")
	public void deleteOrder (@PathParam ("id")int id) {
		dAOOrder.deleteOrder(id);
	}
	/**
	 * Servlet implementation class Order
	 */


}
