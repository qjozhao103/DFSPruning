import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Constraints {

    private Map<String, String[]> constraints;

    public Constraints() {
        constraints = new HashMap<>();
        initializeMap();
    }

    // Initialize a map with Node being the key and Node's constraint neighbours being its values
    private void initializeMap() {
        String[] As = {"G", "H"};
        String[] Bs = {"F"};
        String[] Cs = {"D", "E", "F", "G", "H"};
        String[] Ds = {"C", "E", "F", "G", "H"};
        String[] Es = {"C", "D", "F", "H"};
        String[] Fs = {"B", "C", "D", "E", "G", "H"};
        String[] Gs = {"A", "C", "D", "F", "H"};
        String[] Hs = {"A", "C", "D", "E", "F", "G"};

        constraints.put("A", As);
        constraints.put("B", Bs);
        constraints.put("C", Cs);
        constraints.put("D", Ds);
        constraints.put("E", Es);
        constraints.put("F", Fs);
        constraints.put("G", Gs);
        constraints.put("H", Hs);
    }


    // check if the current node and its ancestor node satisfy the constraint with given value
    public boolean isSatisfied(Node current, Node parent) {
        String parentName = parent.getLabel();
        if (parent.getParent() == null) return true;
        String[] neighbours = constraints.get(current.getLabel());
        int val1 = current.getValue();
        int val2 = parent.getValue();

        if (Arrays.asList(neighbours).contains(parentName)) {
            switch (current.getLabel()) {
                case "A":
                    return checkA(parentName, val1, val2);
                case "B":
                    return BF(val1, val2);
                case "C":
                    return checkC(parentName, val1, val2);
                case "D":
                    return checkD(parentName, val1, val2);
                case "E":
                    return checkE(parentName, val1, val2);
                case "F":
                    return checkF(parentName, val1, val2);
                case "G":
                    return checkG(parentName, val1, val2);
                case "H":
                    return checkH(parentName, val1, val2);
            }
        } else {
            isSatisfied(current, parent.getParent());
        }
        return true;
    }

    /* Constraints defined for nodes*/
    private boolean checkA(String parentName, int val1, int val2) {
        if (parentName.equals("G")) {
            return AG(val1, val2);
        } else if (parentName.equals("H")) {
            return AH(val1, val2);
        }
        return true;
    }

    private boolean checkC(String parentName, int val1, int val2) {
        switch (parentName) {
            case "D":
                return CD(val1, val2);
            case "E":
                return CE(val1, val2);
            case "F":
                return CF(val1, val2);
            case "G":
                return CG(val1, val2);
            case "H":
                return CH(val1, val2);
        }
        return true;
    }

    private boolean checkD(String parentName, int val1, int val2) {
        switch (parentName) {
            case "C":
                return CD(val1, val2);
            case "E":
                return DE(val1, val2);
            case "F":
                return DF(val1, val2);
            case "G":
                return DG(val1, val2);
            case "H":
                return DH(val1, val2);
        }
        return true;
    }

    private boolean checkE(String parentName, int val1, int val2) {
        switch (parentName) {
            case "C":
                return CE(val1, val2);
            case "D":
                return ED(val1, val2);
            case "F":
                return EF(val1, val2);
            case "H":
                return EH(val1, val2);
        }
        return true;
    }

    private boolean checkF(String parentName, int val1, int val2) {
        switch (parentName) {
            case "B":
                return BF(val1, val2);
            case "C":
                return CF(val1, val2);
            case "D":
                return DF(val1, val2);
            case "E":
                return EF(val1, val2);
            case "G":
                return FG(val1, val2);
            case "H":
                return FH(val1, val2);
        }
        return true;
    }

    private boolean checkG(String parentName, int val1, int val2) {
        switch (parentName) {
            case "A":
                return GA(val1, val2);
            case "C":
                return CG(val1, val2);
            case "D":
                return GD(val1, val2);
            case "F":
                return FG(val1, val2);
            case "H":
                return GH(val1, val2);
        }
        return true;
    }

    private boolean checkH(String parentName, int val1, int val2) {
        switch (parentName) {
            case "A":
                return HA(val1, val2);
            case "C":
                return CH(val1, val2);
            case "D":
                return DH(val1, val2);
            case "E":
                return HE(val1, val2);
            case "F":
                return FH(val1, val2);
            case "G":
                return HG(val1, val2);
        }
        return true;
    }


    // A >= G
    public boolean AG(int valA, int valG) {
        return (valA >= valG);
    }
    // A < H
    public boolean AH(int valA, int valH) {
        return (valA < valH);
    }
    // H > A
    private boolean HA(int valH, int valA) {
        return (valA < valH);
    }
    // |F-B| = 1
    public boolean BF(int valB, int valF) {
        int diff = valF - valB;
        return ((diff == 1) || (diff == -1));
    }
    // G < D
    private boolean GD(int valG, int valD) {
        return (valD >= valG);
    }
    // H > G
    private boolean HG(int valH, int valG) {
        return (valG < valH);
    }
    // E ! H-2
    private boolean HE(int valH, int valE) {
        int sub = valH - 2;
        return (valE != sub);
    }
    // G <= A
    private boolean GA(int valG, int valA) {
        return (valA >= valG);
    }
    // |C-H| is even
    private boolean CH(int valC, int valH) {
        int diff = valH - valC;
        return (diff % 2 == 0);
    }
    // |G-C| = 1
    private boolean CG(int valC, int valG) {
        int diff = valG - valC;
        return ((diff == 1) || (diff == -1));
    }
    // C != F
    private boolean CF(int valC, int valF) {
        return (valF != valC);
    }
    // C != E
    private boolean CE(int valC, int valE) {
        return (valE != valC);
    }
    // C != D
    private boolean CD(int valC, int valD) {
        return (valD != valC);
    }
    // D != H
    private boolean DH(int valD, int valH) {
        return (valH != valD);
    }
    // D >= G
    private boolean DG(int valD, int valG) {
        return (valD >= valG);
    }
    // D != F
    private boolean DF(int valD, int valF) {
        return (valF != valD);
    }
    // E < D -1
    private boolean DE(int valD, int valE) {
        int sub = valD - 1;
        return (valE < sub);
    }
    // E < D -1
    private boolean ED(int valE, int valD) {
        int sub = valD - 1;
        return (valE < sub);
    }
    // E != H - 2
    private boolean EH(int valE, int valH) {
        int sub = valH - 2;
        return (valE != sub);
    }
    // |E-F| is odd
    private boolean EF(int valE, int valF) {
        int diff = valE - valF;
        return ((diff % 2 == 1) || (diff % 2 == -1));
    }
    // H != F
    private boolean FH(int valF, int valH) {
        return (valF != valH);
    }
    // F != G
    private boolean FG(int valF, int valG) {
        return (valF != valG);
    }
    // G < H
    private boolean GH(int valG, int valH) {
        return (valG < valH);
    }
}

