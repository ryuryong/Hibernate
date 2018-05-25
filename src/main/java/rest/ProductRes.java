package rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Category;
import domain.Product;
import domain.Services.ProductS;



@Path("/parts")
@Stateless
public class ProductRes {

	private ProductS db = new ProductS();
	
	@PersistenceContext
	EntityManager em;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Product product){
		em.persist(product);
		return Response.ok(product.getIdProduct()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product>getAll()
	{
		return em.createNamedQuery("product.all", Product.class).getResultList();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id){
		Product result = em.createNamedQuery("product.id", Product.class)
							.setParameter("idProduct", id)
							.getSingleResult();
		if(result==null){
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
	
	@GET
	@Path("/{name}/name")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getByName(@PathParam("name") String name) {
		return em.createNamedQuery("product.name", Product.class)
			.setParameter("name", name)
			.getResultList();
	}
	
	@GET
	@Path("/{category}/category")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getByCategory(@PathParam("category") Category category) {
		return em.createNamedQuery("product.category", Product.class)
			.setParameter("category", category)
			.getResultList();
	}
	
	@GET
	@Path("/{min}/price/{max}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getByPriceRange(@PathParam("min") Double min, @PathParam("max") Double max) {
		return em.createNamedQuery("product.priceRange", Product.class)
			.setParameter("min", min)
			.setParameter("max", max)
			.getResultList();
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id){
		Product result = em.createNamedQuery("product.id", Product.class)
				.setParameter("idProduct", id)
				.getSingleResult();
		if(result==null)
			return Response.status(404).build();
		em.remove(result);
		return Response.ok().build();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Product product) {
		Product result = em.createNamedQuery("product.id", Product.class)
			.setParameter("idProduct", id)
			.getSingleResult();
		
		if (result == null) {
			return Response.status(404).build();
		}

		if (product.getName() != null) {
			result.setName(product.getName());
		}
		
		if (product.getPrice() != 0) {
			result.setPrice(product.getPrice());
		}
		if (product.getCategory() != null) {
			result.setCategory(product.getCategory());
		}

		em.persist(result);

		return Response.ok().build();
	}
	
}