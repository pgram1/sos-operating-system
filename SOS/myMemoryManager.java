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

    private ArrayList<MemoryPartition> ram;
    private int ramSize;
    private boolean firstTime;
    private int lastPos;

    public myMemoryManager(int size) {
        this.ramSize = size;
        this.ram = new ArrayList<MemoryPartition>();
        this.ram.add(new MemoryPartition(false, this.ramSize));
        this.firstTime = true;
        this.lastPos = 0;
    }

    //fixed
    public int addProcess(int size) {
        findPart(size, this.lastPos);
        if (this.lastPos == -1) {
            return -1;
        }

        if (((MemoryPartition) this.ram.get(this.lastPos)).getSize() > size) {
            int ps2SZ = ((MemoryPartition) this.ram.get(this.lastPos)).getSize() - size;
            int ps2BA = ((MemoryPartition) this.ram.get(this.lastPos)).getBaseAddress() + size;
            this.ram.add(this.lastPos + 1, new MemoryPartition(ps2BA, ps2SZ));
            ((MemoryPartition) this.ram.get(this.lastPos)).setSize(size);
            ((MemoryPartition) this.ram.get(this.lastPos)).occupy();
        } else {
            ((MemoryPartition) this.ram.get(this.lastPos)).occupy();
        }


        return ((MemoryPartition) this.ram.get(this.lastPos)).getBaseAddress();
    }

    //done
    public int findPart(int size, int lastpos) {
        for (int i = lastpos; i < this.ram.size(); i++) {
            MemoryPartition p = (MemoryPartition) this.ram.get(i);
            if (p.isFree() && p.getSize() >= size) {
                this.lastPos = i;
                return i;
            }
        }
        //go all the way around
        this.lastPos = 0;
        return -1;
    }

    //done
    public void deleteProcessAtAddress(int address) {
        int index = findPartitionIndex(address);
        deleteProcessAtIndex(index);
    }

    //done
    public int findPartitionIndex(int ad) {
        for (int i = 0; i < this.ram.size(); i++) {
            if (ad == ((MemoryPartition) this.ram.get(i)).getBaseAddress()) {
                return i;
            }
        }
        return -1;
    }

    //done
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
    private int addProcessAt(int size, int posi) {
        if (((MemoryPartition) this.ram.get(posi)).getSize() > size) {
            int ps2S = ((MemoryPartition) this.ram.get(posi)).getSize() - size;
            int ps2BA = ((MemoryPartition) this.ram.get(posi)).getBaseAddress() + size;
            this.ram.add(posi + 1, new MemoryPartition(ps2BA, ps2S));
            ((MemoryPartition) this.ram.get(posi)).setSize(size);
            ((MemoryPartition) this.ram.get(posi)).occupy();
        }
        return ((MemoryPartition) this.ram.get(posi)).getBaseAddress();
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
        float free = 0.0F;
        float freemax = 0.0F;
        float hunnid = 100.0F;
        for (int i = 0; i < this.ram.size(); i++) {
            if (((MemoryPartition) this.ram.get(i)).isFree()) {
                free += ((MemoryPartition) this.ram.get(i)).getSize();
                if (freemax < ((MemoryPartition) this.ram.get(i)).getSize()) {
                    freemax = ((MemoryPartition) this.ram.get(i)).getSize();
                }
            }
        }
        return hunnid * (free - freemax) / free;
    }

    //:)
    public String toString() {
        return "NextFit";
    }


    //done
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
