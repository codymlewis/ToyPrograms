/* BSTree.hpp - Assignment 3
 * Author: Cody Lewis - c3283349
 * Date: 23-10-2017
 * Last Modified: 31-10-2017
 * Description:
 * The implementation for a binary search tree
 */
 #include <cstdlib>
 namespace lewis_a03{
 	 //Constructor
   template <typename value_type>
   BSTree<value_type>::BSTree(){
     root = new BTNode<value_type>();
     firstAdd = true;  //Shows whether the root holds content
     vertices = 0;     //The number of nodes in the tree
   };
   //Destructor
   template <typename value_type>
   BSTree<value_type>::~BSTree(){
     if(root!=NULL)
       destroy(root);
   };
   //Mutators

   //Tree destructing method
   template <typename value_type>
   void BSTree<value_type>::destroy(BTNode<value_type>* node){
     if(node!=NULL){
       if(node->getLeft()!=NULL)
         destroy(node->getLeft());
       if(node->getRight()!=NULL)
         destroy(node->getRight());
      rm(node);
     }
   }
   //Root setting method
   template <typename value_type>
   bool BSTree<value_type>::setRoot(BTNode<value_type>* &data){
     root->setData(data->getData());
     return false; //states that the BTNode is not placed into the tree
   };
   //adding wrapper method
   template <typename value_type>
   bool BSTree<value_type>::add(BTNode<value_type>* &data){
     vertices++;
     return add(data,root);
   }
   //The specific add method
   template <typename value_type>
   bool BSTree<value_type>::add(BTNode<value_type>* &data,BTNode<value_type>* branch){
     if(firstAdd){  //Checks that the root is empty
      firstAdd = false;
      return setRoot(data);
     }
     //check whether lower nodes are empty
     switch (compare(data->getData(),branch->getData())) {
       case -1:if(branch->getLeft()==NULL){
                 branch->setLeft(data);
                 data->setParent(branch);
                 return true;
               }
               return add(data,branch->getLeft()); //Traverses the tree based on the data sent in
       case 1:if(branch->getRight()==NULL){
                branch->setRight(data);
                data->setParent(branch);
                return true;
              }
              return add(data,branch->getRight());
       default: vertices--;
                return false; //This gets the zero case
     }
   };
   //Removal functions

   //The removal setup
   template <typename value_type>
   void BSTree<value_type>::remove(int find){
     remove(find,root);
   }
   //The traversal method for the removal
   template <typename value_type>
   void BSTree<value_type>::remove(int find,BTNode<value_type>* node){
     if(node->getLeft()){ //These are implicit bools
       remove(find,node->getLeft());
     }
     if(node->getRight()){
       remove(find,node->getRight());
     }
     if(node->getData() < find){ //I found a bottom up approach much easier than top-down
       remove(node);
       node = NULL;
     }
   }
   //Method to check the positioning of the node, then send to the suitable method
   template <typename value_type>
   void BSTree<value_type>::remove(BTNode<value_type>* &node){
     if(!node->getLeft() && !node->getRight()){ //These are implicit bools
       removeLeaf(node);
     } else if(node->getLeft() && node->getRight()){
       removeSubRoot(node);
       vertices++; //This stops entropy, the above function does not remove, just changes
     } else if(node->getLeft() && !node->getRight()){
        removeSubLeft(node);
      } else {
       removeSubRight(node);
     }
     vertices--;
   }
   //Leaf removal
   template <typename value_type>
   void BSTree<value_type>::removeLeaf(BTNode<value_type>* &node){
    if(node->getParent()){
      if(node->getData() < node->getParent()->getData()){ //Left is less
        node->getParent()->setLeft(NULL);
      } else {
        node->getParent()->setRight(NULL);
      }
    }
    rm(node);
   }
   //double subtree removal
   template <typename value_type>
   void BSTree<value_type>::removeSubRoot(BTNode<value_type>* &node){
     //Find replacement
     BTNode<value_type>* delta = replace(node->getLeft()); //delta because it changes the node
     value_type newVal = delta->getData();
     remove(delta); //recursively gets rid of delta
     node->setData(newVal);
   }
   //Replacement method
   template <typename value_type>
   BTNode<value_type>* BSTree<value_type>::replace(BTNode<value_type>* node){
     if(node->getRight()){
       return replace(node->getRight());
     } else {
       return node;
     }
   }
   //Left sub tree root removal
   template <typename value_type>
   void BSTree<value_type>::removeSubLeft(BTNode<value_type>* &node){
   //For when there is a node+ to the left
    node->getLeft()->inherit(node);
   }
   //Right sub tree root removal
   template <typename value_type>
   void BSTree<value_type>::removeSubRight(BTNode<value_type>* &node){
   //For when there is a node+ to the right
    node->getRight()->inherit(node);
   }
  //removal wrapper method
   template <typename value_type>
   void BSTree<value_type>::rm(BTNode<value_type>*& node){
     delete node;
     node = NULL;
   }
   //Queries
   template <typename value_type>
   std::ostream& BSTree<value_type>::outputTree(std::ostream& out) const{
     if(root) //implicit bool from the memory address
      out << *root;  //This removes the need for a poiter returning function from this object
     else
      out << "";
     return out;
   }
   //emptiness query
   template <typename value_type>
   bool BSTree<value_type>::isEmpty() const{
     return (firstAdd);
   };
   //size query
   template <typename value_type>
   std::size_t BSTree<value_type>::size() const{
     return vertices;
   }
   //Search Queries
   //search for node by name
   template <typename value_type>
   std::ostream& BSTree<value_type>::search(std::ostream& out,std::string name){
     value_type temp = search(root,name);
      if(temp)
        out << temp;
      else
        out << "Name not found";
      return out;
   }
   //tree traversing search for name
   template <typename value_type>
   value_type BSTree<value_type>::search(BTNode<value_type>* node,std::string name){
     value_type temp;
      if(name==(node->getData())){
        temp = node->getData();
      } else if(name<node->getData()){
        if(node->getLeft()){
          temp = search(node->getLeft(),name);
        }
      } else if(name>(node->getData())){
        if(node->getRight()){
          temp = search(node->getRight(),name);
        }
      }
    return temp;
   }
   //Search for nodes lower than the specified value
   template <typename value_type>
   std::size_t BSTree<value_type>::search(int grade){
     return search(root,grade);
   }
   //Tree traversing search by value
   template <typename value_type>
   std::size_t BSTree<value_type>::search(BTNode<value_type>* node,int grade){
     std::size_t found;
     if(node->getData() < grade){
      found = 1;
     } else {
      found = 0;
     }
     if(node->getLeft()){
       found = (found + search(node->getLeft(),grade));
     }
     if(node->getRight()){
       found = (found + search(node->getRight(),grade));
     }
     return found;
   }
   //averaging function
   template <typename value_type>
   double BSTree<value_type>::average(){
     return sum(root)/size();
   }
   //sum function
   template <typename value_type>
   double BSTree<value_type>::sum(BTNode<value_type>* node){
     double sigma = node->getData();
     if(node->getLeft()){
       sigma += sum(node->getLeft());
     }
     if(node->getRight()){
       sigma += sum(node->getRight());
     }
     return sigma;
   }
   template <typename value_type>
   void BSTree<value_type>::invert(){
     invert(root);
   }
   template <typename value_type>
   void BSTree<value_type>::invert(BTNode<value_type>* node){
     if(node->getRight() || node->getLeft()){
       BTNode<value_type>* temp = node->getRight();
       node->setRight(node->getLeft());
       node->setLeft(temp);
       temp = NULL;
     }
     if(node->getRight()){
       invert(node->getRight());
     }
     if(node->getLeft()){
       invert(node->getLeft());
     }
   }
   //Output operator
   template <typename value_type>
   std::ostream& operator<<(std::ostream& out,const BSTree<value_type>& bst){
     if(!bst.isEmpty()){
       bst.outputTree(out);
     }
     return out;
   };
   //Input operator
   template <typename value_type>
   std::istream& operator>>(std::istream& in,BSTree<value_type>& bst){
     BTNode<value_type>* data = new BTNode<value_type>();
     in >> *data;
     if(!bst.add(data)){
       delete data;  //This stops the 2 possible memory leaks
     }
     data = NULL;
     return in;
   };
   //Comparison function
   template<typename value_type>
   int compare(value_type lhs,value_type rhs){
     if (lhs<rhs) {
       return -1;
     } else if (lhs>rhs) {
       return 1;
     } else {
       return 0;
     }
   };
}
