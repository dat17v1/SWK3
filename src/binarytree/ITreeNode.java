package binarytree;

public class ITreeNode
{
    protected ITreeNode right;
    protected ITreeNode left;
    protected int data;

    public ITreeNode(ITreeNode left, ITreeNode right, int data) {
        this.right = right;
        this.left = left;
        this.data = data;
    }

    public ITreeNode(int data) {
        this.data = data;
    }

}
