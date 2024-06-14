
public class SynchronizedBuffer implements Buffer{
	private int buffer = -1;
	private boolean occupied = false;
	
	@Override
	public synchronized void blockingPut(int value) throws InterruptedException {
		while (occupied) {//si esta ocupado detiene el hilo que saca la info
			System.out.println("Producer tries to write");
			displayState("Buffer full. Producer waits");
			wait();//con este se detiene el hilo
		}
		buffer = value; //nuevo valor
		//ya no pongas mas valores hasta que el consumer lo use
		occupied = true;
		displayState("Producer writes " + buffer);
		//carrera de ratas por el monitor lock
		notifyAll();//Se ejecutan todos los hilos
		
	}
	@Override
	public synchronized int blockingGet() throws InterruptedException {
		while (!occupied) {//Si no tiene info que sacar se espera que el otro tenga su codigo
			System.out.println("Consumer tries to read");
			System.out.println("Buffer empty, consumer waits");
			wait();
		}
		//lee el buffer y lo desocupa
		occupied = false;
		displayState("Consumer reads " + buffer);
		//carrera de ratas por el monitor lock
		notifyAll();//hacemos jalar todos los hilos
		
		return buffer;
	}

	private void displayState(String operation) {
		System.out.printf("%-40s%d\t\t%b%n%n", operation, buffer, occupied);
	}
}
