package plarktmaatsActions.beheerder;

import java.util.Calendar;

import plarktmaatsDAO.BodDAOImpl;
import plarktmaatsDAO.VeilingDAOImpl;
import plarktmaatsDomein.Bod;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class StatistiekHoogsteBiedingen extends ActionSupport {
	private BodDAOImpl boddata = new BodDAOImpl();
	private VeilingDAOImpl veilingdata = new VeilingDAOImpl();
	private String dag;
	private String week;
	private String maand;
	private String jaar;
	
	public String execute() {
		Calendar vandaag = Calendar.getInstance();
		Calendar gisteren = Calendar.getInstance();
		gisteren.add(Calendar.DATE, -1);
		Calendar vorigeweek = Calendar.getInstance();
		vorigeweek.add(Calendar.DATE, -7);
		Calendar vorigemaand = Calendar.getInstance();
		vorigemaand.add(Calendar.MONTH, -1);
		Calendar vorigjaar = Calendar.getInstance();
		vorigjaar.add(Calendar.YEAR, -1);
		Bod boddag = boddata.getHoogsteBodTussen(gisteren, vandaag);
		Bod bodweek = boddata.getHoogsteBodTussen(vorigeweek, vandaag);
		Bod bodmaand = boddata.getHoogsteBodTussen(vorigemaand, vandaag);
		Bod bodjaar = boddata.getHoogsteBodTussen(vorigjaar, vandaag);
		if (boddag != null) {
			dag = veilingdata.getVeilingNaam(boddag.getVeilingId());
		} else {
			dag = "Geen biedingen in deze periode";
		}
		if (bodweek != null) {
			week = veilingdata.getVeilingNaam(bodweek.getVeilingId());
		} else {
			week = dag;
		}
		if (bodmaand != null) {
			maand = veilingdata.getVeilingNaam(bodmaand.getVeilingId());
		} else {
			maand = week;
		}
		if (jaar != null) {
			jaar = veilingdata.getVeilingNaam(bodjaar.getVeilingId());
		} else {
			jaar = maand;
		}
		return SUCCESS;
	}

	public String getDag() {
		return dag;
	}

	public void setDag(String dag) {
		this.dag = dag;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getMaand() {
		return maand;
	}

	public void setMaand(String maand) {
		this.maand = maand;
	}

	public String getJaar() {
		return jaar;
	}

	public void setJaar(String jaar) {
		this.jaar = jaar;
	}
	
}
