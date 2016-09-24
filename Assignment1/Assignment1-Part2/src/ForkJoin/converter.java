package ForkJoin;

import java.util.concurrent.locks.Lock;

public class converter  implements Runnable{
	private int id;
	SensorDataHandling DataHandler1;
	Lock lock_data_sensor;
	ConverterAndFunctionDataHandling DataHandler2;
	Lock lock_data_converted;
	
	//class constructor
	public converter(Lock lock_data_sensor,Lock lock_data_converted,SensorDataHandling DataHandler1,ConverterAndFunctionDataHandling DataHandler2,int id){
		this.id = id;
		this.DataHandler1 = DataHandler1;
		this.lock_data_sensor = lock_data_sensor;
		this.DataHandler2 = DataHandler2;
		this.lock_data_converted = lock_data_converted;
	}
	@Override
	public void run(){
		
		while(true){
			// reading data from sensor queue
			lock_data_sensor.lock();
			DataStructure2 temp2 = new DataStructure2(-1,id,true,true,true);
				try {
					temp2 = DataHandler1.converter(id);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			lock_data_sensor.unlock();
			// writing data to converted queue
			lock_data_converted.lock();
				try {
					if(temp2.getint_val() != -1)
						DataHandler2.converter(temp2,id);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			lock_data_converted.unlock();
			try {
				
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
				
	}
}

