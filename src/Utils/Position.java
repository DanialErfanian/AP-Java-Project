package Utils;

import Logic.Targetable;
import org.jetbrains.annotations.NotNull;

public class Position implements java.io.Serializable, Targetable {
    public static int getDistance(@NotNull Position a, @NotNull Position b) {
        return Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position() {
        this.x = 0;
        this.y = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position position = (Position) obj;
            return (this.x == position.x && this.y == position.y);
        }
        return false;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
