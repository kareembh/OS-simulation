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
	
	public static void processInterupt() {
		ready.add(cpuRunning);
		cpuRunning = ready.remove();
	}
	

	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		//asks for inturrpt codes
		Scanner sc = new Scanner(System.in);
		System.out.print("enter a number");
		int temp= sc.nextInt();
		
		while(temp!=1){
		     System.out.print("enter a number");
		     temp = sc.nextInt();
		}
		
	}

}
