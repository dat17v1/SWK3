package binarytree;


import java.util.*;

// recursive delete: https://www.geeksforgeeks.org/binary-search-tree-set-2-delete/
public class ITree
{
	
	private ITreeNode root;
	
	public ITree(ITreeNode node){
		root = node;
	}

	public ITree(){

	}
	
	public void add(int value){
			root = add(root,value);
	}


	private ITreeNode add(ITreeNode node, int value){ // value=55
		// 1. case: Tomt træ
		if(node == null){
			node = new ITreeNode(value);
		}else if(value < node.data){ // 2. rekursiv case
			node.left = add(node.left,value);
		}else if(value > node.data){
			node.right = add(node.right, value);
		}
		return node;
	}

//	protected boolean delete(int value){
//// Locate the node to be deleted and also locate its parent node
//		 ITreeNode parent = null;
//		ITreeNode current = root;
//			while (current != null) {
//				if (value < current.data) {
//					parent = current;
//					current = current.left;
//				} else if (value > current.data) {
//					parent = current;
//					current = current.right;
//				} else
//					break; // Element is in the tree pointed at by current
//			}
//				if (current == null)
//					return false; // Element is not in the tree
//				// Case 1: current has no left child
//				 if (current.left == null) {
//				 // Connect the parent with the right child of the current node
//					 if (parent == null) {
//						root = current.right;
//					 }
//					 else {
//					 if (value < parent.data)
//						parent.left = current.right;
//					 else
//						parent.right = current.right;
//					 }
//				 }
//				 else {
//				 // Case 2: The current node has a left child
//				 // Locate the rightmost node in the left subtree of
//				 // the current node and also its parent.
//				 ITreeNode parentOfRightMost = current;
//				 ITreeNode rightMost = current.left;
//
//				 while (rightMost.right != null) {
//					 parentOfRightMost = rightMost;
//					rightMost = rightMost.right; // Keep going to the right
//				 }
//
//				 // Replace the element in current by the element in rightMost
//				 current.data = rightMost.data;
//
//				 // Eliminate rightmost node
//				 if (parentOfRightMost.right == rightMost)
//					 parentOfRightMost.right = rightMost.left;
//				 else
//					 // Special case: parentOfRightMost == current
//					 parentOfRightMost.left = rightMost.left;
//				 }
//
//		return true; // Element deleted successfully
//	}





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
		ITreeNode n3=new ITreeNode(n1, n2, 60);

		ITree theTree=new ITree(n3);
		theTree.add(45);
		theTree.add(57);
		theTree.add(59);
		theTree.add(67);
		theTree.add(107);
		theTree.add(101);
		theTree.printLevelWise();

	}
}
