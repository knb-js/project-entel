package app.project.entel.vo;

import java.io.Serializable;


public class MessageVO implements Serializable {
	
	
private static final long serialVersionUID = -4386829749075232981L;
	
	private String fecha;
	private String mensaje;
	

	public MessageVO(){
			
	}
	
	public MessageVO(String fecha, String mensaje){
		this.setFecha(fecha);
		this.setMensaje(mensaje);
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fechaRsp) {
		this.fecha = fechaRsp;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	@Override
	public String toString() {
		return "MensajeVO [fecha=" + fecha +  ", mensaje=" + mensaje + "]";
	}

}
