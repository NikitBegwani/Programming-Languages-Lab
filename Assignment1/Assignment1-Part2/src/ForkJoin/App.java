package ForkJoin;

// concurrent library's import
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
	
	public static void main(String[] args) throws InterruptedException{
		// main thread starts executing this
		
		//DataHandler1 object for Handling the queues in which sensors put the data
		SensorDataHandling DataHandler1 = new SensorDataHandling();
		
		//DataHandler2 object for Handling the queues in which converters put the data taken from sensor queues
		ConverterAndFunctionDataHandling DataHandler2 = new ConverterAndFunctionDataHandling();
		
		//Thread pools for 10sensors, 10converters, 3functions(avg, mul, add)
		ExecutorService sensors = Executors.newFixedThreadPool(10);
		ExecutorService converters = Executors.newFixedThreadPool(10);
		ExecutorService functions = Executors.newFixedThreadPool(3);
		
		// locks for acquiring lock on objects:-  DataHandler1, DataHandler2
		Lock lock_data_sensor = new ReentrantLock();
		Lock lock_data_converted = new ReentrantLock();
		
		for(int i=0;i<10;i++){
			//initializing sensor threads with Runnable method, giving lock, DataHandler and sensor id as parameters
			sensors.submit(new sensor(lock_data_sensor,DataHandler1,i));
		}
		
		for(int i=0;i<10;i++){
			// initializing converter threads with Runnable method, giving both locks, DataHandler1, DataHandler2 and converter id as parameters
			converters.submit(new converter(lock_data_sensor,lock_data_converted,DataHandler1,DataHandler2,i+10));
		}
		for(int i=0;i<3;i++){
			//initializing function(avg, mul, add) threads with Runnable method, giving lock, DataHandler2 and function id parameters
			functions.submit(new func(lock_data_converted,DataHandler2,i+20));
		}
	}

}
