package plarktmaatsActions.beheerder;

import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Veiling;
import tools.ProjectTools;

import com.opensymphony.xwork2.ActionSupport;

public class VerwijderVeiling extends ActionSupport {
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
		dao.delete(id);

		return ActionSupport.SUCCESS;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Veiling getVeiling() {
		return veiling;
	}
}
