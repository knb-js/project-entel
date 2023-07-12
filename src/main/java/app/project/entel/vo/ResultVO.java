package app.project.entel.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(Include.NON_NULL)
public class ResultVO implements Serializable {

	private static final long serialVersionUID = 5239785946322403062L;

	private MessageVO peticion;
	private Object object;

	public void setPeticion(String fecha, String mensaje) {
		MessageVO peticion = new MessageVO(fecha, mensaje);
		this.peticion = peticion;
	}

}
