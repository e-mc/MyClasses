/* MyChar class hold the value of a char and includes functions to access or mutate that char.  */

import java.lang.Character;

class MyChar extends Element {

  private Character mychar;     //store value of char using java wrapper class Character
  
  public MyChar(){
    //initiate new char object to '0'
    mychar = new Character('0');
  } //MyChar Constructor
  
  public MyChar(char val){
    mychar = new Character(val);
  } //MyChar Constructor 2
  
  public char Get(){
    //returns value stored in char variable
    return mychar;
  } //Get()
  
  public void Set(char val){
    //sets mychar to given char value
    mychar = val;
  } //Set()
  
  public void Print(){
    //print char in format 'A'
    System.out.print("'" + mychar + "'");
  } //Print()
  
  public void recurse_flatten(Sequence newseq){
    //add this into passed down sequence
    newseq.add(this, newseq.length());
  } //recurse_flatten()
  
  public MyChar deepcopy(){
    //return a new instance of a MyChar with correct value
    return new MyChar(mychar);
  } //deepcopy()
} //class MyChar
