class test{
  public static void main(String[] args){
    System.out.println("Initializing a doubly linked list and placing data into it");
    DLList<Integer> l = new DLList<Integer>();
    for(Integer i = 0; i < 11; i++){
      l.pushTail(i);
    }
    System.out.println("The DL list is " + l.toString());

    System.out.println("Initializing a circular linked list and placing data into it");
    CDLList<Integer> dl = new CDLList<Integer>();
    for(Integer i = 0; i < 11; i++){
      dl.push(i);
    }
    System.out.println("The CDL list is " + l.toString());
  }
}
