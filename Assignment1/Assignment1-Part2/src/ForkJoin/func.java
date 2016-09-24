package ForkJoin;

import java.util.concurrent.locks.Lock;

public class func implements Runnable{
	private int id;
	ConverterAndFunctionDataHandling DataHandler2;
	Lock lock_data_converted;
	//constructor
	public func(Lock lock_data_converted,ConverterAndFunctionDataHandling DataHandler2,int id){
		this.id = id;
		this.DataHandler2 = DataHandler2;
		this.lock_data_converted = lock_data_converted;
	}
	@Override
	public void run(){
		// id 20 for avg id 21 for mul and id 22 for add
		while(true){
			if(id == 20){
				lock_data_converted.lock();
					try {
						DataHandler2.avg();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lock_data_converted.unlock();
			}
			if(id == 21){
				lock_data_converted.lock();
					try {
						DataHandler2.mul();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lock_data_converted.unlock();
			}	
			if(id == 22){
				lock_data_converted.lock();
				try {
					DataHandler2.add();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lock_data_converted.unlock();
		}
			try {
				
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
				
	}

}
