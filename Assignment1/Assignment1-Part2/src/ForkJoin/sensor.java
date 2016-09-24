package ForkJoin;

import java.util.concurrent.locks.Lock;

public 	class sensor implements Runnable{
	private int id;
	SensorDataHandling DataHandler1;
	Lock lock_data_sensor;
	
	//constructor
	public sensor(Lock lock_data_sensor, SensorDataHandling DataHandler1 ,int id){
		this.id = id;
		this.DataHandler1 = DataHandler1;
		this.lock_data_sensor = lock_data_sensor;
	}
	
	public void run(){
		
		while(true){
			// putting sensor data in queue
			lock_data_sensor.lock();
				try {
					DataHandler1.sensor(id);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			lock_data_sensor.unlock();
		
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
				
	}
}

