package binarytree;

import java.util.*;

// recursive delete: https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
public class ITree
{
	
	private ITreeNode root;
	
	public ITree(ITreeNode node){
		
		root =node;
	}

	
	public boolean contains(int value){
		return contains(root, value);
	}
	
	public void add(int value){
		
		root = add(root,value);
	}
	private ITreeNode add(ITreeNode node, int value){
		if(node==null){ // Base Case: opret ny node
			node=new ITreeNode(value);
		}else if(value < node.data){//gå til venstre
			node.left=add(node.left,value);
		}else { // gå til højre
			node.right=add(node.right,value);
		}
		return node;
	}

	protected boolean delete(int value){
// Locate the node to be deleted and also locate its parent node
		 ITreeNode parent = null;
		ITreeNode current = root;
			while (current != null) {
				if (value < current.data) {
					parent = current;
					current = current.left;
				} else if (value > current.data) {
					parent = current;
					current = current.right;
				} else
					break; // Element is in the tree pointed at by current
			}
				if (current == null)
					return false; // Element is not in the tree
				// Case 1: current has no left child
				 if (current.left == null) {
				 // Connect the parent with the right child of the current node
					 if (parent == null) {
						root = current.right;
					 }
					 else {
					 if (value < parent.data)
						parent.left = current.right;
					 else
						parent.right = current.right;
					 }
				 }
				 else {
				 // Case 2: The current node has a left child
				 // Locate the rightmost node in the left subtree of
				 // the current node and also its parent.
				 ITreeNode parentOfRightMost = current;
				 ITreeNode rightMost = current.left;

				 while (rightMost.right != null) {
					 parentOfRightMost = rightMost;
					rightMost = rightMost.right; // Keep going to the right
				 }

				 // Replace the element in current by the element in rightMost
				 current.data = rightMost.data;

				 // Eliminate rightmost node
				 if (parentOfRightMost.right == rightMost)
					 parentOfRightMost.right = rightMost.left;
				 else
					 // Special case: parentOfRightMost == current
					 parentOfRightMost.left = rightMost.left;
				 }

		return true; // Element deleted successfully
	}



	protected void printInorder(){
		printInorder(root);
	}
	private void printInorder(ITreeNode node){
		if(node.left != null){
			printInorder(node.left);
		}
		if(node != null){
			System.out.println(node.data);
		}
		if(node.right != null){
			printInorder(node.right);
		}
	}

	protected void printPreorder(){
		printPreorder(root);
	}
	private void printPreorder(ITreeNode node){
		if(node != null){
			System.out.println(node.data);
		}
		if(node.left != null){
			printPreorder(node.left);
		}
		if(node.right != null){
			printPreorder(node.right);
		}
	}

	protected void printPostorder(){
		printPostorder(root);
	}
	private void printPostorder(ITreeNode node){

		if(node.left != null){
			printPostorder(node.left);
		}
		if(node.right != null){
			printPostorder(node.right);
		}
		if(node != null){
			System.out.println(node.data);
		}
	}


	
	private boolean contains(ITreeNode node, int value){
		//udfyld denne så den returnerer true hvis value findes i træet
		if(node==null){
			return false;
		}else if(node.data==value){
			return true; //nu er den fundet!!
		}else if(node.data > value){  //hvis nodens data er større end søge værdien, gå til venstre
			return contains(node.left, value);
		} else{
			return contains(node.right, value);
		}
	}

	public void printLevelWise() {
		List<List<ITreeNode>> levels = traverseLevels(root);

		for (List<ITreeNode> level : levels) {
			for (ITreeNode node : level) {
				System.out.print(node.data + " ");
			}
			System.out.println();
		}
	}

	private List<List<ITreeNode>> traverseLevels(ITreeNode root) {
		if (root == null) {
			return Collections.emptyList();
		}
		List<List<ITreeNode>> levels = new LinkedList<>();

		Queue<ITreeNode> nodes = new LinkedList<>();
		nodes.add(root);

		while (!nodes.isEmpty()) {
			List<ITreeNode> level = new ArrayList<>(nodes.size());
			levels.add(level);

			for (ITreeNode node : new ArrayList<>(nodes)) {
				level.add(node);
				if (node.left != null) {
					nodes.add(node.left);
				}
				if (node.right != null) {
					nodes.add(node.right);
				}
				nodes.poll();
			}
		}
		return levels;
	}
	
	public static void main(String[] args){
		ITreeNode n1=new ITreeNode(55);
		ITreeNode n2=new ITreeNode(100);
		ITreeNode n3=new ITreeNode(60, n1, n2);
		
		ITree theTree=new ITree(n3);
		theTree.add(45);
		theTree.add(57);
		theTree.add(59);
		theTree.add(67);
		theTree.add(107);
		theTree.add(101);
		//theTree.print();
		//theTree.printPreorder(); // Print "Book table of contents"
		//theTree.printPostorder(); // Print files and directory. First count leafs, then display directory...
		//theTree.printInorder(); // same as Depth-first search. Prints items in sorted order.
		theTree.printLevelWise();
		System.out.println("--------");
		theTree.delete(60);
		theTree.printLevelWise();
		//theTree.printInorder(); // same as Depth-first search. Prints items in sorted order.
//		if(theTree.contains(23)){
//			System.out.println("fundet!");
//		}else{
//			System.out.println("ikke fundet!");
//		}
	}
}
