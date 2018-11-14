public class Aufgabe1 {
    
    private static int indexOf(int[] array, int value, int from, int to) {
        for (int i = from; i <= to; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }
    
    private static BasicNode<Integer> treeFromPreIn(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart, int inEnd) {
        if (preEnd < preStart) {
            return null;
        }
        
        var rootValue = preOrder[preStart];
        var root = new BasicNode<>(rootValue);
        
        var index = indexOf(inOrder, rootValue, inStart, inEnd);
        var leftSize = inStart - index;
        
        root.left = treeFromPreIn(preOrder, inOrder, preStart + 1, preStart + leftSize, inStart, index - 1);
        root.right = treeFromPreIn(preOrder, inOrder, preStart + leftSize + 1, preEnd, index + 1, inEnd);
        return root;
    }
    
    private static BasicNode<Integer> treeFromPreIn(int[] preorder, int[] inorder) {
        return treeFromPreIn(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }
    
    public static void main(String[] args) {
    
    }
}
