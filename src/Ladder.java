package ladder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import monkey.Monkey;
// Abstraction function:
// 梯子ADT
// Representation invariant:
// label 要大于0
// Safety from rep exposure:
// All fields are private and final.
// Thread safety argument：
// 用了monitor设计模式，可以完全保证线程安全。
public class Ladder {
  private final int label;
  private final Map<Rung, Monkey> rungs = Collections.synchronizedMap(new LinkedHashMap<Rung, Monkey>());
  private final List<Monkey> monkeysOnThisLadder = Collections.synchronizedList(new ArrayList<Monkey>());
  
  public Ladder(int label) {
    this.label = label;
  }
  public synchronized Map<Rung, Monkey> getRungs() {
    return rungs;
  }
  public synchronized void addMonkey(Monkey m) {
    this.monkeysOnThisLadder.add(m);
  }
  public synchronized void removeMonkey(Monkey m) {
    this.monkeysOnThisLadder.remove(m);
    this.rungs.replace(m.getWhichRung(), null);
  }
  public synchronized List<Monkey> getMonkeysOnThisLadder() {
    return monkeysOnThisLadder;
  }
  public int getLabel() {
    return label;
  }
  public boolean equals(Object obj) {  
    if(obj instanceof Ladder) {
      obj = (Ladder)obj;
      return (this.label == obj.getLabel());
    }
    return false;
  }
}
