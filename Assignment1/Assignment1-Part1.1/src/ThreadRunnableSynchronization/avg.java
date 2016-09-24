package ThreadRunnableSynchronization;

public class avg  implements Runnable{

	// data object for handling second set of queues
	ConverterandFunctionDataHandling DataHandler2;

	//class constructor
	public avg(ConverterandFunctionDataHandling DataHandler2){
		this.DataHandler2 = DataHandler2;	//initializing parameter
	}
	
	@Override
	//Runnable for thread
	public void run(){
		
		while(true){
			// synchronized over dataobject required for handling second set of queues containing converted data from each sensor
			synchronized(DataHandler2){
				try {
					DataHandler2.avg();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
