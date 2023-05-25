package actividades;

public class ItemNotFound extends Exception {

	public ItemNotFound(String mensaje) {
		super(mensaje);
	}
	
	public ItemNotFound() {
		super();
	}
}
