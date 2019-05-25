import sossim.core.SOSProcess;
import sossim.core.IProcessScheduler;
import java.util.ArrayList;


public class myProcessScheduller implements IProcessScheduler {

    private ArrayList<SOSProcess> queue;

    public myProcessScheduller() {
        this.queue = new ArrayList<SOSProcess>();
    }

    public void addProcess(SOSProcess p) {
        this.queue.add(p);
    }

    public SOSProcess selectProcessToRun() {
        if (this.queue.isEmpty()) {
            return null;
        }
        if (this.queue.size() == 1) {
            return (SOSProcess) this.queue.get(0);
        }
        SOSProcess p = (SOSProcess)this.queue.get(0);
        for (int i = 1; i < this.queue.size(); i++) {
            /*
            if the remaining codesize of where this process is
            is indeed smaller than the remaining codesize of the compared process,
            make this process the new comparable,
            so we end up with the smallest remaining codesize process,
            thus the one with the smallest remaining time

            #Future Work
            optimizable: we could keep the shortest ones sorted in a queue
            so we don't have the overhead of looking over all the processes again
            wasting a bit of JVM ram to keep the extra queue

            We should take into account the assignment overhead, so
            adding to the secondary queue and selection of processes should be done in 2 separate queues

            Worst case scenario is the fact of randomness of when the processes wish to block,
            which is part of their codesize after all. In that case, the provided solution here
            could be better suited.

            Except if we somehow kept track of how many times processes wanna block,
            which then gives us a serious JVM ram waste and a big overhead for all the checks,
            which a fast CPU would go through fast anyways. This would definitely not be a
            slow-CPU-friendly-simulation-ram-conserving approach.
            */
            if ((((SOSProcess)this.queue.get(i)).getCodeSize() - ((SOSProcess)this.queue.get(i)).getCounter()) < (p.getCodeSize() - p.getCounter())) {
            p = (SOSProcess)this.queue.get(i);
            }
        } 
        return p;
    }

    public boolean preempt() {
        return false;
    }

    public void removeProcess(SOSProcess p) {
        this.queue.remove(p);
    }

    public String toString() {
        return "Shortest Remaining Time Next";
    }

    public int getNumberOfProcessesInQueue() {
        return this.queue.size();
    }

    public double getProgresssOfProcessInQueue(int i) {
        return ((SOSProcess) this.queue.get(i)).getProgress();
    }

    public SOSProcess getProcessAtQueue(int i) {
        return (SOSProcess) this.queue.get(i);
    }
}