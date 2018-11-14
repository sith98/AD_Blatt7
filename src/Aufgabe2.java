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
            return delete(root.left, value);
        }
        if (comparison > 0) {
            return delete(root.right, value);
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
            return insert(root.left, value);
        } else if (comparison > 0) {
            return insert(root.right, value);
        } else {
            return root;
        }
    }
}


public class Aufgabe2 {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        
        int[] values = {6, 8, 7, 9, 3, 1};
    
        for (int value : values) {
            tree.insert(value);
        }
    
        System.out.println(tree);
    }
}