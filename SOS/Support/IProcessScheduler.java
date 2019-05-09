package sossim.core;

public interface IProcessScheduler{

  //Called by the kernel, lets the scheduler know that a new process p
  //has to be admitted at the queue
  public void addProcess(SOSProcess p);

  //Called by the kernel, asks the scheduler to select a process from the ready queue that should run, and return it
  public SOSProcess selectProcessToRun();

  //Called by the kernel at every clock tick, asks if the process at the CPU must be pre-empted at this clock tick
  public boolean preempt();

  //Called by the kernel, lets the scheduler know that process p needs to be
  //removed from the queue  (the process is either new or it blocked)
  public void removeProcess(SOSProcess p);

  public String toString();

  //returns the number of processes in the ready queue
  public int getNumberOfProcessesInQueue();

  //returns the progress of the process in the queue as a double with range from 0 to 100
  public double getProgresssOfProcessInQueue(int i);

  //returns the ith process at the queue
  public SOSProcess getProcessAtQueue(int i);
}