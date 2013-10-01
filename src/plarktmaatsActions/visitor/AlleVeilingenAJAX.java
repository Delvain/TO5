package plarktmaatsActions.visitor;

import com.opensymphony.xwork2.ActionSupport;

public class AlleVeilingenAJAX extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String timer = "900";
	private String prijs = "30";
	private String id = "1";

	public String execute(){
		id = id.trim();
		if(id.length() < 0)
			return ERROR;
		
//		VeilingDAOImpl vDI = new VeilingDAOImpl();
//		Veiling v = vDI.read(id);
//		if(v == null)
//			return ERROR;
		
//		if(v.getHoogsteBod() != null)
//			prijs = v.getHoogsteBod().getBedrag()+"";
//		else
//			prijs = v.getMinBedrag()+"";
		
//		Date eindTijd 	= v.getEindTijd().getTime();
//		Date nuTijd 	= Calendar.getInstance().getTime();
//		long verschil	= eindTijd.getTime() - nuTijd.getTime();
//		if(verschil < 1)
//			timer = "Gesloten";
//		int seconds		= (int) (verschil / 1000 % 60);
//		timer = ""+seconds;
		
		return SUCCESS;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getPrijs() {
		return prijs;
	}
	public String getTimer() {
		return timer;
	}
}
