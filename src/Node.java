import java.util.ArrayList;
import java.util.List;

public class Node {
    private String label;
    private int value;
    private int depth;
    // 1 parent node
    private Node parent;

    // 4 children node
    private List<Node> children;

    public Node(String label, int value, Node parent, int depth) {
        this.label = label;
        this.parent = parent;
        this.value = value;
        this.depth = depth;

        children = new ArrayList<>();
        children.add(null);
        children.add(null);
        children.add(null);
        children.add(null);
    }

    public void setChildren(Node node, int index) {
        children.set(index, node);
    }

    public Node getChildren(int index) {
        return children.get(index);
    }
    public Node getParent() {
        return parent;
    }
    public int getDepth() {
        return depth;
    }
    public String getLabel() {
        return label;
    }
    public int getValue() {
        return value;
    }

}

