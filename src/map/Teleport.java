package map;

public class Teleport {
    public int fromMap, fromCol, fromRow;
    public int toMap, toCol, toRow;

    public Teleport(int fromMap, int fromCol, int fromRow, int toMap, int toCol, int toRow) {
        this.fromMap = fromMap;
        this.fromCol = fromCol;
        this.fromRow = fromRow;
        this.toMap = toMap;
        this.toCol = toCol;
        this.toRow = toRow;
    }
}
