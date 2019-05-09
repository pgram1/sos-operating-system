package sossim.core;

public interface IMemoryManager{

  //Called by the Kernel, asks the memory manager to create a  partition of size given to store a process.
  //Returns the base address of the partition
  //or -1 if a partition can not be created. Does NOT compact the memory. It is up to OS to decide if
  //it will require the memory to be compacted (see compact method bellow) or to ignore the process
  public int addProcess(int size);

  //Given the base address of a partition, deletes the process at that partition,
  //making that partition free. Coalesces the partitions automatically
  public void deleteProcessAtAddress(int address);

  //compacts the memory. Every time it relocates a process it informs the OS by calling os.informPT(int oldAddress, int newAddress)
  //in order to update the process table where oldAddress is the address of the process as it was recorded at the process table
  public void compact(SOS os);

  //Returns number of partitions in memory
  public int getNumberOfPartitionsInMemory();

  //Given the index of a partition returns the size of the partition
  public int getSizeOfPartitionInMemory(int i);

  //Given the index of a partition returns the base Address of the partition
  public int getAddressOfPartitionInMemory(int i);

  //calculates memory fragmentation according to the formula
  //(free - freemax)*100/free (or 100% for free=0)
  // where free  = total number of bytes free, freemax  = size of largest free block
  //That way, if all memory is in one big block, the fragmentation is 0%, and if memory is all carved up
  //into hundreds of tiny blocks, it will be close to 100%.
  public float calcFragmentation();

  public String toString();
}