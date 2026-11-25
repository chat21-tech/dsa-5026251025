package lw06.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        String firstLine = read.nextLine();
        String[] nums = firstLine.split(" ");
        
        BinarySearchTree root = new BinarySearchTree();

        for (String str : nums) {
            int num = Integer.parseInt(str);
            root.insert(num);
        }

        int target = read.nextInt();
        read.close();

        System.out.print("Preorder      : ");
        root.preOrder();

        System.out.print("Inorder       : ");
        root.inOrder();

        System.out.print("Postorder     : ");
        root.postOrder();

        System.out.println("Search " + target + "     : " + root.search(target));
        System.out.println("Nodes         : " + root.countNodes());
        System.out.println("Leaves        : " + root.countLeaves());

        read.close();
    }
}
