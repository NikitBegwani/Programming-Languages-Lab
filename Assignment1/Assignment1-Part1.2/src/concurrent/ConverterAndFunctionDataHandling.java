package concurrent;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ConverterAndFunctionDataHandling {
	// blocking queue(thread safe) containing converted data
	@SuppressWarnings("unchecked")
	private static BlockingDeque<DataStructure2>[] SensorProcessedData = new LinkedBlockingDeque[10];
	
	//class constructor
	public ConverterAndFunctionDataHandling(){
		for(int i =0;i<10;i++){	
			SensorProcessedData[i] = new LinkedBlockingDeque<DataStructure2>();	
		}
	}
	// converter puts the converted data in queue
	public void converter(DataStructure2 temp2,int sensorid) throws InterruptedException{		
		SensorProcessedData[sensorid-10].put(temp2);
	}
	// avg is calculated if data is ready from all the sensors else we ignore and put the thread to sleep
	
	public void avg() throws InterruptedException{
			
		int total = 0;	// contains sum of all values
		int avg_val = 0;	// contains the average value
		int count = 0;	// counts whether data from all the sensors has been read or not
		
		for(int i =0;i<10;i++){
			if(!SensorProcessedData[i].isEmpty()){
				DataStructure2 temp;
				temp = SensorProcessedData[i].peekFirst(); // peeking at top value of queue
				if(temp.getavg() == true){			//checking if we have already read this data for avg or not
					total = total + temp.getint_val();
					count++;
				}
			}
		}
		
		if(count == 10){	// if all the sensors has been read
			for(int i = 0; i<10;i++){
		
				if(!SensorProcessedData[i].isEmpty()){
					DataStructure2 temp;
					// we remove the top element, change its avg boolean value to represent that its read
					temp = SensorProcessedData[i].take();
					if(temp.getavg() == true){
						temp.setavg(false);
						SensorProcessedData[i].putFirst(temp);
					}
					// if the top queue value is read by all the function, we remove it from further processing
					if(temp.getavg() == false && temp.getmul() == false && temp.getadd() == false){
						SensorProcessedData[i].takeFirst();
					}
				}
			}
			
			avg_val = total/10;
			//comparing avg value with threshold
			if(avg_val >= 100){
				System.out.println("State detected from AVG: " + avg_val);
			}
			else{
				System.out.println("State not detected from AVG " + avg_val);
			}
		}
	}

	//mul is calculated if data is ready from all the sensors else we ignore and put the thread to sleep
	public void mul() throws InterruptedException{
		
		int mul = 1; // contains the multiplied data
		int count = 0; //counts if data from all the sensors has been read or not
		
		for(int i =0;i<10;i++){
			if(!SensorProcessedData[i].isEmpty()){
				DataStructure2 temp;
				temp = SensorProcessedData[i].peekFirst(); // peeking at top value of queue
				if(temp.getmul() == true){			//checking if we have already read this data for mul or not
					// if threshold is already crossed, we don't need to multiply this improves the performance
					if(mul<100000 || temp.getint_val() ==0)
						mul = mul*temp.getint_val();
					count++;
				}
			}
		}
		
		// if data from all sensors has been read
		if(count == 10){
			for(int i = 0; i<10;i++){
				if(!SensorProcessedData[i].isEmpty()){
					// we remove the top element, change its mul boolean value to represent that its read
					DataStructure2 temp;
					temp = SensorProcessedData[i].take();
					if(temp.getmul() == true){
						temp.setmul(false);
						SensorProcessedData[i].putFirst(temp);
					}
					//if the top queue value is read by all the function, we remove it from further processing
					if(temp.getavg() == false && temp.getmul() == false && temp.getadd() == false){
						SensorProcessedData[i].takeFirst();
					}
					
				}
			}
			//check threshold
			if(mul >= 100000){
				System.out.println("State detected from MUL: " + mul);
			}
			else{
				System.out.println("State not detected from MUL: " + mul);
			}
		}
		
	
		
	}
	
	//add is calculated if data is ready from all the sensors else we ignore and put the thread to sleep
	public void add() throws InterruptedException{

		int total = 0;	// contains sum of all values
		int count = 0;	// counts whether data from all the sensors has been read or not
	
		for(int i =0;i<10;i++){
			if(!SensorProcessedData[i].isEmpty()){
				DataStructure2 temp;
				temp = SensorProcessedData[i].peekFirst();	// peeking at top value of queue
				if(temp.getadd() == true){			//checking if we have already read this data for avg or not
					total = total + temp.getint_val();
					count++;
				}
				
			}
			
		}
		if(count == 10){	// if all the sensors has been read
			for(int i = 0; i<10;i++){
				// we remove the top element, change its add boolean value to represent that its read
					if(!SensorProcessedData[i].isEmpty()){
						DataStructure2 temp;
						temp = SensorProcessedData[i].take();
						if(temp.getavg() == true){
							temp.setadd(false);
							SensorProcessedData[i].putFirst(temp);
						}
						//if the top queue value is read by all the function, we remove it from further processing
						if(temp.getavg() == false && temp.getmul() == false && temp.getadd() == false){
							SensorProcessedData[i].takeFirst();
						}
						
					}
				
			}
			//check threshold
			if(total >= 10000){
				System.out.println("State detected from ADD: " + total);
			}
			else{
				System.out.println("State not detected from ADD: " + total);
			}
		}
		
}
}
