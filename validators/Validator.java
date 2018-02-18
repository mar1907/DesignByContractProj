package validators;

public interface Validator<T> {

	/**
	 * This method checks whether an object complies to a condition (is valid)
	 * 
	 * @param t the object to be checked
	 * @return true if valid, false otherwise
	 */
	public String isValid(T t);
}
