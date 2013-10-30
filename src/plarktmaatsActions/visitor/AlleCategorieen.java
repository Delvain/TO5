package plarktmaatsActions.visitor;
import java.util.List;

import plarktmaatsDAO.CategorieDAOImpl;
import plarktmaatsDomein.Categorie;

import com.opensymphony.xwork2.ActionSupport;


public class AlleCategorieen extends ActionSupport {
	private List<Categorie> categorieen;
	
	public String execute() {
		CategorieDAOImpl cat = new CategorieDAOImpl();
		categorieen = cat.getAll();
		return SUCCESS;
	}

	public List<Categorie> getCategorieen() {
		return categorieen;
	}

	public void setCategorieen(List<Categorie> categorieen) {
		this.categorieen = categorieen;
	}
}