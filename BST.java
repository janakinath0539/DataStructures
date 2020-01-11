import java.util.Scanner;

class BinarySearchTree {

    class Node {
        int data;
        int count=1;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    Node root,predecessor,successor=null;

    BinarySearchTree() {
        root = null;
    }


    Node insert(Node root,int value) {

        if (root == null) {
            root = new Node(value);
            return root;
        }
        if(root.data==value) {
            root.count = root.count + 1;
            return root;
        }

        if (value < root.data)
            root.left = insert(root.left, value);
        else if (value > root.data)
            root.right = insert(root.right, value);
        return root;
    }


    Node delete(Node root, int value)
    {
        if (root == null)  return root;

        if (value < root.data)
            root.left = delete(root.left, value);
        else if (value > root.data)
            root.right = delete(root.right, value);

        else
        {
            if(root.count>1){
                root.count--;
                return root;
            }


            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.data = BSTMin(root.right);

            root.right = delete(root.right, root.data);
        }

        return root;
    }


    void Successor_Predecessor(Node root, int value)
    {
        predecessor=null;
        successor=null;
        if (root == null)
            return;

        while (root != null)
        {

            if (root.data == value)
            {

                if (root.right != null)
                {
                    successor = root.right;
                    while (successor.left != null)
                        successor = successor.left;
                }

                if (root.left != null)
                {
                    predecessor = root.left;
                    while (predecessor.right != null)
                        predecessor = predecessor.right;
                }

                return;
            }

            else if (root.data < value)
            {
                predecessor = root;
                root = root.right;
            }
            else
            {
                successor = root;
                root = root.left;
            }
        }
    }



    Node search(Node root, int value)
    {

        if (root==null || root.data==value)
               return root;

        if (root.data > value)
            return search(root.left, value);

        return search(root.right, value);
    }


    void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            if(root.count==1)
             System.out.print(root.data+" ");
            else
                System.out.print(root.data+"("+root.count+")"+" ");
            inorder(root.right);
        }
    }

void preorder(Node root) {
    if (root != null) {
        if(root.count==1)
            System.out.print(root.data+" ");
        else
            System.out.print(root.data+"("+root.count+")"+" ");
        preorder(root.left);
        preorder(root.right);
    }
}

 void postorder(Node root) {
     if (root != null) {
         postorder(root.left);
         postorder(root.right);
         if(root.count==1)
             System.out.print(root.data+" ");
         else
             System.out.print(root.data+"("+root.count+")"+" ");
     }
 }

    int depth(Node node, int data, int depth)
    {
        if (node == null)
            return 0;

        if (node.data == data)
            return depth;

        int dl =depth(node.left, data, depth + 1);
        if (dl != 0)
            return dl;

        dl = depth(node.right, data, depth + 1);
        return dl;
    }

    int BSTMax(Node node)
    {
        if (node == null)
            return Integer.MIN_VALUE;

        int key = node.data;
        int lr = BSTMax(node.left);
        int rr = BSTMax(node.right);

        if (lr > key)
            key = lr;
        if (rr > key)
            key = rr;
        return key;
    }

    int BSTMin(Node node)
    {
        if (node == null)
            return Integer.MAX_VALUE;

        int key = node.data;
        int lr = BSTMin(node.left);
        int rr = BSTMin(node.right);

        if (lr < key)
            key = lr;
        if (rr < key)
            key = rr;
        return key;
    }


}



public class BST{

    public static void main(String[] args) {

        BinarySearchTree tree = new BinarySearchTree();
        Scanner in = new Scanner(System.in);
        int ex=-1;
        do {
            System.out.println(" 1.Insert\t 2.Delete\t 3.Search\n 4.Minimum \t 5.Maximum \t 6.Successor\n 7. Predecessor \t 8. Traversals \t 9.Delete all nodes \n 0.Exit");
            System.out.println("BST operations - choose an option:");
            int option = in.nextInt();
            switch (option) {
                case 1:
                    System.out.print("Enter value to be inserted:");
                    int key = in.nextInt();
                    tree.root = tree.insert(tree.root, key);
                    System.out.print("Inserted node Depth is:");
                    int d=tree.depth(tree.root,key,0);
                    System.out.println(d);
                    break;
                case 2:
                    System.out.print("Enter value to be deleted:");
                    int key1 = in.nextInt();
                    BinarySearchTree.Node element =tree.search(tree.root,key1);
                    if(element==null)
                        System.out.println("Deletion unsuccessful");
                    else {
                        BinarySearchTree.Node y = tree.delete(tree.root, key1);
                        int d2 = tree.depth(tree.root, key1, 0);
                        System.out.print("Deleted node Depth:");
                        System.out.println(d2);
                    }
                    break;
                case 3:
                    System.out.print("Enter value to search:");
                    int key2 = in.nextInt();
                    BinarySearchTree.Node t= tree.search(tree.root, key2);
                    if(t==null)
                        System.out.println("key not found");
                    else {
                        System.out.println("key is found");
                        System.out.print("Depth of the searched node "+key2+ "is:");
                        int d3=tree.depth(tree.root,key2,0);
                        System.out.println(d3);
                    }
                    break;
                case 4:
                    int min=tree.BSTMin(tree.root);
                    System.out.println("Minimum node in a tree: "+min);
                    int d4=tree.depth(tree.root,min,0);
                    System.out.println("Minimum node Depth is: "+d4);
                    break;
                case 5:
                    int max=tree.BSTMax(tree.root);
                    System.out.println("Maximum node in a tree: "+max);
                    int d5=tree.depth(tree.root,max,0);
                    System.out.println("Maximum node Depth is: "+d5);
                    break;
                case 6:
                    System.out.print("Enter value to find  successor:");
                    int key3 = in.nextInt();
                    tree.Successor_Predecessor(tree.root, key3);
                    if(tree.successor==null){
                        System.out.println("no successor");
                    }
                    else {
                        System.out.print("successor node :");
                        System.out.println(tree.successor.data);
                        System.out.print("Successor node depth is:");
                        int d6 = tree.depth(tree.root, tree.successor.data, 0);
                        System.out.println(d6);
                    }
                    break;
                case 7:
                    System.out.print("Enter value to find predecessor:");
                    int key4 = in.nextInt();
                    tree.Successor_Predecessor(tree.root, key4);
                    if(tree.predecessor==null){
                        System.out.println("No predecessor");
                    }
                    else {
                        System.out.print("predecessor node is:");
                        System.out.println(tree.predecessor.data);
                        System.out.print("Predecessor node Depth is:");
                        int d7 = tree.depth(tree.root, tree.predecessor.data, 0);
                        System.out.println(d7);
                    }
                    break;
                case 8:
                    System.out.println("Inorder Traversal:");
                    tree.inorder(tree.root);
                    System.out.println();
                    System.out.println("Preorder Traversal:");
                    tree.preorder(tree.root);
                    System.out.println();
                    System.out.println("Postorder Traversal:");
                    tree.postorder(tree.root);
                    System.out.println();
                    break;
                case 9:
                    tree.root=null;
                    break;
                case 0:
                    ex=0;
                    break;
            }
        }while(ex!=0);
    }
}