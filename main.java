
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;


public class main {

	static int processCounter= 1;
	
	static int cpuRunning = 1;
	
	static ArrayDeque<Integer> ready = new ArrayDeque<Integer>();  
	static ArrayList<Integer> waiting = new ArrayList<Integer>();   
	static ArrayList<device> deviceArray = new ArrayList<device>();
	
	
	//timer Interrupt method 15
	public static void timerInterrupt() {
		ready.add(cpuRunning);
		cpuRunning = ready.remove();
	}
	
	//Create a process method 80
	public static void createProcess() {
		processCounter++;
		if(cpuRunning == processCounter){
			cpuRunning = processCounter;
		}else{
			ready.add(processCounter);
			System.out.println("Creating process "+ processCounter);
		}
	}
	
	//Request I/O method 70
	public static void requestIo(int deviceNum){
		//the deviceNum is the device number associated with its position in the device Array.
		deviceArray.get(deviceNum-1).addRunning(cpuRunning);
		waiting.add(cpuRunning); //may need to take out
		cpuRunning = ready.remove();
		System.out.println("requeting I/O on device  "+ deviceNum);
	}
	
	//Completion of I/O method 71
	public static void completionIo(int deviceNum){
		//the deviceNum is the device number associated with its position in the device Array.
		ready.add(deviceArray.get(deviceNum-1).get());
		deviceArray.get(deviceNum-1).removeRunning();
		cpuRunning = ready.remove();
		System.out.println("completed I/O on device  "+ deviceNum);
	}
	
	//Wait system call method 64
	public static void waitSysCall(){
		waiting.add(cpuRunning);
		if(ready.isEmpty()==false){
		cpuRunning = ready.remove();
		}
	}
	
	//Signal waiting method 65
	public static void signalWaiting(int processId){
	
		if(waiting.contains(processId) ==true){
			//add to ready queue. may need iterator 
			for(int i = 0;i<waiting.size();i++){
				if(waiting.get(i) == processId){
					ready.add(waiting.get(i));
					waiting.remove(i);
				}
			}
		}else{
			System.out.println("process is not in the waiting queue.");
		}
		
	}
	
//	Graceful shut down method 244
	public static void graceful(){
		ready.clear();
		
		Iterator<device> iterRR = deviceArray.iterator();
		while(iterRR.hasNext()) {
	         iterRR.next().deviceShutDown();
		}
		
		deviceArray.clear();

		cpuRunning = 1;
		
		System.exit(0);
	}
	
	//Status display method 255
	public static void status(){

		System.out.println("Program Satus");
		System.out.println("CPU running: "+cpuRunning);
		System.out.println("Ready queue");
		Iterator<Integer> iter = ready.iterator();
		while(iter.hasNext()) {
	         int element = iter.next();
	         System.out.print(element+" ");
	      }
		
		System.out.println();
		System.out.println();
		
		System.out.println("Waiting queue");
		Iterator<Integer> iterw = waiting.iterator();
		while(iterw.hasNext()) {
	         int element = iterw.next();
	         System.out.print(element+" ");
	      }
		
		System.out.println();
		System.out.println();
		
		Iterator<device> iter1 = deviceArray.iterator();
		int i = 0;
		while(iter1.hasNext()) {
			System.out.println("These are the processes for device " + (i+1));
			iter1.next().deviceStatus();
			i++;
			System.out.println();
	      }
	}
	
	//Terminates process 81
	public static void terminateProcess(){
		cpuRunning = ready.remove();
	}
	
	//Ungraceful shutdown
	public static void ungraceful(){
		System.exit(0);
	}
	
	

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		//Asks for number of devices
		System.out.print("How many I/O devices will you need?");
		int deviceNum= sc.nextInt();
		
		//creates multiple arrays based on user input.
		for(int i =0;i<deviceNum;i++){
			deviceArray.add(new device(i));
			System.out.println("this is device " + (i+1));
		}
		

		//switch statement that is associated with each function.
		while(1<2){
		     System.out.print("enter a interrupt number");
		     int temp = sc.nextInt();
		     
		     switch(temp) {
		     case 01 :
			        ungraceful();
			        break;
		     
		     case 15 :
		        timerInterrupt();
		        break; 
		     
		     case 64 :
		    	waitSysCall();
		        break; 
		        
		     case 65 :
		    	 System.out.println("Enter process number.");
		    	 int deviceNum1 = sc.nextInt();
		    	 int processId = deviceNum1;
		    	 signalWaiting(processId);
			     break; 
		     
		     case 70 :
		    	 System.out.println("Enter device number.");
		    	 int deviceNum2 = sc.nextInt();
		    	 deviceNum = deviceNum2;
		    	 requestIo(deviceNum);
		    	 	
		    	 break; 
			        
		     case 71 :
		    	 System.out.println("Enter device number.");
		    	 int deviceNum3 = sc.nextInt();
		    	 deviceNum = deviceNum3;
		    	 completionIo(deviceNum);
		    	 break; 
			        
		     case 80 :
			     createProcess();
			     break; 
			        
		     case 81 :
		    	 terminateProcess();
			     break; 
			        
		     case 244 :
		    	 graceful();
			     break; 
			        
		     case 255 :
			     status();
			     break; 
			       
		     default : // Optional
		        // Statements
		  }
		}
	}
}