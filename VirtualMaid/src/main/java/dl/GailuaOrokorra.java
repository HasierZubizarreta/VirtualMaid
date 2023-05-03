package dl;

public class GailuaOrokorra extends GailuaJB {

	public GailuaOrokorra() {
		super();
	}

	
	public GailuaOrokorra(int idGailua, String izena, int iraupenaP, float kontsumoa) {
		super(idGailua, izena, "Bestelakoa", iraupenaP, kontsumoa);
		super.setIraupenaPertsonala(iraupenaP);
		super.irudia =  System.getProperty("user.home")+"/Development/Eclipse/VirtualMaid/VirtualMaid/src/main/webapp/EDUKIAK/IRUDIAK/electrodomesticos.jpg";
		programakBete();
	}

	@Override
	protected void programakBete() {
		programak.add("pertsonala");
		
	}

	@Override
	public void setIraupena(String programa) {
		switch (programa) {
			
		case "pertsonala":
			super.setIraupena(super.getIraupenaPertsonala());
		
		default:
			break;
		}
	}

}
