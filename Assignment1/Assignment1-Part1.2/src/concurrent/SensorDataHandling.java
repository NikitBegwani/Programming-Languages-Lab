package concurrent;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class SensorDataHandling {
	//queue contain binary_val raw sensor data
	@SuppressWarnings({"unchecked"})
	public static BlockingDeque<DataStructure1>[] SensorData = new LinkedBlockingDeque[10];

	//constructor
	public SensorDataHandling(){
		for(int i =0;i<10;i++){
			SensorData[i] = new LinkedBlockingDeque<DataStructure1>();
		}
	}
	
	public void sensor(int sensorid) throws InterruptedException{		
		//generating random 8-bit binary number
		Random rg = new Random();
		int n = rg.nextInt(256);
		String binary_val =  Integer.toBinaryString(n);
		DataStructure1 temp = new DataStructure1(binary_val,sensorid,true,true,true);
		//putting that data in sensor queue depending on sensor id
		SensorData[sensorid].put(temp);
	}
	//converter reading the data from sensor queue , converting it to integer and returning it
	public DataStructure2 converter(int sensorid) throws InterruptedException{
		

		DataStructure1 temp1;
		DataStructure2 temp2 = new DataStructure2(-1,sensorid,true,true,true);
			if(!SensorData[sensorid-10].isEmpty()){
				temp1 = SensorData[sensorid-10].take();
				temp2 = new DataStructure2(Integer.parseInt(temp1.getbinary_val(),2),sensorid,true,true,true);
				}
		return temp2;
	}

}
