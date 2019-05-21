import processing.core.PApplet;
import sossim.gui.*;

public class SOS extends PApplet {
  
  private SOSGUI gui;

  public static void main(String[] args) {
    PApplet.main("SOS");  
  }

  public void settings() {
    size(900, 600);
  }

  public void setup() {  
    //Sets the simulator.
    gui = new SOSGUI(this); 
    
    //Add this to define manually whatever memory size you want:
    gui.defineRAMSize(512);
    
    //Add this to add your implementation of a process scheduller:
    gui.defineProcessScheduller(new myProcessScheduller());
    
    //Add this to add your implementation of a memory manager:
    gui.defineMemoryManager(new myMemoryManager(512));      
  }

  public void draw() {
    gui.draw();
  }
  
  public void keyPressed() {
    if (key == CODED) {
      if (keyCode == UP) {
        gui.incSimStep();
      } else if (keyCode == DOWN) {
        gui.decSimStep();
      } else if (keyCode == LEFT) {
        gui.decRRSlices();
      } else if (keyCode == RIGHT) {
        gui.incRRSlices();
      }
    }else{
      if (keyCode == 'p' || keyCode == 'P') {
        gui.playSim();
      } else if (keyCode == 's' || keyCode == 'S') {
        gui.pauseSim();
      } else if (keyCode == 'f' || keyCode == 'F') {
        gui.forwardSim();
      } else if (keyCode == 'x' || keyCode == 'X') {
        gui.endSim();
      }
    }
  }
}
