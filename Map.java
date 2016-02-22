/* Map contains a key-value Pair and a pointer to the next Map in a sequence of Maps. It also contains methods
to access or mutate the Map objects in various ways. */

public class Map {
  
  private Pair map_pair;        // A value-key pair to allow value access with a char key
  private Map next;             // next keeps track of the next Map node
  private Map temp;             // temp Map to allow traversal through nodes
  private Map add_map;          // Map to be filled and placed in the sequence of Maps
  private Pair sentinel;        // Indicate the end of a list of Maps
    
  public Map(){
    //initialize values to null, set up sentinel, and make sentinel first node
    next = null;
    temp = null;
    sentinel = new Pair(new MyChar((char)127), new MyInteger(-1057));
    map_pair = sentinel;
    //sentinel value for end() using highest ascii value. 127 -> DEL 
  } //Map constructor
  
  public Map(Map nex, Pair inval){
    //construct a Map with the given parameters
    next = nex;
    map_pair = inval;
  } //Map constructor 2
    
  public MapIterator begin(){
    //returns first node for iterator
    return new MapIterator(this);
  } //begin()
  
  public MapIterator end(){
    //return an iterator with the Sentinel Pair for comparisons
    temp = this;
    
    while (temp.next != null){
      temp = temp.next;
    } //traverse Map objects
    
    return new MapIterator(temp);
  } //end()
    
  public void Print(){
    //print in format "[ ('key' value) (...) ... ])"
    System.out.print("[ ");
    
    temp = this;
    
    while (temp.map_pair != sentinel){
      temp.map_pair.Print();
      System.out.print(" ");
      temp = temp.next;
    } //traverse Map objects
    
    System.out.print("]");
  } //Print()
  
  public void add(Pair inval){
    //add a new node with the given pair    

    if (map_pair == sentinel){
      next = new Map(null, sentinel);
      map_pair = inval;
      return;
    } //no pairs yet  
    
    if (inval.Get_Key().Get() < this.Get_Key().Get()){
      add_map = new Map(this.next, this.map_pair);
      this.map_pair = inval;
      this.next = add_map;
      return;
    } //add to front
    
    temp = this;
    
    while (inval.Get_Key().Get() >= temp.Get_Key().Get() && temp.next.map_pair != sentinel)
      temp = temp.next;
    
    if (inval.Get_Key().Get() == temp.Get_Key().Get() || 
        inval.Get_Key().Get() > temp.Get_Key().Get() && temp.next.map_pair == sentinel){
      add_map = new Map(temp.next, inval);
      temp.next = add_map;
      return;
    } // add to end of identical keys, or if added to back (before sentinel)
    else {
      add_map = new Map(temp.next, temp.map_pair);
      temp.map_pair = inval;
      temp.next = add_map;
      return;
    } //added to middle, placed before temp value
  } //add()
  
  public MapIterator find(MyChar key){
    //return a MapIterator to the first instance of a given key
 
    temp = this;
   
    while (temp.Get_Key().Get() != key.Get() && temp.next != null)
      temp = temp.next;
    
    return new MapIterator(temp);
  } //find
  
  public MyChar Get_Key(){
    return map_pair.Get_Key();
  } //Get_Key to map_pair
  
  public Map rest(){
    return next;
  } //return next map
  
  public Pair first(){
    return map_pair;
  } //return pair
} //clas Map
