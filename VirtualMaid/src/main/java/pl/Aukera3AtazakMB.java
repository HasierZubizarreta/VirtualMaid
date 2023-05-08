package pl;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class Aukera3AtazakMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int labOrrModua;
	private int denbora=0, orain=0;
	
	public int getLabOrrModua() {
		return labOrrModua;
	}

	/**
	 * Laburpenen orrialdeko datuak garbitu eta orrialdea 1 modura jartzeko (formularioa eta taula bistaratzeko)
	 * @param aLViewMB  Administrarien laburpenen orrialdearentzat prestatutako datuak dituen MB
	 */
	public void labOrrModuaAldatu(Aukera3ViewMB a3ViewMB) {	
		//a3ViewMB.resetView();
		labOrrModua=1; //1: Laburpenen orrialdean festarako formularioa + dagokion taula bistaratu
		a3ViewMB.resetView();
	}
	public void resetView() throws IOException {
		
		labOrrModua=0;
		FacesContext.getCurrentInstance().getExternalContext().redirect("../../");
		
	}
	
	public void aldatuAurtengoGastuetara(Aukera3ViewMB a3ViewMB) {
		
		denbora=0; orain=0;
		a3ViewMB.resetView();
	}
	public void aldatuAurrekoUrtekoGastuetara(Aukera3ViewMB a3ViewMB) {
		
		denbora=0; orain=1;
		a3ViewMB.resetView();
	}
	public void aldatuAstekoGastuetara(Aukera3ViewMB a3ViewMB) {
		
		denbora=1; orain=0;
		a3ViewMB.resetView();
	}
	public void aldatuAurrekoAstekoGastuetara(Aukera3ViewMB a3ViewMB) {
		
		denbora=1; orain=1;
		a3ViewMB.resetView();
	}

	public int getDenbora() {
		return denbora;
	}

	public int getOrain() {
		return orain;
	}
}
