import sossim.core.SOS;
import sossim.core.IProcessScheduler;
import java.util.ArrayList;


public class myProcessScheduller implements IProcessScheduler {
    public myProcessScheduller() {

        private ArrayList<SOSProcess> queue;

        public myProcessScheduller() {
            this.queue = new ArrayList<SOSProcess>();
        }

        public void addProcess(SOSProcess p) {
            this.queue.add(p);
        }

        public SOSProcess selectProcessToRun() {
            return p;
        }

        public boolean preempt() {
            return false;
        }

        public void removeProcess(SOSProcess p) {
            this.queue.remove(p);
        }

        public String toString() {
            return "Fancypants";
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
}