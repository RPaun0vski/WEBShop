package org.example.resources;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.example.model.DAOOrder;
import org.example.model.PO;


@Path("/webshop")

public class Order extends Application{
	// Samo test metoda http://localhost:8080/api/webshop/sayhi
	@GET
	@Path("/sayhi")
	public Response sayHi() {
		return Response.status(Response.Status.OK)
				.entity("Proba")
				.build();
			
	}
	DAOOrder dAOOrder = new DAOOrder();

	//umesto switch ide @HTML metoda, @Path endpoint koji ce biti hostovan, @Produces ako samo saljemo + @Consumes ako imamo povratnu info
	
	//http://localhost:8080/api/webshop//getOrder/{id}
	@Path ("/getOrder")
	@Produces (MediaType.APPLICATION_JSON)
	public PO getOrder (@PathParam ("id")int id){
		return dAOOrder.getOrder(id);
	}
	
	//http://localhost:8080/api/webshop/create
	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void createOrder(PO po) {   
		dAOOrder.createOrder(po);;
	}

	//http://localhost:8080/api/webshop/update
	@PUT
	@Path("/update")
	@Produces (MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public void updateOrder(PO po) {
		dAOOrder.updateOrder(po);
	}

	//http://localhost:8080/api/webshop/delete/{id}
	
	@DELETE
	@Path("/delete")
	public void deleteOrder (@PathParam ("id")int id) {
		dAOOrder.deleteOrder(id);
		
	}

	
	/**
	 * Servlet implementation class Order
	 */
	
/*@WebServlet("/webshop")
public class Order extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public Order() {
		super();
	}
	//ime=Moma&prezime=Momorivoc&prosek=9.5&sifra=123

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String action=request.getParameter("action");

		// u slucaju izmene u action-u, vraca nas na Indeks.jsp. Ovime stitimo action
		switch (action) {

		case "Kreiraj porudzbenicu":
			createPO(request, response);
			break;
		case "Prikazi":			
			getOrderByID(request, response);
			break;
		case "Izbrisi":			
			deleteOrderByID(request, response);
			break;
		case "Azuriraj":			
			updateOrder(request, response);
			break;
		default:
			response.sendRedirect("index.jsp");
			break;
		}

	}

	// ovakve metode se obicno stave u zasebnu klasu.Obicno se nazivaju StudentServis
	private void createPO (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String product = request.getParameter("product");
		String unit_price = request.getParameter("unit_price");
		String qty = request.getParameter("qty");
		String uom = request.getParameter("UoM");
		String delivery_address = request.getParameter("delivery_address");
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		String status = request.getParameter("PO_status");

		if (	product!=null && product.length()>0 &&
				unit_price!=null && unit_price.length()>0 &&
				qty!=null && qty.length()>0 &&
				uom!=null && uom.length()>0 &&
				delivery_address!=null && delivery_address.length()>0 &&
				phone!=null && phone.length()>0 &&
				status!=null && status.length()>0
				) {
			try {

				double p = Double.parseDouble(unit_price);
				double q = Double.parseDouble(qty);

				PO po = new PO(0, product, p, q, uom, delivery_address, phone, mail, status); 

				DAOOrder dao = new DAOOrder();
				dao.createOrder(po);

				request.setAttribute("msg", "Uspesan unos");
				request.setAttribute("oc", po);
				request.getRequestDispatcher("Order_confirmation.jsp").forward(request, response);
				//Student.jsp jos uvek ne postoji. Sada se pravi nova view stranica
				//Student.jsp koja je prilagodjena da ispisuje jednog studenta


			} catch (Exception e)  {
				request.setAttribute("msg", "Proverite brojcane unose");
				request.getRequestDispatcher("index.jsp").forward(request, response);
				// TODO: handle exception
			}
		}
		else {
			System.out.println("Morate popuniti sva polja");
			request.setAttribute("msg", "Morate popuniti sva polja");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			//moramo reci gde saljemo ovaj atribut prosledjujuci request i response
		}
	}

	private void deleteOrderByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOOrder dao = new DAOOrder();
		String id = request.getParameter("id");

		try {
			int i = Integer.parseInt(id);
			//prvo pozivamo metodu za brisanje...
			dao.deleteOrder(i);
			request.setAttribute("msg", "Uspesno obrisana porudzbina pod rednim brojem: " + id);
			//... a onda metodu za izlistavanje
			request.getRequestDispatcher("index.jsp").forward(request, response);
			//Student.jsp jos uvek ne postoji. Sada se pravi nova view stranica
			//Student.jsp koja je prilagodjena da ispisuje jednog studenta
		}
		catch (Exception e)  {
			request.setAttribute("msg", "id mora biti broj");
			request.getRequestDispatcher("index.jsp").forward(request, response);

		}


	}
	private void getOrderByID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOOrder dao = new DAOOrder();
		String id = request.getParameter("id");

		try {
			int i = Integer.parseInt(id);
			request.setAttribute("msg", "Uspesan prikaz filtrirane porudzbnice prema rednom broju: " + id);
			request.setAttribute("oc", dao.getOrder(i));
			request.getRequestDispatcher("Order_confirmation.jsp").forward(request, response);
			//Student.jsp jos uvek ne postoji. Sada se pravi nova view stranica
			//Student.jsp koja je prilagodjena da ispisuje jednog studenta
		}
		catch (Exception e)  {
			request.setAttribute("msg", "id mora biti broj");
			request.getRequestDispatcher("index.jsp").forward(request, response);

		}

	}

	private void updateOrder (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

		String product = request.getParameter("product");
		String unit_price = request.getParameter("unit_price");
		String qty = request.getParameter("qty");
		String uom = request.getParameter("UoM");
		String delivery_address = request.getParameter("delivery_address");
		String phone = request.getParameter("phone");
		String mail = request.getParameter("mail");
		String status = request.getParameter("PO_status");
		String id = request.getParameter("id");

		if (	product!=null && product.length()>0 &&
				unit_price!=null && unit_price.length()>0 &&
				qty!=null && qty.length()>0 &&
				uom!=null && uom.length()>0 &&
				delivery_address!=null && delivery_address.length()>0 &&
				phone!=null && phone.length()>0 &&
				status!=null && status.length()>0
				) {
			try {
				double p = Double.parseDouble(unit_price);
				double q = Double.parseDouble(qty);
				int i = Integer.parseInt(id);

				PO po = new PO(i, product, p, q, uom, delivery_address, phone, mail, status); 

				DAOOrder dao = new DAOOrder();
				dao.updateOrder(po);

				request.setAttribute("msg", "Uspesna izmena porudzbine");
				//request.setAttribute("studenti", dao.updateOrder(po));
				request.getRequestDispatcher("Order_confirmation.jsp").forward(request, response);
				//Student.jsp jos uvek ne postoji. Sada se pravi nova view stranica
				//Student.jsp koja je prilagodjena da ispisuje jednog studenta
			}
			catch (Exception e)  {
				request.setAttribute("msg", "Neuspesna izmena porudzbine");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);


	}*/
	
}

