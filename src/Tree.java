import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;



public class Tree {
    private Integer root;
    private ArrayList<Tree> subtrees;

    public Tree() {
        this.root = null;
        this.subtrees = new ArrayList<>();
    }
    public Tree(Integer root) {
        this.root = root;
        this.subtrees = new ArrayList<>();
    }
    public Tree(Integer root, ArrayList<Tree> subtrees) {
        this.root = root;
        this.subtrees = subtrees;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public int size() {
        if (this.isEmpty()) {
            return 0;
        } else {
            int size = 1;
            for (Tree subtree: this.subtrees ) {
                size += subtree.size();
            }
            return size;
        }
    }

    public int count(Integer item) {
        if (this.isEmpty()) {
            return 0;
        } else {
            int num = 0;
            if (this.root.equals(item)) {
                num++;
            }
            for (Tree subtree: this.subtrees) {
                num += subtree.count(item);
            }
            return num;
        }
    }

    @Override
    public String toString() {
        return this.strIndented(0);
    }

    private String strIndented(int depth) {
        if (this.isEmpty()) {
            return "";
        } else {
            String s = "  ".repeat(depth) + this.root.toString() + "\n";
            for (Tree subtree: this.subtrees) {
                s = s.concat(subtree.strIndented(depth + 1));
            }
            return s;
        }
    }

    public double average() {
        if (this.isEmpty()) {
            return 0.0;
        } else {
            Integer[] values = this.averageHelper();
            /* Type cast to double required to ensure that we are doing
             * proper division. */
            return (double) values[0] / values[1];
        }
    }

    Integer[] averageHelper() {
        Integer[] values = {0, 0};
        if (!this.isEmpty()) {
            values[0] = this.root;
            values[1] = 1;
            for (Tree subtree : this.subtrees) {
                Integer[] subtreeVals = subtree.averageHelper();
                values[0] += subtreeVals[0];
                values[1] += subtreeVals[1];
            }
        }
        return values;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Tree otherTree)) {
            return false;
        } else if (this.isEmpty() && otherTree.isEmpty()) {
            return true;
        } else if (this.isEmpty() || otherTree.isEmpty()) {
            return false;
        } else if (!Objects.equals(this.root, otherTree.root)) {
            return false;
        } else if (this.subtrees.size() != otherTree.subtrees.size()) {
            return false;
        } else {
            return Objects.equals(this.subtrees, otherTree.subtrees);
        }
    }

    public boolean contains(Integer item) {
        if (this.isEmpty()) {
            return false;
        }

        if (Objects.equals(this.root, item)) {
            return true;
        } else {
            for (Tree subtree : this.subtrees) {
                if (subtree.contains(item)) {
                    return true;
                }
            }
            return false;
        }
    }

    public List<Integer> leaves() {
        List<Integer> leaves = new ArrayList<>();
        if (this.isEmpty()) {
            return leaves;
        } else if (this.subtrees.isEmpty()) {
            leaves.add(this.root);
            return leaves;
        } else {
            for (Tree subtree : this.subtrees) {
                leaves.addAll(subtree.leaves());
            }
            return leaves;
        }
    }

    public boolean deleteItem(Integer item) {
        if (this.isEmpty()) {
            return false;
        }
        if (this.root.equals(item)) {
            return true;
        }
        for (Tree subtree : this.subtrees) {
            boolean deleted = subtree.deleteItem(item);
            /* If deleting the item empties the subtree, then we need to remove
             * that subtree from our subtree list. */
            if (deleted && subtree.isEmpty()) {
                this.subtrees.remove(subtree);
                return true;
            } else if (deleted) {
                return true;
            }
        }
        /* If we didn't return in the loop, the item wasn't in any of the
         * subtrees. */
        return false;
    }

    private void deleteRoot() {
        if (this.subtrees.isEmpty()) {
            this.root = null;
        } else {
            Tree chosenSub = this.subtrees.get(-1);
            this.root = chosenSub.root;
            this.subtrees.addAll(chosenSub.subtrees);
        }
    }

    private Integer extractLeaf() {
        if (this.subtrees.isEmpty()) {
            Integer oldRoot = this.root;
            this.root = null;
            return oldRoot;
        }
        Integer leaf = this.subtrees.get(0).extractLeaf();
        /* Check if we emptied the subtree */
        if (this.subtrees.get(0).isEmpty()) {
            this.subtrees.remove(0);
        }
        return leaf;
    }

    public void insert(Integer item) {
        if (this.isEmpty()) {
            this.root = item;
        } else if (this.subtrees.isEmpty()) {
            this.subtrees.add(new Tree(item));
        } else {
            Random rand = new Random();
            if (rand.nextInt(3) == 2) {
                this.subtrees.add(new Tree(item));
            } else {
                int idx = rand.nextInt(this.subtrees.size());
                this.subtrees.get(idx).insert(item);
            }
        }
    }

    public boolean insertChild(Integer item, Integer parent) {
        if (this.isEmpty()) {
            return false;
        }
        if (this.root.equals(parent)) {
            this.subtrees.add(new Tree(item));
            return true;
        }
        for (Tree subtree : this.subtrees) {
            if (subtree.insertChild(item, parent)) {
                return true;
            }
        }
        return false;
    }
}
