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

}
