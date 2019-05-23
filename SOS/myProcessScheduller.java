import sossim.core.IProcessScheduler;

public class myProcessScheduller implements IProcessScheduler{
  public myProcessScheduller(){
    
    private ArrayList<SOSProcess> processQueue;
    
    public void myProcessScheduller(){
		  processQueue = new ArrayList<SOSProcess>();
    }
  
    public void addProcess(SOSProcess p){
     processQueue.add(p);
    }
    
    public SOSProcess selectProcessToRun(){
    return p;
    }
    
    public boolean preempt(){
    return false;
    }
    
    public void removeProcess(SOSProcess p){
    
    }
    
    public String toString(){
    return "Fancypants";
    }
    
    public int getNumberOfProcessesInQueue(){
    return 1;
    }
    
    public double getProgresssOfProcessInQueue(int i){
    return 1.0;
    }
    
    public SOSProcess getProcessAtQueue(int i){
    return p;
    }
    
  }
}
