import sossim.core.IProcessScheduler;

public class myProcessScheduller implements IProcessScheduler{
  public myProcessScheduller(){
  
    public void addProcess(SOSProcess p){
    
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
