package ThreadRunnableSynchronization;

import java.util.LinkedList;
import java.util.Random;

public class SensorDataHandling {

	//queue contain binary_val raw sensor data
	@SuppressWarnings({"unchecked"})
	private static LinkedList<DataStructure1>[] SensorData = new LinkedList[10];
	
	private static final int NUM_LOCKS = 10;
	private static Object[] lockSensorDataArray = new Object[NUM_LOCKS];
	
	//constructor
	public SensorDataHandling(){
		for(int i =0;i<10;i++){
			SensorData[i] = new LinkedList<DataStructure1>();
			lockSensorDataArray[i] = new Object();

		}
	}
	
	public void sensor(int sensorid) throws InterruptedException{		 
		//generating random 8-bit binary number
		Random rg = new Random();
		int n = rg.nextInt(256);
		String binary_val =  Integer.toBinaryString(n);
		//putting that data in sensor queue depending on sensor id
		synchronized (lockSensorDataArray[sensorid]){
			DataStructure1 temp = new DataStructure1(binary_val,sensorid,true,true,true);
			SensorData[sensorid].add(temp);
		}
	}
	//converter reading the data from sensor queue , converting it to integer and returning it
	public DataStructure2 converter(int sensorid) throws InterruptedException{
		DataStructure1 temp1;
		DataStructure2 temp2 = new DataStructure2(-1,sensorid,true,true,true);
		synchronized (lockSensorDataArray[sensorid-10]){
			if(!SensorData[sensorid-10].isEmpty()){
				temp1 = SensorData[sensorid-10].remove();
				temp2 = new DataStructure2(Integer.parseInt(temp1.getbinary_val(),2),sensorid,true,true,true);			
			}
		}
		return temp2;
	}
}
	

