package plarktmaatsActions.visitor;

import java.util.ArrayList;

import plarktmaatsDAO.CategorieDAOImpl;
import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Categorie;
import plarktmaatsDomein.Veiling;

import com.opensymphony.xwork2.ActionSupport;

public class ToonCategorie extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String cat;
	private ArrayList<Veiling> veilingen = new ArrayList<Veiling>();

	public String execute(){
		CategorieDAOImpl cDI = new CategorieDAOImpl();
		
		Categorie c = cDI.read(cat);
		if(c == null)
			return ActionSupport.INPUT;
		
		VeilingDAOImpl vDI = new VeilingDAOImpl();
		veilingen = vDI.getAllByCategorie(cat);
		
		return SUCCESS;
	}
	
	public String getCat() {
		return cat;
	}
	public ArrayList<Veiling> getVeilingen() {
		return veilingen;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
}
