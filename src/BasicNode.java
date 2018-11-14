public class BasicNode<T> {
    T value;
    BasicNode<T> left;
    BasicNode<T> right;
    
    public BasicNode(T value) {
        this.value = value;
    }
    
    private static <T> String toString(BasicNode<T> node) {
        if (node == null) {
            return "n";
        } else {
            return "(" + toString(node.left) + "," + node.value + "," + toString(node.right) + ")";
        }
    }
}
