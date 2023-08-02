public class BinatyTree {

    private Node root;
    private class Node {
        int value;
        Node parent;
        Node left;
        Node right;
        boolean isRed;

        public Node(int value) {
            this.value = value;
            this.isRed = true;
        }
    }

    public void insert(int value) {
        Node newNode = new Node(value);
        root = insertNode(root, newNode);
        fixRedBlackTree(newNode);
    }

    private Node insertNode(Node root, Node newNode) {
        if (root == null) {
            return newNode;
        }
        if (newNode.value < root.value) {
            root.left = insertNode(root.left, newNode);
            root.left.parent = root;
        }
        if (newNode.value > root.value) {
            root.right = insertNode(root.right, newNode);
            root.right.parent = root;
        }
        return root;
    }

    private void fixRedBlackTree(Node newNode) {
        while (newNode != root && newNode.parent.isRed) {
            if (newNode.parent == newNode.parent.parent.left) {
                Node uncle = newNode.parent.parent.right;
                if (uncle != null && uncle.isRed) {
                    newNode.parent.isRed = false;
                    uncle.isRed = false;
                    newNode.parent.parent.isRed = true;
                    newNode = newNode.parent.parent;
                } else {
                    if (newNode == newNode.parent.right) {
                        newNode = newNode.parent;
                        leftRotate(newNode);
                    }
                    newNode.parent.isRed = false;
                    newNode.parent.parent.isRed = true;
                    rightRotate(newNode.parent.parent);
                }
            } else {
                Node uncle = newNode.parent.parent.left;
                if (uncle != null && uncle.isRed) {
                    newNode.parent.isRed = false;
                    uncle.isRed = false;
                    newNode.parent.parent.isRed = true;
                    newNode = newNode.parent.parent;
                } else {
                    if (newNode == newNode.parent.left) {
                        newNode = newNode.parent;
                        rightRotate(newNode);
                    }
                    newNode.parent.isRed = false;
                    newNode.parent.parent.isRed = true;
                    leftRotate(newNode.parent.parent);
                }
            }
        }
        root.isRed = false;
    }

    private void leftRotate(Node root) {
        Node rightChild = root.right;
        root.right = rightChild.left;
        if (root.right != null) {
            root.right.parent = root;
        }
        rightChild.parent = root.parent;
        if (root.parent == null) {
            this.root = rightChild;
        } else if (root == root.parent.left) {
            root.parent.left = rightChild;
        } else {
            root.parent.right = rightChild;
        }
        rightChild.left = root;
        root.parent = rightChild;
    }

    private void rightRotate(Node root) {
        System.out.println(root.value + " rotate right");
        Node leftChild = root.left;
        root.left = leftChild.right;
        if (root.left != null) {
            root.left.parent = root;
        }
        leftChild.parent = root.parent;
        if (root.parent == null) {
            this.root = leftChild;
        } else if (root == root.parent.left) {
            root.parent.left = leftChild;
        } else {
            root.parent.right = leftChild;
        }
        leftChild.right = root;
        root.parent = leftChild;
    }

    public void printTree() {
        printTree(root);
    }

    private void printTree(Node root) {
        if (root == null) {
            return;
        }
        printTree(root.left);
        if (root.isRed == true) {
            System.out.print("\u001B[31m" + root.value + " ");
        } else {
            System.out.print("\u001B[30m" + root.value + " ");
        }
        printTree(root.right);
    }

    public static void main(String[] args) {
        BinatyTree redBlackTree = new BinatyTree();
        redBlackTree.insert(10);
        redBlackTree.insert(5);
        redBlackTree.insert(15);
        redBlackTree.insert(3);
        redBlackTree.insert(7);
        redBlackTree.insert(8);
        redBlackTree.insert(20);
        redBlackTree.insert(12);
        redBlackTree.insert(0);
        redBlackTree.insert(11);
        redBlackTree.insert(145);
        redBlackTree.insert(3657);
        redBlackTree.insert(73);
        redBlackTree.insert(87);
        redBlackTree.insert(280);
        redBlackTree.insert(1452);
        redBlackTree.printTree();
    }
}
