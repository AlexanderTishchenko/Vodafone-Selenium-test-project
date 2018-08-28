package autotest.elements.complex;

import autotest.elements.ElementBase;

public class RowWithSubrow<TRow extends ElementBase, TSubrow extends ElementBase> {
    public RowWithSubrow(TRow row) {
        this.row = row;
        subrow = null;
    }

    public RowWithSubrow(TRow row, TSubrow subrow) {
        this.row = row;
        this.subrow = subrow;
    }

    public TRow row;

    public TSubrow subrow;

    public void setSubrow(TSubrow subrow) {
        this.subrow = subrow;
    }
}
