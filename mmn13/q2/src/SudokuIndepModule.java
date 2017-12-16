public class SudokuIndepModule {
    // defines each independent module (row, col, block (square) size
    public final static int moduleSize = 9;
    // holds values of module
    private int[] values;

    /**
     * C'tor
     */
    public SudokuIndepModule() {
        this.values = new int[moduleSize];
    }

    /**
     * Checks validity of new cell's value in existing module
     * @param index index in module
     * @param value value to be inserted
     * @return
     */
    public boolean checkValidity(int index, int value) {
        if (value == 0)
            return true;
        for (int i = 0; i < moduleSize; i++) {
            if (i != index && (value > 0 && value <= moduleSize) && this.values[i] == value)
                return false;
        }
        return true;
    }

    /**
     * Updates new value to module
     * @param index index in module
     * @param value value to be inserted
     * @return
     */
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

    /**
     * Removes (puts 0)
     * @param index index in module
     */
    public void removeValue(int index) {
        if (index >=0 && index < moduleSize)
            this.values[index] = 0;
    }
}
