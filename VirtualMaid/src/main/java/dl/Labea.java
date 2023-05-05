package dl;

public class Labea extends GailuaJB{

	
	private static final long serialVersionUID = 1L;
	
	//float tenperatura.   auxf aldagaia erabiliko du tenperatura ezartzeko
	
	public Labea() {
		super();
	}
	
	
	public Labea(int idGailua, String izena, int iraupenaP, float kontsumoa) {
		super(idGailua, izena, "Labea", iraupenaP, kontsumoa);
		super.setIraupenaPertsonala(iraupenaP);
		super.irudia =  System.getProperty("user.home")+"/Development/Eclipse/VirtualMaid/VirtualMaid/src/main/webapp/EDUKIAK/IRUDIAK/electrodomesticos.jpg";
		programakBete();
	}
	
	@Override
	protected void programakBete() {
		programak.add("grill");
		programak.add("oilaskoa");
		programak.add("Arraina");
		programak.add("pizza");
	}

	@Override
	public void setIraupena(String programa) {
		switch (programa) {
		case "grill":
			super.setIraupena(30);
			break;
		
		case "oilaskoa":
			super.setIraupena(50);
			break;
		
		case "arraina":
			super.setIraupena(35);
			break;
		
		case "pizza":
			super.setIraupena(17);
			break;
			
		case "pertsonala":
			super.setIraupena(super.getIraupenaPertsonala());
		
		default:
			break;
		}
	}

}
