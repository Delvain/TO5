package biebService;

public class ServiceProvider {
	private static IBiebService biebService = new BiebService();
	public static IBiebService getBiebService() {
		return biebService;
	}
}
