public class SudokuIndepModule {
    public final static int moduleSize = 9;
    private int[] values;

    public SudokuIndepModule() {
        this.values = new int[moduleSize];
    }

    public boolean checkValidity(int index, int value) {
        if (value == 0)
            return true;
        for (int i = 0; i < moduleSize; i++) {
            if (i != index && (value > 0 && value <= moduleSize) && this.values[i] == value)
                return false;
        }
        return true;
    }

    public boolean updateValue(int index, int value) {
        if (value < 0 || value > moduleSize)
            return false;
        if (index >=0 && index < moduleSize) {
            if (!this.checkValidity(index, value))
                return false;
            else {
                this.values[index] = value;
                return true;
            }
        }
        return false;
    }

    public void removeValue(int index, int value) {
        if (index >=0 && index < moduleSize)
            this.values[index] = 0;
    }
}
