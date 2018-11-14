class Node<T extends Comparable<T>> {
    T value;
    Node<T> left;
    Node<T> right;
    private int height;
    
    Node(T value) {
        this.value = value;
        height = 0;
    }
    
    static int getHeight(Node<?> node) {
        return node == null ? -1 : node.height;
    }
    
    static void updateHeight(Node<?> node) {
        if (node != null) {
            node.height = 1 + Math.max(Node.getHeight(node.left), Node.getHeight(node.right));
        }
    }
    
    static <T extends Comparable<T>> String toString(Node<T> node) {
        if (node == null) {
            return "n";
        } else {
            return "(" + toString(node.left) + "," + node.value + "," + toString(node.right) + ")";
        }
    }
    
}
