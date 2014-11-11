package samples;

public class Samples {
    
    static class Tree {
        
        private Node root;
        
        private static class Node {
            int value;
            Node left;
            Node right;
            
            public Node(int value) {
                this.value = value;
            }
        }
        
        void insert(int value) {
            if (root == null) {
                root = new Node(value);
                return;
            }
            insert(root, value);
        }
        
        void print() {
            if (root == null) {
                return;
            }
            print(root);
        }
        
        private void print(Node node) {
            if (node == null) {
                return;
            }
            System.out.println(node.value);
            print(node.left);
            print(node.right);
        }
        
        private void insert(Node node, int value) {
            if (value < node.value) {
                insertLeft(node, value);
            } else {
                insertRight(node, value);
            }
        }
        
        private void insertLeft(Node node, int value) {
            if (node.left == null) {
                node.left = new Node(value);
                return;
            }
            insert(node.left, value);
        }
        
        private void insertRight(Node node, int value) {
            if (node.right == null) {
                node.right = new Node(value);
                return;
            }
            insert(node.right, value);
        }
    }

    public static void main(String[] args) {
        
        Tree tree = new Tree();
        for (int i = 0; i < 100; i++) {
            tree.insert(i);
        }
        
        tree.print();
    }
    
}
