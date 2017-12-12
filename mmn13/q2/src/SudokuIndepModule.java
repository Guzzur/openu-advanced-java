public class SudokuIndepModule {
    public final static int moduleSize = 9;
    private int[] values;

    public SudokuIndepModule() {
        this.values = new int[9];
    }

    public boolean checkValidity(int index, int value) {
        for (int i = 0; i < moduleSize; i++) {
            if (i != index && (value > 0 && value <= moduleSize) && this.values[i] == value)
                return false;
        }
        return true;
    }

    public void updateValue(int index, int value) {
        if (index >=0 && index < moduleSize)
            this.values[index] = value;
    }

    public void removeValue(int index, int value) {
        if (index >=0 && index < moduleSize)
            this.values[index] = 0;
    }
}
