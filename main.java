import java.util.Scanner;
import java.util.ArrayDeque;

public class main {

	static int processCounter= 1;
	static int cpuRunning = 1;
	static ArrayDeque<Integer> ready = new ArrayDeque<Integer>();
	static ArrayDeque<device> devices = new ArrayDeque<device>();
	
	
	public static void createProcess() {
		processCounter++;
		if(cpuRunning == processCounter){
			cpuRunning = processCounter;
		}else{
			ready.add(processCounter);
		}
	}
	
	public static void timerInterupt() {
		ready.add(cpuRunning);
		cpuRunning = ready.remove();
	}
	
	public static void requestIo(int deviceNum){
		//the deviceNum is the device number associated with its position in the device Array.
		
	}
	
	public static void completionIo(int deviceNum){
		//the deviceNum is the device number associated with its position in the device Array.
		
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
			//deviceArray.add(devices = new device());
		}
		
		
		//asks for inturrpt codes
		System.out.print("enter a number");
		int temp= sc.nextInt();
		
		while(temp!=1){
		     System.out.print("enter a number");
		     temp = sc.nextInt();
		}
		
	}

}
