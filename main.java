import java.util.Scanner;
import java.util.ArrayDeque;

public class main {

	static int processCounter= 1;
	static int cpuRunning = 1;
	static ArrayDeque<Integer> ready = new ArrayDeque<Integer>();
	static ArrayDeque<device> devices = new ArrayDeque<device>();
	
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
		}
	}
	
	//Request I/O method 70
	public static void requestIo(int deviceNum){
		//the deviceNum is the device number associated with its position in the device Array.
		
	}
	
	//Completion of I/O method 71
	public static void completionIo(int deviceNum){
		//the deviceNum is the device number associated with its position in the device Array.
		
	}
	
	//Wait system call method 64
	public static void waitSysCall(){
		
	}
	
	//Signal waiting method 65
	public static void signalWaiting(){
		
	}
	
	//Graceful shut down method 244
	public static void graceful(){
		
	}
	
	//Status display method
	public static void status(){
		
	}
	
	//Terminates process 81
	public static void terminateProcess(){
		
	}
	
	//Ungraceful shutdown
	public static void ungraceful(){
		System.exit(0);
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		ArrayDeque<device> deviceArray = new ArrayDeque<device>();
		
		//Asks for number of devices
		System.out.print("How many I/O devices will you need?");
		int deviceNum= sc.nextInt();
		
		//creates multiple arrays based on user input. Not yet finished!
		for(int i =0;i<deviceNum;i++){
			deviceArray.add(new device());
			System.out.println("this is device " + (i+1));
		}
		
		
		//asks for inturrpt codes
		System.out.print("enter a number");
		int temp= sc.nextInt();
		
		while(1<2){
		     System.out.print("enter a number");
		     temp = sc.nextInt();
		     
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
		    	 signalWaiting();
			     break; 
		     
		     case 70 :
		    	 requestIo(deviceNum);
		    	 break; 
			        
		     case 71 :
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
