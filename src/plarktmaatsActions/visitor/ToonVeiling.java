package plarktmaatsActions.visitor;

import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Veiling;

import com.opensymphony.xwork2.ActionSupport;

public class ToonVeiling extends ActionSupport {
	private VeilingDAOImpl database = new VeilingDAOImpl();
	private Veiling veiling = new Veiling();
	private int veilingId;

	public String execute() {
		veiling = database.read("" + veilingId);

		return ActionSupport.SUCCESS;
	}

	public void validate() {
		if (database.read("" + veilingId) == null) {
			addFieldError("veilingId", "Veiling is onbekend");
		}
	}

	public int getVeilingId() {
		return veilingId;
	}

	public void setVeilingId(int bi) {
		veilingId = bi;
	}

	public Veiling getVeiling() {
		return veiling;
	}

	public void setVeiling(Veiling v) {
		veiling = v;
	}

}
