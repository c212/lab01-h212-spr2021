public class Blob {
  int key;
  Blob left, right; 
  public Blob(int key, Blob left, Blob right) {
    this.key = key; 
    this.left = left; 
    this.right = right; 
  }
  public Blob(int key) {
    // how do I describe this one in terms of the other constructor 
    this(key, null, null); // this is how you do it    
  }
  // how do you propose to print this structure? 
  
  public static void main(String[] args) {
    Blob a = new Blob(1, new Blob(2, null, null), null);  
    Blob b = new Blob(3); // new Blob(3, null, null); 
    a.right = b; 
    System.out.println(a); 
  }
}
