package binarytree;

public class ITreeNode
{

	public int data;
	public ITreeNode left;
	public ITreeNode right;
	
	public ITreeNode(int data, ITreeNode left, ITreeNode right){
		this.data=data;
		this.left=left;
		this.right=right;	
	}
	public ITreeNode(int data){
		this.data=data;
	}
	
}
