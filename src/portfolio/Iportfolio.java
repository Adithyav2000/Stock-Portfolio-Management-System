package portfolio;

/**
 * interface portfolio.
 */
public interface Iportfolio {
  /**
   * This appends the new stock classes into this portfolio class.
   *
   * @param input input to be appended.
   * @return float object after appending.
   */
  float append(String input);

  /**
   * Get index returns a string after getting its index.
   *
   * @param i index.
   * @return string to be returned.
   */
  String getIndex(int i);

  /**
   * Returns the size.
   *
   * @return returns the size as int.
   */
  int getSize();
}
