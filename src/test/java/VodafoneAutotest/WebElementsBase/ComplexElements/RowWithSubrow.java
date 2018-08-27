package VodafoneAutotest.WebElementsBase.ComplexElements;

import VodafoneAutotest.WebElementsBase.ElementBase;

public class RowWithSubrow<TRow extends ElementBase, TSubrow extends ElementBase> {
    public RowWithSubrow(TRow row) {
        Row = row;
        Subrow = null;
    }

    public RowWithSubrow(TRow row, TSubrow subrow) {
        Row = row;
        Subrow = subrow;
    }

    public TRow Row;

    public TSubrow Subrow;

    public void SetSubrow(TSubrow subrow) {
        Subrow = subrow;
    }

    public TSubrow GetSubrow() {
        return Subrow;
    }
}
