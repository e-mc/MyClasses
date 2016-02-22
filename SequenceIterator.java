/* SequenceIterator class creates a sequence pointer and provides methods to advance or 
return data as this pointer traverses a given list of sequences. */

public class SequenceIterator {

  private Sequence itr;           //Sequence object to iterate through
  
  SequenceIterator(){
    //itialize pointer to null
    itr = null;
  } //SequenceIterator constructor
  
  SequenceIterator(Sequence seq){
    itr = seq;
  } //SequenceIterator constructor 2
  
  public void advance(){
    //move forward a node
    itr = itr.rest();
  } //advance()
  
  public Element get(){
    //return data in current node
    return itr.first();
  } //get()
  
  public Boolean equal (SequenceIterator rhs){
    //check equality of rhs and this nodes
    return itr == rhs.itr;
  } //equal
} //class SequenceIterator
