package domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Entity
@NamedQueries({
	@NamedQuery(name="product.all", query="SELECT x FROM Product x"),
	@NamedQuery(name="product.id", query="SELECT x FROM Product x WHERE x.idProduct = :idProduct"),
	@NamedQuery(name="product.name", query="SELECT x FROM Product x WHERE x.name = :name"),
	@NamedQuery(name="product.category", query="SELECT x FROM Product x WHERE x.category = :category"),
	@NamedQuery(name="product.priceRange", query="SELECT x FROM Product x WHERE x.price BETWEEN :min AND :max")
})

public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProduct;
	private String name;
	@Enumerated(EnumType.STRING)
	private Category category;
	private double price;
	
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
