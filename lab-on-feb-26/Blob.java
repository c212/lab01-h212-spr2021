import java.util.ArrayList; 
import java.util.Scanner; 

public class Blob {
  int key; 
  Blob left, right; 
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
  public Blob(int key) {
    this.key = key; 
    this.left = null; 
    this.right = null; 
  }
  public Blob(int key, Blob left, Blob right) {
    this.key = key; 
    this.left = left; 
    this.right = right; 
  }
  public void insert(int key) {
    if (key == this.key) {
      
    } else if (key < this.key) { // go left
      if (this.left == null) {
        this.left = new Blob(key); 
      } else {
        this.left.insert(key); // pass responsibility to that node
      }
    } else { // go right 
      if (this.right == null) {
        this.right = new Blob(key); 
      } else {
         this.right.insert(key);         
      }
     
    }
  }
  public static void main(String[] args) {
    Blob a = new Blob(1, new Blob(2, null, null), null);  
    Blob b = new Blob(3); // new Blob(3, null, null); 
    a.right = b; 
    a.display();
    // start with a new one 
    a = new Blob(5); 
    System.out.println("Starting with 5: "); 
    a.display(); 
    a.insert(3);
    System.out.println("After inserting 3: "); 
    a.display(); 
    a.insert(7);
    System.out.println("After inserting 7: "); 
    a.display(); 
    a.insert(6);
    System.out.println("After inserting 6: "); 
    a.display(); 
    a.insert(2); 
    System.out.println("After inserting 2: "); 
    a.display(); 

    System.out.println("\n\n\n\n"); 
    Scanner in = new Scanner(System.in); 
    System.out.print("Enter> "); 
    String input = in.nextLine();
    if (input.equals("bye")) System.exit(0); 
    int number = Integer.parseInt( input ); 
    a = new Blob( number ); 
    a.display(); 
    System.out.print("Enter> "); 
    input = in.nextLine();
    while (! input.equals("bye")) {
      number = Integer.parseInt(input);
      a.insert(number); 
      System.out.println("After inserting " + number + ": "); 
      a.display();
      System.out.print("Enter> "); 
      input = in.nextLine();
    }
    System.out.println("Bye, see you later."); 
  }
}
