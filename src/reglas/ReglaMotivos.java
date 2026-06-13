package reglas;

import modelo.PlanEstudio;
import modelo.ResultadoRegla;
import modelo.SolicitudCondicional;

public class ReglaMotivos implements ReglaAcademica{
	public ResultadoRegla evaluar(SolicitudCondicional soli, PlanEstudio plan) {
		boolean cumple=false;
		if(soli.getMotivo().equalsIgnoreCase("salud") || soli.getMotivo().equalsIgnoreCase("trabajo")) {
			cumple=true;
		}

		return new ResultadoRegla(cumple, "Motivo: "+soli.getMotivo());
	}
}
