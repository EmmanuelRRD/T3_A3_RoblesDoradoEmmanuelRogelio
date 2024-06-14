import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BlockingBufferTest {//no se que se hace aqui despues pido ayuda a chat

	public static void main(String[] args) throws InterruptedException{
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Buffer sharedLocation = new BlockingBuffer();
		
		executorService.execute(new Producer(sharedLocation));
		executorService.execute(new Consumer(sharedLocation));
		
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.MINUTES);
	}

}
