/* Matrix can print, add, create, and multiply matrices */

public class Matrix extends Sequence {
  
  private Sequence row;        //head row of matrix
  private Matrix next_row;     //next row of matrix

  private Matrix product;      //product matrix to be created and returned
  
  private int col_count;       //column count
  private int row_count;       //row count
  private int counter;         //counter for sum and product methods
  private MyInteger sumint;    //store sums for DotProduct and Sum
  private MyInteger mult_int;  //store multiplied value for DotProduct
  
  private int col_dim;         //column dimension
  private int row_dim;         //row dimension

    
  public Matrix(int rowsize, int colsize){
    //create a row sequence and connect a next row to it
    mult_int = new MyInteger(0);
    sumint = new MyInteger(0);
    row = new Sequence();
    
    col_dim = colsize;
    row_dim = rowsize;
    
    for (col_count = 0; col_count < colsize; col_count++){
      row.add(new MyInteger(0), row.length());
    } //initialize all positions to 0        
    
    if (rowsize > 1)
      next_row = new Matrix(rowsize - 1, colsize);
  } //Matrix Constructor
  
  public void Set(int rowsize, int colsize, int value){
    //set value of particular index of this matrix
    //check bounds
    if (colsize >= col_dim || rowsize >= row_dim){
      System.err.println("Sequence overflow: out of bounds (Matrix.Set())");
      System.exit(1);
    } //out of bounds, error
    if (colsize < 0 || rowsize < 0){
      System.err.println("Sequence underflow: out of bounds (Matrix.Set())");
      System.exit(1);
    } //out of bounds, error      
    
    if (rowsize > 0){
      next_row.Set(rowsize - 1, colsize, value);
    } //find correct row by calling set on next and decl-ing rowsize

    else {
      row.delete(colsize);
      row.add(new MyInteger(value), colsize);
    } //found correct spot; delete value, add new one at position
  } //Set()
  
  public int Get(int rowsize, int colsize){
    // get the value of an element
    if (colsize >= col_dim || rowsize >= row_dim){
      System.err.println("Sequence overflow: out of bounds (Matrix.Get())");
      System.exit(1);
    } //position not in matrix

    if (colsize < 0 || rowsize < 0){
      System.err.println("Sequence underflow: out of bounds (Matrix.Get())");
      System.exit(1);
    } //position not in matrix
    
    if (rowsize > 0)
      return next_row.Get(rowsize - 1, colsize);
    
    
    else if (row.index(colsize) instanceof MyInteger){
      sumint = (MyInteger) row.index(colsize);
      return sumint.Get();
    } //double check class before casting
    
    return -1; //error
  } //Get()
  
  public Matrix Sum(Matrix mat){
    // return the sum of two matrices: mat & this
    for (col_count = 0; col_count < col_dim; col_count++){
      sumint = (MyInteger) this.row.index(col_count);
      //myint.Add((MyInteger) mat.row.index(col_count));
      sumint.Addition(mat.Get(0, col_count));
    } //iterate through and add
      
    if (row_dim > 1)
      next_row.Sum(mat.next_row);
    return this;
  } //Sum()
  
  public Matrix Product(Matrix mat){
    //return product of mat & this

    if (this.col_dim != mat.row_dim){
      System.out.println("Matrix dimensions incompatible for Product");
      System.exit(1);      
    } //can't multiply these dimensions
      
    product = new Matrix(this.row_dim, mat.col_dim);
    row_count = this.col_dim;
    col_count = mat.row_dim;
    
    for (row_count = 0; row_count < this.row_dim; row_count++){
      for (col_count = 0; col_count < mat.col_dim; col_count++){
        product.Set(row_count, col_count, DotProduct(mat, row_count, col_count, mat.row_dim, product.sumint));      
      } //compute dot product, add to correct pos in product
    } //run through all rows

    return product;
  } //Product()  
    
  public int DotProduct(Matrix mat, int row_num, int col_num, int dimension, MyInteger sum){
    //compute dot product of given row and column    

    sum = new MyInteger(0);
      
    for (counter = 0; counter < dimension; counter++){
      mult_int.Set(0);
      mult_int.Addition(this.Get(row_num, counter));
      mult_int.Multiply(mat.Get(counter, col_num));
      sum.Addition(mult_int.Get());
    } //iterate through and add into sum
      
    return sum.Get();
  } //DotProduct()
  
  public void Print(){
    //Print out matrix by calling print of each row
    row.Print();
    System.out.println();
    
    if (row_dim > 1)
      next_row.Print();
  } //Print()
} //class Matrix
