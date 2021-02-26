public class Blob extends Node {
  // constructor chaining
  public Blob(int key, Node left, Node right) {
    super(key, left, right);  
  }
  public Blob(int key) {
    super(key);     
  }
  public static void main(String[] args) {
    Blob a = new Blob(1, new Blob(2, null, null), null);  
    Blob b = new Blob(3); // new Blob(3, null, null); 
    a.right = b; 
    a.display();
  }
}
