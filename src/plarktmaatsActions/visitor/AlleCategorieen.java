package plarktmaatsActions.visitor;
import java.util.ArrayList;

import plarktmaatsDAO.CategorieDAOImpl;
import plarktmaatsDomein.Categorie;

import com.opensymphony.xwork2.ActionSupport;


public class AlleCategorieen extends ActionSupport {
	private ArrayList<Categorie> categorieen;
	
	public String execute() {
		CategorieDAOImpl cat = new CategorieDAOImpl();
		setCategorieen(cat.getAll());
		return SUCCESS;
	}

	public ArrayList<Categorie> getCategorieen() {
		return categorieen;
	}

	public void setCategorieen(ArrayList<Categorie> categorieen) {
		this.categorieen = categorieen;
	}
}