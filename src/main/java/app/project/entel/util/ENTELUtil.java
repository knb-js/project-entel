package app.project.entel.util;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;

import java.text.SimpleDateFormat;
import java.util.Date;

@Log4j2
public class ENTELUtil {

    @Scope("singleton")
    public static class Codigos {
        static Codigos instance;

        //OK
        public static final String INFOOK = "000;OK-000 - INFOOK - Información obtenida correctamente";
        public static final String ADDOOK = "000;OK-000 - ADDOOK - Usuario agregado correctamente";

        // ERRORES
        public static final String NOTDATAFOUND= "001;ERR-001 - NOTDATAFOUND - No se encontraron datos";
        public static final String INVALIDPARAMETERS = "002;ERR-002 - PARAMETROSINVALIDOS - parametros inválidos";
        public static final String UNEXPECTEDERROR = "003;ERR-003 - UNEXPECTEDERROR - Ha ocurrido un error inesperado, revise el log";

        public static final String ERRORUSER = "004;ERR-004 - ERRORUSER - No se ha podido ingresar el usuario, datos incorrectos";

        public static Codigos getInstance() {
            if (instance == null) {
                instance = new Codigos();
            }
            return instance;
        }

        public Codigos() {

        }

    }
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy;HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
