package plarktmaatsActions.visitor;

import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Veiling;
import tools.ProjectTools;

import com.opensymphony.xwork2.ActionSupport;

public class ToonVeiling extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Veiling veiling;
	private String id;

	public String execute() {
		VeilingDAOImpl dao = new VeilingDAOImpl();
		if(!ProjectTools.isNumeric(id))
			return ActionSupport.INPUT;
		if(dao.read(id) == null) {
			return ActionSupport.INPUT;
		}
		
		veiling = dao.read(id);

		return ActionSupport.SUCCESS;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Veiling getVeiling() {
		return veiling;
	}
}
