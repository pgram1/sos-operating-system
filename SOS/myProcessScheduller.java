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
            if ((((SOSProcess)this.queue.get(i)).getCodeSize() - ((SOSProcess)this.queue.get(i)).getCounter())< (p.getCodeSize() - p.getCounter())) {
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