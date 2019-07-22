import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class Sensor {

  private int lower;
  private int higher;
  private int[] buffer;

  Sensor(int lower, int higher, int[] buffer) {
    this.lower = lower;
    this.higher = higher;
    this.buffer = buffer;

  }

  public int[] getOutput() {
    int[] output = Arrays
            .stream(this.buffer)
            .filter(num -> num <= this.higher && num >= this.lower)
            .toArray();
    Arrays.sort(output);
    return output;
  }

  public String toString() {
    return Arrays
            .toString(this.getOutput())
            .replaceAll("[\\[\\]\\s]","");
  }

  public double findMean() {
    int sum = IntStream.of(this.getOutput()).sum();
    return sum / (float) this.getOutput().length;
  }

  public double findMedian() {
    int[] arr = this.getOutput();
    int length = arr.length;
    if (length % 2 == 0) {
      int index1 = length / 2;
      int index2 = index1 - 1;
      return (arr[index1] + arr[index2]) / 2.0;
    }
    return arr[length / 2];
  }

  public int findMode() {

    HashMap<Integer, Integer> numOccurences = new HashMap<>();

    int[] arr = this.getOutput();
    int mostCommon = 0;

    for (int i = 0; i < arr.length; i++) {
      if (numOccurences.containsKey(arr[i])) {
        int count = numOccurences.get(arr[i]);
        numOccurences.put(arr[i], count + 1);

        if (count > mostCommon) {
          mostCommon = arr[i];
        }
      } else
        numOccurences.put(arr[i], 1);
    }
    return mostCommon;
  }


  public int getLower() {
    return lower;
  }

  public int getHigher() {
    return higher;
  }

  public int[] getBuffer() {
    return buffer;
  }

}