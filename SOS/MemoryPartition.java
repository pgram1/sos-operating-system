/*
   _____ _____             ____                 
  / __(_) / (_)__  ___ _  / __/__  ___ ________ 
 / _// / / / / _ \/ _ `/ _\ \/ _ \/ _ `/ __/ -_)
/_/ /_/_/_/_/_//_/\_, / /___/ .__/\_,_/\__/\__/ 
                 /___/     /_/                  
   ___           __  __         __          __      
  / _/__  ____  / /_/ /  ___   / /__  ___  / /__ ___
 / _/ _ \/ __/ / __/ _ \/ -_) / / _ \/ _ \/  '_/(_-<
/_/ \___/_/    \__/_//_/\__/ /_/\___/\___/_/\_\/___/
                                                    
*/


public class MemoryPartition {
    private int bAddr;
    private int sz;
    private boolean freeSwitch;

    public MemoryPartition(boolean free, int s) {
        this.bAddr = 0;
        this.sz = s;
        this.freeSwitch = free;
    }

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
