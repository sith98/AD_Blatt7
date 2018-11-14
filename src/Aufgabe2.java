class Tree<T extends Comparable<T>> {
    Node<T> root;
    
    Tree() {
        root = null;
    }
    
    void insert(T value) {
        root = insert(root, value);
    }
    
    void delete(T value) {
        root = delete(root, value);
    }
    
    @Override
    public String toString() {
        var builder = new StringBuilder();
        print(root, builder);
        return builder.toString();
    }
    
    
    private static <T extends Comparable<T>> void print(Node<T> node, StringBuilder builder) {
        if (node == null) {
            builder.append('n');
        } else {
            builder.append('(');
            print(node.left, builder);
            builder.append(',');
            builder.append(node.value);
            builder.append(',');
            print(node.right, builder);
            builder.append(')');
        }
    }
    
    private static <T extends Comparable<T>> Node<T> delete(Node<T> root, T value) {
        if (root == null) {
            return null;
        }
        int comparison = value.compareTo(root.value);
        if (comparison < 0) {
            root.left = delete(root.left, value);
            root = checkRotateLeft(root);
            return root;
        }
        if (comparison > 0) {
            root.right = delete(root.right, value);
            root = checkRotateRight(root);
            return root;
        }
        
        // found value
        if (root.left == null && root.right == null) {
            return null;
        }
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        
        T min = getMin(root.right);
        root.value = min;
        
        
        root.right = delete(root.right, min);
        root = checkRotateRight(root);
        
        return root;
    }
    
    
    private static <T extends Comparable<T>> T getMin(Node<T> root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root.value;
        }
        return getMin(root.left);
    }
    
    private static <T extends Comparable<T>> Node<T> insert(Node<T> root, T value) {
        if (root == null) {
            return new Node<>(value);
        }
        int comparison = value.compareTo(root.value);
        if (comparison < 0) {
            root.left = insert(root.left, value);
            root = checkRotateRight(root);
        } else if (comparison > 0) {
            root.right = insert(root.right, value);
            root = checkRotateLeft(root);
        }
        return root;
    }
    
    private static <T extends Comparable<T>> Node<T> checkRotateRight(Node<T> node) {
        if (node == null || node.left == null || Node.getHeight(node.left) - Node.getHeight(node.right) < 2) {
            Node.updateHeight(node);
            return node;
        }
        if (Node.getHeight(node.left.right) > Node.getHeight(node.left.left)) {
            node.left = rotateLeft(node.left);
        }
        return rotateRight(node);
    }
    private static <T extends Comparable<T>> Node<T> checkRotateLeft(Node<T> node) {
        if (node == null || node.right == null || Node.getHeight(node.right) - Node.getHeight(node.left) < 2) {
            Node.updateHeight(node);
            return node;
        }
        if (Node.getHeight(node.right.left) > Node.getHeight(node.right.right)) {
            node.right = rotateRight(node.right);
        }
        return rotateLeft(node);
    }
    
    private static <T extends Comparable<T>> Node<T> rotateRight(Node<T> node) {
        var newNode = node.left;
        var temp = newNode.right;
        newNode.right = node;
        node.left = temp;
        return newNode;
    }
    private static <T extends Comparable<T>> Node<T> rotateLeft(Node<T> node) {
        var newNode = node.right;
        var temp = newNode.left;
        newNode.left = node;
        node.right = temp;
        return newNode;
    }
}


public class Aufgabe2 {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        
        int[] values = {5, 6, 9, 12, 13, 3, 8, 10, 11, 16, 15, 14, 4, 2, 1};
        int[] valuesToDelete = {12, 8, 5, 4, 3, 6, 15, 14};
        
        for (int value : values) {
            tree.insert(value);
            System.out.println(tree);
        }
        
        for (int value : valuesToDelete) {
            tree.delete(value);
            System.out.println(tree);
        }
    
    }
}