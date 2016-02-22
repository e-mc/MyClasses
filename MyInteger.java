/* MyInteger class hold the value of an int and includes functions to access or mutate that int.  */

import java.lang.Integer;

class MyInteger extends Element{

  private Integer myint;         //store value of int using Integer wrapper class
  
  public MyInteger(){
    //initialize to 0
    Integer myint = new Integer(0);
  } //MyInteger Constructor
  
  public MyInteger(int val){
    myint = new Integer(val);
  } //MyInteger Constructor 2
  
  public void Addition(int rhs){
    myint += rhs;
  } //add to self
  
  public void Multiply(int rhs){
    myint *= rhs;
  } //multiply with self
  
  public int Get(){
    //return value stored in myint
    return myint;
  } //Get()
  
  public void recurse_flatten(Sequence newseq){
    //add this into passed down sequence
    newseq.add(this, newseq.length());
  } //recurse_flatten
  
  public void Set(int val){
    //Set myint to given val
    myint = val;
  } //Set()
  
  public void Print(){
    //print int in format "3"
    System.out.print(myint);
  } //Print()
  
  public MyInteger deepcopy(){    
    //return a new instance of a MyInt with correct value
    return new MyInteger(myint);
  } //deepcopy()
} //class MyInteger
