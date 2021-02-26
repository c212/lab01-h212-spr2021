import java.util.ArrayList; 

public class Node {
  int key; 
  Node left, right; 
  public Node(int key) {
    this.key = key; 
    this.left = null; 
    this.right = null; 
  }
  public Node(int key, Node left, Node right) {
    this.key = key; 
    this.left = left; 
    this.right = right; 
  }
  public void display() {
    Displaux displaux = this.displaux(); 
    for (String line : displaux.lines) 
      System.out.println( line ); 
  }
  public Displaux displaux() {
    if (this.right == null && this.left == null) { 
      String line = this.key + ""; 
      int width = line.length(); 
      int height = 1; 
      int middle = width / 2; 
      ArrayList<String> lines = new ArrayList<String>(); 
      lines.add(line); 
      return new Displaux( lines, width, height, middle ); 
    }
    if (this.right == null) { 
      Displaux result = this.left.displaux(); 
      ArrayList<String> lines = result.lines; 
      int n = result.width; 
      int p = result.height; 
      int x = result.middle; 
      String s = this.key + ""; 
      int u = s.length(); 
      String firstLine = f(x + 1, ' ') + f(n - x - 1, '_') + s;
      String secondLine = f(x, ' ') + "/" + f(n - x - 1 + u, ' '); 
      ArrayList<String> shiftedLines = new ArrayList<String>();
      for (String line : lines) 
        shiftedLines.add(line + f(u, ' ')); 
      shiftedLines.add(0, secondLine); 
      shiftedLines.add(0, firstLine);
      return new Displaux(shiftedLines, n + u, p + 2, n + u / 2); 
    }
    // Only right child.
    if (this.left == null) { 
      Displaux result = this.right.displaux(); 
      ArrayList<String> lines = result.lines; 
      int n = result.width; 
      int p = result.height; 
      int x = result.middle; 
      String s = this.key + ""; 
      int u = s.length(); 
      String firstLine = s + f(x, '_') + f(n - x, ' ');
      String secondLine = f(u + x, ' ') + "\\" + f(n - x - 1, ' '); 
      ArrayList<String> shiftedLines = new ArrayList<String>();
      for (String line : lines) 
        shiftedLines.add(f(u, ' ') + line); 
      shiftedLines.add(0, secondLine); 
      shiftedLines.add(0, firstLine);
      return new Displaux(shiftedLines, n + u, p + 2, u / 2); 
    } 
    // Two children.
    Displaux result = this.left.displaux(); 
    ArrayList<String> left = result.lines; 
    int n = result.width; 
    int p = result.height; 
    int x = result.middle; 
    result = this.right.displaux(); 
    ArrayList<String> right = result.lines; 
    int m = result.width; 
    int q = result.height; 
    int y = result.middle; 
    String s = this.key + ""; 
    int u = s.length(); 
    
    String firstLine = f(x + 1, ' ') + f(n - x - 1, '_') + s + f(y, '_') + f(m - y, ' ');
    String secondLine = f(x, ' ') + "/" + f(n - x - 1 + u + y, ' ') + "\\" + f(m - y - 1, ' ');  
    
    if (p < q) { 
      for (int i = 0; i < q - p; i++) 
        left.add(f(n, ' '));  
    } else if (q < p) { 
      for (int i = 0; i < p - q; i++) 
        right.add(f(m, ' '));  
    } 
      
    ArrayList<String> lines = new ArrayList<String>();  
    for (int count = 0; count < Math.min(left.size(), right.size()); count++)         
      lines.add( left.get(count) + f(u, ' ') + right.get(count) );     
    lines.add(0, secondLine);
    lines.add(0, firstLine); 
    return new Displaux(lines, n + m + u, Math.max(p, q) + 2, n + u / 2); 
    
   
  }
  public String f(int length, char c) {
    return new String(new char[length]).replace('\0', c);
  }  
  public ArrayList<String> g(int length, String s) {
    ArrayList<String> result = new ArrayList<String>(); 
    for (int i = 0; i < length; i++) {
      result.add(s); 
    }
    return result;
  }     
  public String toString() {
    return this.key + "(" + this.left + ", " + this.right + ")"; 
  }
  public static void main(String[] args) {
    Node a = new Node(5); 
    Node temp = new Node(2); 
    a.left = temp;     
    temp = new Node(10); 
    a.right = temp;     
    temp = new Node(7); 
    a.right.left = temp; 
    temp = null; 
    temp = new Node(9); 
    a.right.right = temp; 
    temp = new Node(8); 
    a.right.left.right = temp;
    a.display(); 
    System.out.println( a ); 
     
  }
}

    
