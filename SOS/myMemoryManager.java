import sossim.core.SOS;
import sossim.core.IMemoryManager;
import java.util.ArrayList;

/*
  ________
< AAAAAAAA >
  --------
         \   ^__^ 
          \  (oo)\_______
             (__)\       )\/\
                 ||----w |
                 ||     ||
    
*/

public class myMemoryManager implements IMemoryManager {

    private ArrayList <MemoryPartition> ram;
    private int ramSize;

    public myMemoryManager(int size) {
        this.ramSize = size;
        this.ram = new ArrayList<MemoryPartition>();
        this.ram.add(new MemoryPartition(false, this.ramSize));
    }

    public int addProcess(int size) {
        int position = findAvailablePartition(size);
        if (position == -1) {
            return -1;
        }
        if (((MemoryPartition) this.ram.get(position)).getSize() > size) {
            int pSize = ((MemoryPartition) this.ram.get(position)).getSize() - size;
            int pBa = ((MemoryPartition) this.ram.get(position)).getBaseAddress() + size;
            this.ram.add(position + 1, new MemoryPartition(pBa, pSize));
            ((MemoryPartition) this.ram.get(position)).setSize(size);
            ((MemoryPartition) this.ram.get(position)).occupy();
        } else {
            ((MemoryPartition) this.ram.get(position)).occupy();
        }
        return ((MemoryPartition) this.ram.get(position)).getBaseAddress();
    }

    public void deleteProcessAtAddress(int address) {
        int index = findPartitionIndex(address);
        deleteProcessAtIndex(index);
    }

    public void compact(SOS os) {
        for (int i = 0; i < this.ram.size() - 1; i++) {
            if (((MemoryPartition) this.ram.get(i)).isFree()) {
                int ps1AOld = ((MemoryPartition) this.ram.get(i)).getBaseAddress();
                int ps2AOld = ((MemoryPartition) this.ram.get(i + 1)).getBaseAddress();
                int ps1SOld = ((MemoryPartition) this.ram.get(i)).getSize();
                int ps2SOld = ((MemoryPartition) this.ram.get(i + 1)).getSize();
                deleteProcessAtIndex(i + 1);
                addProcessAt(ps2SOld, i);
                os.informPT(ps2AOld, ((MemoryPartition) this.ram.get(i)).getBaseAddress());
            }
        }
    }

    //done
    public int getNumberOfPartitionsInMemory() {
        return this.ram.size();
    }

    //done
    public int getSizeOfPartitionInMemory(int i) {
        return ((MemoryPartition) this.ram.get(i)).getSize();
    }

    //done
    public int getAddressOfPartitionInMemory(int i) {
        return ((MemoryPartition) this.ram.get(i)).getBaseAddress();
    }

    //done
    public float calcFragmentation() {
        float free = 0.0;
        float freemax = 0.0;
        float hunnid = 100.0;
        for (int i = 0; i < this.ram.size(); i++) {
            if (((MemoryPartition) this.ram.get(i)).isFree()) {
                free += ((MemoryPartition) this.ram.get(i)).getSize();
                if (freemax < ((MemoryPartition) this.ram.get(i)).getSize()) freemax = ((MemoryPartition) this.ram.get(i)).getSize();
            }
        }
        return hunnid * (free - freemax) / free;
    }

    //why do this?!
    public String toString() {
        return "No";
    }


    private void deleteProcessAtIndex(int position) {
        if (position >= 0) {
            ((MemoryPartition) this.ram.get(position)).release();
            // done
            coaless(position);
            if (position > 0) {
                coaless(position - 1);
            }
        }
    }


    //done
    private void coaless(int posi) {
        if (posi < this.ram.size() - 1 && (
                (MemoryPartition) this.ram.get(posi)).isFree() && ((MemoryPartition) this.ram.get(posi + 1)).isFree()) {
            int newSize = ((MemoryPartition) this.ram.get(posi)).getSize() + ((MemoryPartition) this.ram.get(posi + 1)).getSize();
            ((MemoryPartition) this.ram.get(posi)).setSize(newSize);
            this.ram.remove(posi + 1);
        }
    }

}