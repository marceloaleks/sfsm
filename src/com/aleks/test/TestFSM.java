package com.aleks.test;

import com.aleks.sfsm.Node;
import com.aleks.sfsm.Node;
import com.aleks.sfsm.SimpleFSM;
import com.aleks.sfsm.SimpleFSM;
import java.util.concurrent.TimeUnit;

public class TestFSM {

  public TestFSM() {

    SimpleFSM fsm = new SimpleFSM() {
      @Override
      public void onExit() {
        System.out.println("THE END");
      }
      
    };
    

    int secs[] = new int[]{3, 0};
    Node n2 = fsm.addState("T1", () -> {
      System.out.printf("First Node  pausa=%d secs\n", secs[0]);

      TimeUnit.SECONDS.sleep(secs[0]--);

    }).setTimeout(2000)
      .setRetries(5);

    fsm.addState(() -> {
      System.out.println("no name");
    });

    
    fsm.addState("T3", () -> {
      System.out.println("Third Node");
      secs[0] = 7;
    }).setNext("T1");

    
    
    //Redirect the log output to the sys out
//    fsm.setLogStream(System.out);
    fsm.print();
    fsm.startInSameThread();

  }

  public static void main(String[] args) throws Exception {
    new TestFSM();
    System.out.println("FIM");
  }
}
