import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Search {
    private List<String> order;
    private Stack<Node> frontier;
    private int failingBranches;
    private ArrayList<String> solutions;
    private Constraints constraint;

    public Search(List<String> order){
        this.order = order;
        frontier = new Stack<Node>();
        solutions = new ArrayList<String>();
        failingBranches = 0;
        constraint = new Constraints();
        Node parent = new Node(null, 0, null, 0);

        // update next level of the search tree
        UpdateNextLevel(order.get(0), parent, 0);
        // update paths in frontier
        updateFrontier(parent);
        // pop the current node from the frontier
        Node current = frontier.pop();
        DFS(current, 1);
    }


    public int getFailingBranches(){
        return failingBranches;
    }
    public List<String> getSolutions(){
        return solutions;
    }


    private void DFS(Node current, int depth) {
        String indent = getIndent(depth);
        // check current node's constraint with its ancestors
        if (!containsViolation(current, current.getParent())) {
            // if all constraints is satisfied
            // if at the deepest level -> print the current solution path
            if (depth == order.size()) {
                System.out.print(indent + current.getLabel() + " = " + current.getValue() + " SOLUTION: ");
                String path = printTree(current, "");
                solutions.add(path);
            } else {
                // continue going through the frontier
                System.out.println(indent + current.getLabel() + " = " + current.getValue());
                UpdateNextLevel(order.get(depth), current, depth);
                updateFrontier(current);
            }
        } else {
            // constraint meets violation, stop and prune the path
            System.out.println(indent + current.getLabel() + " = " + current.getValue() + " FAILURE");
            failingBranches++;
        }
        // continue dfs further
        if (!frontier.isEmpty()) {
            current = frontier.pop();
            DFS(current, current.getDepth());
        }
    }

    private boolean containsViolation(Node current, Node parent) {
        while (parent.getParent() != null){
            if (constraint.isSatisfied(current, parent)){
                parent = parent.getParent();
            } else {
                return true;
            }
        }
        return false;
    }

    private void UpdateNextLevel(String s, Node parent, int depth) {
        for (int i = 0; i <= 3; i++) {
            Node node = new Node(s, i+1, parent, depth + 1);
            parent.setChildren(node, i);
        }
    }

    private void updateFrontier(Node parent) {
        for (int i = 3; i >= 0; i--){
            frontier.push(parent.getChildren(i));
        }
    }

    private String printTree(Node current, String path) {
        if (current.getParent() == null) {
            System.out.print("\n");
            return path;
        }
        String newPath = initializeNewPath(current);
        path = path + newPath;
        System.out.print(newPath);
        return printTree(current.getParent(), path);
    }

    private String initializeNewPath(Node current) {
        if (current.getParent() != null) {
            if (current.getParent().getParent() == null) {
                return current.getLabel() + " = " + current.getValue() + " ";
            } else {
                return current.getLabel() + " = " + current.getValue() + ", ";
            }
        }
        return "";
    }

    // indentation for the printed tree
    private static String getIndent(int depth) {
        switch (depth) {
            case 1:
                return "-";
            case 2:
                return "--";
            case 3:
                return "---";
            case 4:
                return "----";
            case 5:
                return "-----";
            case 6:
                return "------";
            case 7:
                return "-------";
            case 8:
                return "--------";
            default:
                return "";
        }
    }
}

