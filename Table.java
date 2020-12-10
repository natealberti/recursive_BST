package Program_7;


public class Table {

    private TNode root;

    //constructor for an empty table
    public Table() {
        root = null;
    }

    public void insert(Keyed data) {
        //table is empty
        if(root==null)
            root = new TNode(data);
        else
            root = privateInsert(root, data);
    }

    private TNode privateInsert(TNode myRoot, Keyed data) {
        //empty spot is found in table for inserted data
        if(myRoot==null)
            return new TNode(data);

        int compare = data.compare(myRoot._data);
        if(compare < 0)
            myRoot.left = privateInsert(myRoot.left, data);
        else if(compare > 0)
            myRoot.right = privateInsert(myRoot.right, data);

        //data is not found in table / passing root back up the line
        return myRoot;
    }

    //check HW24
    public void delete(Keyed data) {
            root = privateDelete(root, null, data);
    }

    //not fully working
    private TNode privateDelete(TNode myRoot, TNode myParent, Keyed data) {
        if(myRoot==null)
            return myParent;

        int comp = data.compare(myRoot._data);

        if(comp < 0) {
            myRoot.left = privateDelete(myRoot.left, myRoot, data);
        }

        else if(comp > 0)
            myRoot.right = privateDelete(myRoot.right, myRoot, data);

        //object is found and deleted
        else if(comp==0) {
            //node has no children
            if(myRoot.left==null && myRoot.right==null)
                return null;
            //only has a right child
            else if(myRoot.left==null) {
                return myRoot.right;
            }

            //only has a left child
            else if(myRoot.right==null) {
                return myRoot.left;
            }

            //node has both children
            TNode minRight = minimum(myRoot.right);
            myRoot._data = minRight._data;
            minRight._data = null; //need to delete old instance of node, idk how :(
        }

        //data is not in table / passing root back up the line
        return myRoot;
    }

    //returns and takes "Keyed"
    public Keyed search(Keyed data) {
        return privateSearch(root, data);
    }

    private Keyed privateSearch(TNode myRoot, Keyed data) {
        if(myRoot==null)
            return null;
        int comp = data.compare(myRoot._data);
        if(comp < 0)
            return privateSearch(myRoot.left, data);
        else if(comp > 0)
            return privateSearch(myRoot.right, data);
        //data is found
        return myRoot._data;
    }

    public int getHeight() {
        return privateHeight(root);
    }

    private int privateHeight(TNode myRoot) {
        //if table is empty // if child DNE
        if(myRoot==null)
            return 0;
        //returns node height plus the max height of either child
        return 1 + Math.max(privateHeight(myRoot.left), privateHeight(myRoot.right));
    }

    public int getSize() {
        return privateGetSize(root);
    }

    private int privateGetSize(TNode myRoot) {
        if(myRoot==null)
            return 0;
        return 1 + privateGetSize(myRoot.left) + privateGetSize(myRoot.right);
    }

    //adds up total levels of all nodes in tree and divides it by the size of the tree
    public double getAvgLevel() {
        return (double) (getTotalLevels(root, 1))/getSize();
    }

    private int getTotalLevels(TNode myRoot, int level) {
        if(myRoot==null)
            return 0;
        return level + getTotalLevels(myRoot.left, level+1) + getTotalLevels(myRoot.right, level+1);
    }

    public void showTree() {
        privateShowTree(root, 0);
    }

    private void privateShowTree(TNode myRoot, int level) {
        if(myRoot==null) {
            return;
        }
        privateShowTree(myRoot.right, level+1);
        System.out.println();
        for(int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.print(myRoot._data.toStr());
        privateShowTree(myRoot.left, level+1);
    }

    public String toString() {
        privateToString(root);
        return null;
    }

    private void privateToString(TNode myRoot) {
        if(myRoot==null)
            return;
        privateToString(myRoot.left);
        System.out.println(myRoot._data.toStr());
        privateToString(myRoot.right);
    }

    //returns lowest node in table
    private TNode minimum(TNode myRoot) {
        if(myRoot.left==null)
            return myRoot;
        return minimum(myRoot.left);
    }

    //returns highest node in table
    private TNode maximum(TNode myRoot) {
        if(myRoot.right==null)
            return myRoot;
        return maximum(myRoot.right);
    }

}
