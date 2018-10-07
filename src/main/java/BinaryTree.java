public class BinaryTree<T> {

    class Node{
        private int key, height = 1;
        private T value;
        private Node left = null, right = null;
        Node(Node pNode, int key, T element){
            this.key = key;
            value = element;
        }
        Node(int key, T element){
            this.key = key;
            value = element;
        }

        public T getValue(){
            return value;
        }

        public int getKey() {
            return key;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    private Node tree = null;
    BinaryTree(){
    }

    Node recursiveFind(Node node, int key){
        if (node == null || node.key == key){
            return node;
        }
        if (key < node.key){
            return recursiveFind(node.left, key);
        }else{
            return recursiveFind(node.right, key);
        }
    }

    public T find(int key){
        Node res = recursiveFind(tree, key);
        return res.value;
    }


    private int getHeight(Node node){
        return (node == null)? 0 : node.height;
    }

    private int getBalance(Node node){
        return getHeight(node.right) - getHeight(node.left);
    }

    private void updateHeight(Node node){
        int leftHeight = getHeight(node.left), rightHeight = getHeight(node.right);
        node.height = (leftHeight > rightHeight? leftHeight : rightHeight) + 1;
    }

    private Node rightRotate(Node node){
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        updateHeight(node);
        updateHeight(left);
        return left;
    }

    private Node leftRotate(Node node){
        Node right =  node.right;
        node.right = right.left;
        right.left = node;
        updateHeight(node);
        updateHeight(right);
        return right;
    }

    private Node balance(Node node){
        updateHeight(node);
        if (getBalance(node) == 2){
            if (getBalance(node.right) < 0){
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }
        if (getBalance(node) == -2){
            if (getBalance(node.left) > 0){
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        }
        return node;
    }

    private Node recursiveInsert(Node node, int key, T value){
        if (node == null){
            return new Node(key, value);
        }
        if (key <= node.key){
            node.left = recursiveInsert(node.left, key, value);
        }else{
            node.right = recursiveInsert(node.right, key, value);
        }
        return balance(node);
    }

    public void insert(int key, T value){
        tree = recursiveInsert(tree, key, value);
    }

    private Node findMin(Node node){
        return (node.left == null? node : findMin(node.left));
    }

    private Node removeMin(Node node){
        if (node.left == null){
            return node.right;
        }
        node.left = removeMin(node.left);
        return balance(node);
    }

    private Node recursiveRemove(Node node, int key){
        if (node == null){
            return null;
        }
        if (key < node.key){
            node.left = recursiveRemove(node.left, key);
        }else{
            if (key > node.key){
                node.right = recursiveRemove(node.right, key);
            }else{
                Node left = node.left, right = node.right;
                node.right = node.left = null;
                if (right == null){
                    return left;
                }
                Node min = findMin(right);
                min.right = removeMin(right);
                min.left = left;
                return balance(min);
            }
        }
        return balance(node);
    }

    public T remove(int key){
        Node rem = recursiveRemove(tree, key);
        return rem.value;
    }

    public static void main(String[] args){
        BinaryTree<Double> tree = new BinaryTree<>();
        tree.insert(1, 1.1);
        tree.insert(2, 2.2);
        tree.insert(3, 3.3);
        tree.insert(4, 4.4);
        tree.insert(5, 5.5);
        for (int i = 1; i < 6; ++i){
            tree.find(i);
        }
    }
}
