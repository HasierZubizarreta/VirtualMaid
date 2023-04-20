package dl;

public class PrezioaJB {
	
	private int ordua;
	private float prezioa;	
	private String kolorea;
	
	public PrezioaJB() {
	}
	
	public PrezioaJB(int ordua, float prezioa, String kolorea) {
		super();
		this.ordua = ordua;
		this.prezioa = prezioa;
		this.kolorea = kolorea;
	}

	public int getOrdua() {
		return ordua;
	}

	public float getPrezioa() {
		return prezioa;
	}

	public String getKolorea() {
		return kolorea;
	}
	
	public String getInformazioa() {
		
		String izena;
		
        	if(ordua<10){
            	izena="0"+ordua+":00 - " + prezioa +"€";
            }
        	
        	else{
        		izena=ordua+":00 - " + prezioa +"€";
        	}

		return izena;
	}

	public String toString() {
		return "PrezioaJB [ordua=" + ordua + ", prezioa=" + prezioa + ", kolorea=" + kolorea + "]";
	}
	
	public String toCSV() {
		return "" + ordua + ";" +prezioa+ ";" + kolorea;
	}
	
}
