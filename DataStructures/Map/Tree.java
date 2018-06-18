public class Tree<T extends Comparable<T>> {
  private TNode<T> root;
  public Tree() {
    root = null;
  }
  public Tree(T data) {
    root = new TNode<T>(data);
  }
  public boolean isEmpty() { return root == null; }
  public void add(T data) {
    if(isEmpty()) {
      root = new TNode<T>(data);
    } else {
      TNode<T> addNode = new TNode<T>(data);
      traverseAdd(addNode, root);
    }
  }
  public void traverseAdd(TNode<T> addNode, TNode<T> currentNode) {
    int compare = currentNode.compareTo(addNode);
    if(compare == 0) {
      return;
    } else if(compare < 0) {
      if(currentNode.getLeft() == null) {
        currentNode.setLeft(addNode);
      } else {
        traverseAdd(addNode, currentNode.getLeft());
      }
    } else {
      if(currentNode.getRight() == null) {
        currentNode.setRight(addNode);
      } else {
        traverseAdd(addNode, currentNode.getRight());
      }
    }
  }
  public T find(T data) {
    return find(new TNode<T>(data), root);
  }
  public T find(TNode<T> node, TNode<T> current) {
    int compare = current.compareTo(node);
    if(compare == 0) {
      return current.getData();
    } else if(compare < 0 && current.getLeft() != null) {
      return find(node, current.getLeft());
    } else if(compare > 0 && current.getRight() != null) {
      return find(node, current.getRight());
    } else {
      return null;
    }
  }
  public String toString() { return root.toString(""); }
}
