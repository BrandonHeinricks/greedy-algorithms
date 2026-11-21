package uni;

public class Clip {
    private int start;
    private int end;

    public Clip(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Clip[" + start + ", " + end + ")";
    }
}
