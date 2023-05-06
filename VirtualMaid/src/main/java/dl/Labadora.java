package dl;

public class Labadora extends GailuaJB{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Labadora() {
		super();
	}
	
	
	public Labadora(int idGailua, String izena, int iraupenaP, float kontsumoa) {
		super(idGailua, izena, "Labadora", iraupenaP, kontsumoa);
		super.setIraupenaPertsonala(iraupenaP);
		super.irudia =  "IRUDIAK/garbigailua.jpg";
		programakBete();
	}
	
	@Override
	protected void programakBete() {
		programak.add("azkarra");
		programak.add("tartekoa");
		programak.add("luzea");
		programak.add("pertsonala");
	}
	
	@Override
	public void setIraupena(String programa) {
		switch (programa) {
		case "azkarra":
			super.setIraupena(30);
			break;
		
		case "tartekoa":
			super.setIraupena(55);
			break;
		
		case "luzea":
			super.setIraupena(110);
			break;
			
		case "pertsonala":
			super.setIraupena(super.getIraupenaPertsonala());
		
		default:
			break;
		}
	}
}

	
