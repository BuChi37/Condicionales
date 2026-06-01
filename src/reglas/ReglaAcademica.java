package reglas;
import modelo.*;

public interface ReglaAcademica {
	public ResultadoRegla evaluar(SolicitudCondicional solic, PlanEstudio plan);
}
