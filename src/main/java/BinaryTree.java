public class BinaryTree<K extends Comparable<K>, V> {

    private class Node {
        private int height = 1;
        private K key;
        private V value;
        private Node left = null, right = null;

        Node(Node pNode, K key, V element) {
            this.key = key;
            value = element;
        }

        Node(K key, V element) {
            this.key = key;
            value = element;
        }

        public V getValue() {
            return value;
        }

        public K getKey() {
            return key;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }

    private Node tree = null;

    BinaryTree() {
    }

    private Node recursiveFind(Node node, K key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (key.compareTo(node.key) < 0) {
            return recursiveFind(node.left, key);
        } else {
            return recursiveFind(node.right, key);
        }
    }

    public V find(K key) {
        Node res = recursiveFind(tree, key);
        return (res == null) ? null : res.value;
    }

    public V changeNode(K key, V value) {
        Node res = recursiveFind(tree, key);
        if (res == null) {
            insert(key, value);
            return null;
        }
        V oldValue = res.value;
        res.value = value;
        return oldValue;
    }


    private int getHeight(Node node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return getHeight(node.right) - getHeight(node.left);
    }

    private void updateHeight(Node node) {
        int leftHeight = getHeight(node.left), rightHeight = getHeight(node.right);
        node.height = (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
    }

    private Node rightRotate(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        updateHeight(node);
        updateHeight(left);
        return left;
    }

    private Node leftRotate(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        updateHeight(node);
        updateHeight(right);
        return right;
    }

    private Node balance(Node node) {
        updateHeight(node);
        if (getBalance(node) == 2) {
            if (getBalance(node.right) < 0) {
                node.right = rightRotate(node.right);
            }
            return leftRotate(node);
        }
        if (getBalance(node) == -2) {
            if (getBalance(node.left) > 0) {
                node.left = leftRotate(node.left);
            }
            return rightRotate(node);
        }
        return node;
    }

    private Node recursiveInsert(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }
        if (key.compareTo(node.key) <= 0) {
            node.left = recursiveInsert(node.left, key, value);
        } else {
            node.right = recursiveInsert(node.right, key, value);
        }
        return balance(node);
    }

    public void insert(K key, V value) {
        tree = recursiveInsert(tree, key, value);
    }

    private Node findMin(Node node) {
        return (node.left == null ? node : findMin(node.left));
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return balance(node);
    }

    private Node recursiveRemove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = recursiveRemove(node.left, key);
        } else {
            if (key.compareTo(node.key) > 0) {
                node.right = recursiveRemove(node.right, key);
            } else {
                Node left = node.left, right = node.right;
                node.right = node.left = null;
                if (right == null) {
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

    public V remove(K key) {
        Node rem = recursiveRemove(tree, key);
        return rem.value;
    }

    public static void main(String[] args) {
        BinaryTree<Integer, Double> tree = new BinaryTree<>();
        tree.insert(1, 1.1);
        tree.insert(2, 2.2);
        tree.insert(3, 3.3);
        tree.insert(4, 4.4);
        tree.insert(5, 5.5);
        for (int i = 1; i < 6; ++i) {
            System.out.println(i);
            tree.find(i);
        }

    }
}
