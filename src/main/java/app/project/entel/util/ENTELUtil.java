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
        public static final String INFOOK = "MensajeOK ;Información obtenida correctamente ✔️";
        public static final String ADDOOK = "MensajeOK ;Usuario agregado correctamente ✔️";

        // ERRORES
        public static final String NOTDATAFOUND= "MensajeERROR ;No se encontraron datos ❌";
        public static final String INVALIDPARAMETERS = "MensajeERROR ;parametros inválidos ❌";
        public static final String UNEXPECTEDERROR = "MensajeERROR ;Ha ocurrido un error inesperado, revise el log ❌";

        public static final String ERRORUSER = "MensajeERROR ;No se ha podido ingresar el usuario, datos incorrectos ❌";

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
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd;");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        return strDate;
    }
}
