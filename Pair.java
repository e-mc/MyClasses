/* The Pair class contains a character, key, and methods to get, set, or access them in some way.  */


public class Pair extends Element {
    
  MyChar key;
  Element value;
  
  public Pair(){
    key = null;
    value = null;
  } //Pair constructor 1
  
  public Pair(MyChar k, Element val){
    key = k;
    value = val;
  } //Pair constructor 2
    
  public void Print(){
    //print in format ('key' value)
    System.out.print("(");
    key.Print();
    System.out.print(" ");
    value.Print();
    System.out.print(")");
  } //Print()
  
  public MyChar Get_Key(){
    return key;
  } //return the key
  
  public void recurse_flatten(Sequence newseq){
     return;
  } //empty stub to allow extends without changing other parts
 
  public Element deepcopy(){
     return value;
  } //empty stub to allow extends without changing other parts
} //class Pair
