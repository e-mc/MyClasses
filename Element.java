/* Element is an abstract class inherited by MyChar and MyInt */

abstract public class Element {

  abstract public void Print();
  //Calling Element's print function will call
  //either MyInteger or MyChar's prints depending
  //on the caller object

  abstract public void recurse_flatten(Sequence newseq);
  //call and return the correct flatten function

  abstract public Element deepcopy();
  //create a deep copy of whichever subclass called this

} //class Element
