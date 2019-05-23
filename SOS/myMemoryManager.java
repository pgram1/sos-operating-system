import sossim.core.*;

public class myMemoryManager implements IMemoryManager {
    public myMemoryManager() {

        private ArrayList <MemoryPartition> ram;
        private int ramSize;

        public void myMemoryManager(int size) {
            this.ramSize = size;
            this.ram = new ArrayList();
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

        public int getNumberOfPartitionsInMemory() {
            return this.ram.size();
        }

        public int getSizeOfPartitionInMemory(int i) {
            return ((MemoryPartition) this.ram.get(i)).getSize();
        }

        public int getAddressOfPartitionInMemory(int i) {
            return ((MemoryPartition) this.ram.get(i)).getBaseAddress();
        }

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

        public String toString() {
            return "No";
        }


        private void deleteProcessAtIndex(int position) {
            if (position >= 0) {
                ((MemoryPartition) this.ram.get(position)).release();
                coaless(position);
                if (position > 0) {
                    coaless(position - 1);
                }
            }
        }

    }
}
