package ThreadRunnableSynchronization;

public 	class sensor implements Runnable{
	
	private int id;
	SensorDataHandling DataHandler1;
	
	//constructor
	public sensor(SensorDataHandling DataHandler1 ,int id){
		this.id = id;
		this.DataHandler1 = DataHandler1;
	}
	
	public void run(){
		
		while(true){
			// putting sensor data in queue
			synchronized(DataHandler1){
				try {
					DataHandler1.sensor(id);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				//TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
				
	}
}

