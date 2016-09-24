package ThreadRunnableSynchronization;

public class App {
	
	public static void main(String[] args) throws InterruptedException{		
		//main thread starts processing
		
		//DataHandler1 object for Handling the queues in which sensors put the data
		SensorDataHandling DataHandler1 = new SensorDataHandling();
		//DataHandler2 object for Handling the queues in which converters put the data taken from sensor queues
		ConverterandFunctionDataHandling DataHandler2 = new ConverterandFunctionDataHandling();
		
		Thread sensors[] = new Thread[10];	// 10 Threads for 10 sensors
		Thread converters[] = new Thread[10];	// 10 Threads for 10 converters
		Thread functions[] = new Thread[3];	// 3 Threads for functions one each for avg, add, mul	
	
		for(int i=0;i<10;i++){
			// initializing sensor threads with Runnable method, giving DataHandler and sensorid as parameters
			sensors[i] =new Thread(new sensor(DataHandler1,i));
		}
		 
		for(int i=0;i<10;i++){
			// sensors begin generating data
			sensors[i].start();
		}
		
		for(int i=0;i<10;i++){
			// initializing converter threads with Runnable method, giving DataHandler1, DataHandler2 and converter id as parameters
			converters[i] = new Thread(new converter(DataHandler1,DataHandler2,i+10));
		}
		
		for(int i=0;i<10;i++){
			//converters  begin converting data
			converters[i].start();
		}
		
		// initializing function threads with Runnable method, giving DataHandler2 parameters
		functions[0] = new Thread(new avg(DataHandler2));
		functions[1] = new Thread(new mul(DataHandler2));
		functions[2] = new Thread(new add(DataHandler2));
		
		for(int i=0;i<3;i++){
			//function threads begin their execution
			functions[i].start();
		}
	}

}
