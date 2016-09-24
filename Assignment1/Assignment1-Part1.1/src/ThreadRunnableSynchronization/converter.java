package ThreadRunnableSynchronization;

public class converter  implements Runnable{
	
	private int id;
	SensorDataHandling DataHandler1;
	ConverterandFunctionDataHandling DataHandler2;
	
	//class constructor
	public converter(SensorDataHandling DataHandler1,ConverterandFunctionDataHandling DataHandler2,int id){
		this.id = id;
		this.DataHandler1 = DataHandler1;
		this.DataHandler2 = DataHandler2;
	}
	@Override
	public void run(){
		
		while(true){
			DataStructure2 temp = new DataStructure2(-1,id,true,true,true);
			//getting data from sensor and converting it
			synchronized(DataHandler1){
				try {
					temp = DataHandler1.converter(id);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//putting the converted data into second set of queues
			synchronized(DataHandler2){
				try {
					if(temp.getint_val() != -1)
						DataHandler2.converter(temp,id);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			try {
				
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
				
	}
}

