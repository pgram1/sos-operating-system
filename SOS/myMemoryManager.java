import sossim.core.IMemoryManager;

public class myMemoryManager implements IMemoryManager{
  public myMemoryManager(){
  
    public int addProcess(int size){
    return 1;
    }
    
    public void deleteProcessAtAddress(int address){
    
    }
    
    public void compact(SOS os){
    
    }
    
    public int getNumberOfPartitionsInMemory(){
    return 1;
    }
    
    public int getSizeOfPartitionInMemory(int i){
    return 1;
    }
    
    public int getAddressOfPartitionInMemory(int i){
    return 1;
    }
    
    public float calcFragmentation(){
    return 1.0;
    }
    
    public String toString(){
    return "Fancypants";
    }
  
  }
}
