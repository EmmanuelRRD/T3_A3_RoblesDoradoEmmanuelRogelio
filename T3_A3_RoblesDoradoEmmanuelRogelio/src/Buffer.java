
public interface Buffer {//Esta cosa sirve como un buffer principal
	
	public void blockingPut(int value) throws InterruptedException;//los metodos listos para agregarles godigo
	
	public int blockingGet() throws InterruptedException;//InterruptedException = modificar hilos
}
