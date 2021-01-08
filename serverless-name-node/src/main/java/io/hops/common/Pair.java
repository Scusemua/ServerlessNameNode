package io.hops.common;

public class Pair<L, R> {

    private final L l;
    private final R r;

    public Pair(L l, R r) {
        this.l = l;
        this.r = r;
    }

    public L getL() {
        return l;
    }

    public R getR() {
        return r;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + (this.l != null ? this.l.hashCode() : 0);
        hash = 43 * hash + (this.r != null ? this.r.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair<L, R> other = (Pair<L, R>) obj;
        if (this.l != other.l && (this.l == null || !this.l.equals(other.l))) {
            return false;
        }
        if (this.r != other.r && (this.r == null || !this.r.equals(other.r))) {
            return false;
        }
        return true;
    }
}
