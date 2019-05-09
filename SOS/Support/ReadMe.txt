Simple OS Simulator
created by Kostas Dimopoulos 17/4/2016
updated 26/4/2019

contents:
At top level
SOS.pde - the main script of the simulator. You only need to edit the setup function
code - a fodler that contains the library in .jar format. Leave it as it is.
Support - useful documents

in support
IProcessScheduler.java - the source code of the interface your process scheduller needs to implement. 
Read the comments carefuly.
IMemoryManager.java -  the source code of the interface your Memory Manager needs to implement. 
Read the comments carefuly.
myProcessScheduller.java - A template to use in order to create your process schedullers. Your implementated .java file needs to be copied at the top level
mwMemoryManager.java - A template to use in order to create your Memory Managers. Your implementated .java file needs to be copied at the top level
SOSProcess.java - an implementation of how the simulator knows the processes
SimulationSetup.txt - an example text file you can create to use for inputing processes
ReadMe.txt - this file
log.txt - an example output file the simulator can create with the simulation findings


To better understand how the simulator will work consider the following comments
The simulation loop:
At every clock tick the kernel will:

  //------------------------------------------------------------------------------  
  //STEP 1: ARRIVAL OF NEW PROCESSES 
  //If there are still processes with arival times after teh current time, check if a new process has arrived at this clock time           
      //If a process arrived now          
        
        //Ask the if the memory manager can put it in memory. If not, ask the memory manager to compact the RAM and try again
        //if the memory manager still can not put it in memory, ignore the process request
          //if necessary update process table
          //add the process to the process scheduler queue


    //------------------------------------------------------------------------------    
    //STEP 2: OLD PROCESSES UNBLOCKING
    //wakeup processes that have blocked 10 ticks before   

    //------------------------------------------------------------------------------    
    //STEP 3: RUN PROCESS AT CPU
    //if CPU is empty, ask for a process from the process scheduler
      // Execute process in CPU 
      //if the process executes (@) block it for 10 ticks
      //else if process executed an exit call ($) clean up the process from system and empty the CPU 
 

    //------------------------------------------------------------------------------    
    //STEP 4: Update Scheduler and clock
    //update the process scheduler of simulation step, asking if it needs to pre-empt
  
    //Calculate fragmentation at this stage;

    //increment clock
