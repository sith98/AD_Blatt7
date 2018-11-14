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
        var leftSize = index - inStart;
        
        root.left = treeFromPreIn(preOrder, inOrder, preStart + 1, preStart + leftSize, inStart, index - 1);
        root.right = treeFromPreIn(preOrder, inOrder, preStart + leftSize + 1, preEnd, index + 1, inEnd);
        return root;
    }
    
    private static BasicNode<Integer> treeFromInPost(int[] inOrder, int[] postOrder, int inStart, int inEnd, int postStart, int postEnd) {
        if (postEnd < postStart) {
            return null;
        }
        
        var rootValue = postOrder[postEnd];
        var root = new BasicNode<>(rootValue);
        
        var index = indexOf(inOrder, rootValue, inStart, inEnd);
        var leftSize = index - inStart;
        
        root.left = treeFromInPost(inOrder, postOrder, inStart, index - 1, postStart, postStart + leftSize - 1);
        root.right = treeFromInPost(inOrder, postOrder, index + 1, inEnd, postStart + leftSize, postEnd - 1);
        return root;
    }
    
    private static BasicNode<Integer> treeFromPreIn(int[] preOrder, int[] inOrder) {
        return treeFromPreIn(preOrder, inOrder, 0, preOrder.length - 1, 0, inOrder.length - 1);
    }
    private static BasicNode<Integer> treeFromInPost(int[] inOrder, int[] postOrder) {
        return treeFromInPost(inOrder, postOrder, 0, inOrder.length - 1, 0, postOrder.length - 1);
    }
    
    private static BasicNode<Integer> insert(BasicNode<Integer> root, int value) {
        if (root == null) {
            return new BasicNode<>(value);
        }
        if (value < root.value) {
            root.left = insert(root.left, value);
        } else if (value > root.value) {
            root.right = insert(root.right, value);
        }
        return root;
    }
    
    private static BasicNode<Integer> searchTreeFromPre(int[] preOrder) {
        BasicNode<Integer> node = null;
        
        for (int value : preOrder) {
            node = insert(node, value);
        }
        return node;
    }
    
    public static void main(String[] args) {
        int[] preOrder = {6, 4, 2, 1, 3, 5, 9, 8, 10};
        int[] inOrder = {1, 2, 3, 4, 5, 6, 8, 9, 10};
        int[] postOrder = {1, 3, 2, 5, 4, 8, 10, 9, 6};
        
        var preNode = treeFromPreIn(preOrder, inOrder);
        var postNode = treeFromInPost(inOrder, postOrder);
        var searchNode = searchTreeFromPre(preOrder);
        
        System.out.println(BasicNode.toString(preNode));
        System.out.println(BasicNode.toString(postNode));
        System.out.println(BasicNode.toString(searchNode));
    }
}
