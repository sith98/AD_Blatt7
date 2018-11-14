class Node<T extends Comparable<T>> {
    T value;
    Node<T> left;
    Node<T> right;
    int height;
    
    Node(T value) {
        height = 0;
    }
    
    static int getHeight(Node<?> node) {
        return node == null ? -1 : node.height;
    }
    
    static void updateHeight(Node<?> node) {
        if (node != null) {
            node.height = Math.max(Node.getHeight(node.left), Node.getHeight(node.right));
        }
    }
    
}