import java.util.ArrayList;
import java.util.Iterator;

public class device {

	private int deviceNumber;
	public int deviceRunning=0;
	ArrayList<Integer> dReady;
	device(){
		dReady = new ArrayList<Integer>();
	}
	
	device(int i){
		dReady = new ArrayList<Integer>();
		deviceNumber = i;
	}
	
	
	 public  void addRunning(int cpu){
		 if(deviceRunning==0){
			 deviceRunning = cpu;
		 }else{
			 dReady.add(cpu);
		 }
	 }
	
	
	public int get(){
		return deviceRunning;
	}
	
	
	public void removeRunning(){
		deviceRunning=dReady.remove(0);
	}
	
	
	public void deviceStatus(){
		if(deviceRunning == 0){
			System.out.println("Device running: No process running");
		}else{
			System.out.println("Device running: "+deviceRunning);
		}
		Iterator<Integer> iter3 = dReady.iterator();
		System.out.print("Device processes: ");
			
			while(iter3.hasNext()) {
		         int element = iter3.next();
		         System.out.print(element+" ");
		     }
		
		System.out.println();
	}
	
	public void deviceShutDown(){
		dReady.clear();
	}
}