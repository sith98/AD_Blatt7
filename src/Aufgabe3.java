import java.util.ArrayList;
import java.util.List;

public class Aufgabe3 {
    private static int numMinAvlTrees(int height) {
        if (height == 0) {
            return 1;
        }
        if (height == 1) {
            return 2;
        }
        return numMinAvlTrees(height - 1) * numMinAvlTrees(height - 2) * 2;
    }


    private static List<String> inOrderMinAvlTrees(int height) {
        if (height == 0) {
            return List.of("(n,0,n)");
        }
        if (height == 1) {
            return List.of("(n,0,(n,0,n))", "((n,0,n),0,n)");
        }
        var treesMinus1 = inOrderMinAvlTrees(height - 1);
        var treesMinus2 = inOrderMinAvlTrees(height - 2);

        List<String> trees = new ArrayList<>();
        for (String treeMinus1 : treesMinus1) {
            for (String treeMinus2 : treesMinus2) {
                trees.add("(" + treeMinus1 + ",0," + treeMinus2 + ')');
                trees.add("(" + treeMinus2 + ",0," + treeMinus1 + ')');
            }
        }
        return trees;
    }

    public static void main(String[] args) {
        System.out.println(numMinAvlTrees(5));

        var trees = inOrderMinAvlTrees(3);
        trees.forEach(System.out::println);
    }
}