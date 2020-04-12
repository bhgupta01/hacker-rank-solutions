public class MinHeap {

    private int[] weights;
    private int numberOfWeights;
    private int lastParentIndex;

    public MinHeap(int[] weights) {
        this.weights = weights;
        numberOfWeights = weights.length;
        lastParentIndex = calculateLastParentIndex();
        heapify();
    }

    public void push(int newWeight) {
        weights[numberOfWeights] = newWeight;
        numberOfWeights++;
        lastParentIndex = calculateLastParentIndex();
        heapify();
    }

    public boolean hasWeight() {
        return numberOfWeights > 0;
    }

    public int pop() {
        int topElement = weights[0];
        numberOfWeights--;
        weights[0] = weights[numberOfWeights];
        lastParentIndex = calculateLastParentIndex();
        heapify();

        return topElement;
    }

    private int calculateLastParentIndex() {
        return (numberOfWeights / 2) - 1;
    }

    private void heapify() {
        for (int i = lastParentIndex; i >= 0; i--) {
            int leftChildIndex = leftChildIndex(i);
            int rightChildIndex = leftChildIndex + 1;

            int parent = weights[i];
            int leftChild = -1;
            if (leftChildIndex < numberOfWeights)
                leftChild = weights[leftChildIndex];

            int rightChild = -1;
            if (rightChildIndex < numberOfWeights)
                rightChild = weights[rightChildIndex];

            if (leftChild > -1 && parent > leftChild) {
                if (rightChild > -1 && leftChild > rightChild) {
                    swap(i, rightChildIndex);
                } else {
                    swap(i, leftChildIndex);
                }
            } else if (rightChild > -1 && parent > rightChild) {
                swap(i, rightChildIndex);
            }
        }
    }

    private void reHeapify() {

    }

    private void swap(int i, int j) {
        int tmp = weights[i];
        weights[i] = weights[j];
        weights[j] = tmp;
    }

    private int leftChildIndex(int currentIndex) {
        return (2 * currentIndex) + 1;
    }
}
