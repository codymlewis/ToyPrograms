/**
 * A binary tree node implementation
 * @author Cody Lewis
 */
public class TNode<T extends Comparable<T>> implements Comparable<TNode<T>> {
  // Member variables
  private T data;
  private TNode<T> left, right;
  public TNode() {
    data = null;
    left = null;
    right = null;
  }
  public TNode(T data) {
    this.data = data;
    left = null;
    right = null;
  }
  public TNode(T data, TNode<T> left, TNode<T> right) {
    this.data = data;
    this.left = left;
    this.right = right;
  }
  public void setData(T data) { this.data = data; }
  public void setLeft(T data) { left = new TNode<T>(data); }
  public void setLeft(TNode<T> data) { left = data; }
  public void setRight(T data) { right = new TNode<T>(data); }
  public void setRight(TNode<T> data) { right = data; }
  public T getData() { return data; }
  public TNode<T> getLeft() { return left; }
  public TNode<T> getRight() { return right; }
  @Override
  public int compareTo(TNode<T> o) {
    return data.compareTo(o.getData());
  }
  public String toString(String pipes) {
    String str = "";
    if(right != null)
      str += right.toString(pipes + "|");
    str += pipes + data.toString() + "\n";
    if(left != null)
      str += left.toString(pipes + "|");
    return str;
  }
}
