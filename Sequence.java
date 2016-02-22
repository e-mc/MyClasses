/* Sequence class will hold an Element and a point to the next Sequence.
According to the calls in the test files, sequence acts as a listnode as opposed
to a container for a head listnode, which would then lead to others.  */

class Sequence extends Element {

  private Element data;               //holds either a MyChar or MyInt
  protected Sequence next;            //points to next sequence object
  private Sequence prev;              //points to the previous sequence object
  
  protected Sequence temp;            //temp is used to traverse sequences by pointing to next or prev
  protected Sequence add;             //add is initialized to correct values and places in the sequence list
  
  private final MyInteger sentinel;   //dummy variable to signal end of iterating
  private int seqcount;               //count of seqs from the current to the last one
  private int counter;                //a counter for traversals

  public Sequence(){
    //initialize all to null, with count of 0
    this.seqcount = 0;
    this.sentinel = new MyInteger(-1057);
    this.data = sentinel;
    this.next = null;
    this.prev = null;
  } //Sequence constructor
  
  public Sequence(Element dat, Sequence pre, Sequence nex, int count){
    //create a sequence with the given values
    this.sentinel = new MyInteger(-1057);
    this.seqcount = count;
    this.data = dat;
    this.next = nex;
    this.prev = pre;
  } //Sequence constructor 2
  
  public void add(Element ele, int pos){
    //performs checks then either adds to front
    //or calls the recursive_add function
    if (pos < 0){
      System.err.println("Sequence underflow: out of bounds (Sequence.add)");
      System.exit(1);
    } // index less than 0, out of bounds
    if (pos > seqcount){
      System.err.println("Sequence overflow: out of bounds (Sequence.add)");
      System.exit(1);
    } // pos too high, out of bounds
    
    //temp = this;
    
    if (pos == 0){
      //copy contents of head node to new node
      add = new Sequence(this.data, this.prev, this.next, seqcount);   
      //copy inserted data to head node and make add_seq the next node
      this.data = ele;
      
      if (this.next != null)
        this.next.prev = add;
      
      this.next = add;
      this.seqcount++;
      return;
    } //add to front of list
    
    temp = this;
    counter = 0;
    
    while (temp.prev != null){
      temp = temp.prev;
      counter++;
    } //traverse to first sequence node
    
    temp.recurse_add(ele, pos + counter); 
    //start from first, recursively update nodes

    return;
  } //add()
  
  private void recurse_add(Element ele, int pos){
    //run through calling recurse_add until the correct position is found

    if (pos > 1){
      this.seqcount++;
      next.recurse_add(ele, pos - 1);
    } //recursively update all nodes, until this.next is where we want to add
    
    else {
      //we're at the node before the position, add and update
      add = new Sequence(ele, this, this.next, this.seqcount);
      
      if (this.next != null)
        this.next.prev = add;
      this.next = add;
      this.seqcount++;
    } //if pos = 1
    
    return;
  } //recurse_add

  public void delete(int pos){
    //delete a node at the given position       
    //check if in bounds, else return error
    if (pos < 0){
      System.err.println("Sequence underflow: out of bounds (Sequence.delete)");
      System.exit(1);
    } //underflow error
    if (pos >= seqcount){
      System.err.println("Sequence overflow: out of bounds (Sequence.delete)");
      System.exit(1);
    } //overflow error

    if (seqcount == 1){
      seqcount--;
      this.next = null;
      this.data = null;
      return;
    } //only one item in list
 
    temp = this;
    counter = 0;
    
    while (temp.prev != null){
      temp = temp.prev;
      counter++;
    } //traverse to first sequence node
    
    temp.recurse_delete(pos + counter);
    //always call rec_delete from the first node
  } //delete()
  
  private void recurse_delete(int pos){
    //run through calling recurse_delete until the correct position is found 
           
    if (pos > 0){
      this.seqcount--;
      next.recurse_delete(pos - 1);
    } //recursively update all nodes, decl-ing count
    
    else if (this.next != null){
      this.data = next.data;
      this.seqcount--;
      
      next.recurse_delete(0);
    } //if pos = 0, we're at the node we want to delete
    
    if (this.seqcount == 0){
      this.next = null;        
    } //we shifted everything left, so the last node is set to null

    //all data was shifted left, set final node to null
    
    return;
  } //recurse_delete()
  
  public void Print(){
    //print out Sequence in form "[ int 'char' int ... ]"      

    System.out.print("[ ");
    
    //counter = 0;
    temp = this;
    
    for (counter = 0; counter < this.seqcount; counter++){
      temp.data.Print();
      System.out.print(" ");
      temp = temp.next;
    } //traverse nodes with temp, print each element
    
    System.out.print("]");
  } //Print()
  
  public Element index(int pos){
    //access an element at a particular position  
    //check if in bounds, else return error
    if (pos < 0){
      System.err.println("Sequence underflow: out of bounds (Sequence.index)");
      System.exit(1);
    } //underflow error
    if (pos >= seqcount){
      System.err.println("Sequence overflow: out of bounds (Sequence.index)");
      System.exit(1);
    } //overflow error
    
    counter = 0;
    temp = this;
    
    while (counter < pos){
      temp = temp.next;
      counter++;
    } //traverse sequences with temp
    
    return temp.data;  
  } //index()
  
  public Sequence flatten(){
    //shallow copy of all elements within this
    //add elements of any subseqence found to current sequence

    add = new Sequence();
    temp = this;
    
    while (temp.next != null){
      temp.data.recurse_flatten(add);        
      temp = temp.next;
    } //traverse and flatten all elements of seq

    return add;
  } //flatten()
  
  public void recurse_flatten(Sequence newseq){
    //recursively call flatten from any subsequences/elements of subsequences
    temp = this;
    
    while (temp.next != null){
      temp.data.recurse_flatten(newseq);
      temp = temp.next;
    } //traverse and flatten any nested seqs
     
    return;
  } //recurse_flatten()
  
  public Sequence copy(){
    //make a deep copy of the current sequence
    add = new Sequence();
    temp = this;
    
    while (temp.next != null){
      add.add(temp.data.deepcopy(), add.length());
      temp = temp.next;
    } //tranverse and copy
    
    return add;
  } //copy
  
  public Sequence deepcopy(){
    //deepcopy functions defined for all Element subclasses
    //this function passes around new seq add, filling it with correct vals

    temp = this;
    add = new Sequence();
    
    while (temp.next != null){
      add.add(temp.data.deepcopy(), add.length());
      temp = temp.next;
    } //traverse and add a deepcopy of each element to a new seq
  
    return add;
  } //deepcopy()
  
  public Element first(){
    //return data from current node
    return this.data;
  } //first()

  public Sequence rest(){
    //return the next node
    return this.next;
  } //rest()
  
  public SequenceIterator begin(){
    //returns first node for iterator
    return new SequenceIterator(this);
  } //begin()
  
  public SequenceIterator end(){
    //end sequence flagged by a -1 seqcount and magic number -1057
    temp = this;
    
    while (temp.next != null){
      temp = temp.next;
    } //traverse till sentinel is found
    
    return new SequenceIterator(temp);
  } //end()
  
  public int length(){
    //return length of list from current node on
    return seqcount;
  } //length()
  
} //class Sequence
