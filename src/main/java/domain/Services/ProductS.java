package domain.Services;

import java.util.ArrayList;
import java.util.List;

import domain.Product;

public class ProductS {
	
	private static List<Product> db = new ArrayList<Product>();
	private static int curId = 0;
	public List<Product> getAll(){
		return db;
	}

	public Product get(int id){
		for (Product x :db){
			if(x.getIdProduct()==id)
				return x;
		}
		return null;
	}
	
	public void add(Product x){
		x.setIdProduct(++curId);;
		db.add(x);
	}
	
	public void delete(Product x){
		db.remove(x);
	}	
	
	public void update(Product product){
		for(Product x :db){
			if(x.getIdProduct()==product.getIdProduct()){
				x.setName(product.getName());
				x.setCategory(product.getCategory());
				x.setPrice(product.getPrice());
			}
		}
	}
}