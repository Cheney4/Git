package cse360assign2;

/**
 * Class to hold integers in an array. Non-duplicate values
 * are inserted in order. If the array is full the largest value
 * is dropped off the end. Bounds checking is done.
 *
 * @author Adam Charney PIN 213 for CSE360 Spring 2016
 *
 */

public class OrderedIntList {

	/** Array to hold sorted integers */
	private int[] array;

	/** Place holder to aid with current size and check bounds */
	private int counter;

	/**
	 * Creates an ordered integer list, count is ten by default
	 */

	OrderedIntList ()
	{
		array = new int[10];
	}

	/**
	 * Creates an array of integers
	 *
	 * @param size size of ordered integer list
	 */

	OrderedIntList(int size)
	{
		array = new int[size];
	}

	/**
	 * insert - Inserts parameter into array in ascending numerical
	 * order. If list is full and parameter is less than the largest
	 * value, largest value is dropped off array, otherwise number
	 * is not inserted.
	 *
	 * @param insertNum Number to be inserted*/

	public void insert (int insertNum)
	{

		int index = 0;
		int follow = 0;

		while(index < counter && insertNum > array[index])
		{
			index++;
			follow++;
		}

		if(insertNum != array[index])
		{
			for (index = counter; index > follow; index--)
				array[index] = array[index - 1];

			//check for space in array or that insertion is less than greatest
			if(counter < array.length - 1 || insertNum < array[counter])
				array[follow]= insertNum;

			if(counter < this.size())
				counter++;
		}

		//create more space for full array
		if (this.length() == this.size())
			array = insertHelper(array);

		//reduce size if less than 50% full
		while (counter < (this.size() / 2))
			array = deleteHelper(array);
	}

	/**
	 * insertHelper - if the array is full, creates a new array
	 * 50% larger, based on integer arithmetic
	 *
	 * @param array array to be enlarged
	 *
	 * @return enlarged array
	 */

	private int[] insertHelper(int[] enlargeArray)
	{
		//size of 50% larger array
		int largerSize = this.size() + (this.size() / 2);

		int[] largerArray = new int[largerSize];

		//copy original array contents to larger array
		for(int index = 0; index < this.length(); index++)
			largerArray[index] = array[index];

		return largerArray;
	}

	/**
	 * size - Returns the total size of the entire array.
	 */

	public int size()
	{
		return array.length;
	}

	/**
	 * length - Returns the size of the array based on the number of populated elements.
	 */

	public int length()
	{
		return counter - 1;
	}

	/**
	 * search - Searches the given array using the binary search algorithm
	 *
	 * @param - array[] array to be searched
	 * @param - key key to searched for
	 * @param - min minimum index of search range
	 * @param - max maximum index of search range
	 */

	private int search(int[] searchArray, int key, int min, int max)
	{

		int keyLocation = -1;

		//key was not found
		if (max < min)
			return keyLocation;

		else
		{
			int middle = (min + max) / 2;

			//key is in upper half of array
			if(searchArray[middle] < key)
				search(searchArray, key, middle + 1, max);

			//key is in lower half of array
			else if (searchArray[middle] > key)
				search(searchArray, key, min, middle - 1);

			//key has been found
			else if (searchArray[middle] == key)
				keyLocation = middle;
		}

		return keyLocation;
	}

	/**
	 * delete - removes one element from the array
	 *
	 * @param key key element to be deleted
	 */

	public void delete(int key)
	{
		int keyIndex = search(array, key, array[0], array[counter - 1]);

		for (int index = keyIndex; index < counter; index++)
			array[index] = array[index + 1];

		counter--;

		if(counter < (this.size() / 2))
			array = deleteHelper(array);

	}

	/**
	 * deleteHelper - copies the contents of the array into a
	 * new array 40% smaller, based on integer arithmetic.
	 *
	 * @param array - array to be reduced
	 *
	 * @return array reduced in size by 40%
	 */

	private int[] deleteHelper(int[] reduceArray)
	{
		int smallerSize = this.size() - ((this.size() * 4) / 10);

		int[] smallerArray = new int[smallerSize];

		for(int index = 0; index < counter; index++)
			smallerArray[index] = array[index];

		return smallerArray;
	}

	/**
	 * toString - creates a string of the integers in the array separated
	 * by a space.
	 *
	 * @return string of integers in the array separated by a space
	 */

	public String toString()
	{
		String arrayContent = "";

		for (int index = 0; index < counter; index++)
			{
				if(index < counter - 1)
					arrayContent = arrayContent + array[index] + " ";
				else
					arrayContent = arrayContent + array[index];
			}

		return arrayContent;
	}
}
