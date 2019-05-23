public class MemoryPartition {
  private int bAddr;
  private int sz;
  private boolean freeSwitch;

  public MemoryPartition(int bA, int s) {
      this.bAddr = bA;
      this.sz = s;
      this.freeSwitch = true;
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