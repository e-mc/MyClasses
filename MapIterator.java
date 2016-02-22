/* MapIterator holds a Map object that can point to Maps in a sequence of Maps. */


public class MapIterator {
  
  private Map itr;           //Map object to iterate through set of maps
  
  MapIterator(){
    //set itr to null
    itr = null;
  } //MapIterator constructor
  
  MapIterator(Map m){
    //point itr to the given map
    itr = m;
  } //MapIterator constructor 2
  
  public void advance(){
    //return next Map node
    itr = itr.rest();
  } //advance()
  
  public Pair get(){
    //return data in current Map node
    return itr.first();
  } //get()
  
  public Boolean equal (MapIterator rhs){
    //check equality of two Map nodes
    return itr == rhs.itr;
  } //equal 
} //class MapIterator
