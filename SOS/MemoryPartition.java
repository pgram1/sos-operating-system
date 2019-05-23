public class MemoryPartition {
  private int bAddr;
  private int sz;
  private boolean freeSwitch;

  public MemoryPartition(boolean free, int s) {
      this.bAddr = 0;
      this.sz = s;
      this.freeSwitch = free;
  }

  public int getSize() {
      return this.sz;
  }

  public void setSize(int s) {
      this.sz = s;
  }

  public void setBaseAddress(int address) {
      this.bAddr = address;
  }

  public int getBaseAddress() {
      return this.bAddr;
  }

  public void release() {
      this.freeSwitch = true;
  }

  public void occupy() {
      this.freeSwitch = false;
  }

  public boolean isFree() {
      return this.freeSwitch;
  }
}