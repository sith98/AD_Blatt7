import java.util.Arrays;

public class Aufgabe4 {
    private static void avlSort(int[] numbers) {
        var tree = new Tree<Integer>();
        
        for (int number : numbers) {
            tree.insert(number);
        }
        inOrderArraySet(tree.root, numbers, 0);
    }
    
    private static int inOrderArraySet(Node<Integer> node, int[] numbers, int index) {
        if (node == null) {
            return index;
        }
        
        int newIndex = inOrderArraySet(node.left, numbers, index);
        numbers[newIndex] = node.value;
        return inOrderArraySet(node.right, numbers, newIndex + 1);
    }
    
    public static void main(String[] args) {
        int[] numbers = {5, 6, 9, 12, 13, 3, 8, 10, 11, 16, 15, 14, 4, 2, 1};
        avlSort(numbers);
    
        System.out.println(Arrays.toString(numbers));
    }
}
