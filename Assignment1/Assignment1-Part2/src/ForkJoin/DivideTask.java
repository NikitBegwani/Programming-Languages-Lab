//class for doing mergesort using ForkJoin framework

package ForkJoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.RecursiveTask;

@SuppressWarnings("serial")
//defines the recursive process which needs to be done
public class DivideTask extends RecursiveTask<int[]> {

	  int[] arrayToDivide;
	  //constructor
	  public DivideTask(int[] arrayToDivide) {
	    this.arrayToDivide = arrayToDivide;
	  }

	  @SuppressWarnings("unused")
	@Override
	  protected int[] compute() {
	    @SuppressWarnings("rawtypes")
		List<RecursiveTask> forkedTasks = new ArrayList<>();

	    /*
	     * We divide the array till it has only 1 element. 
	     */
	    if (arrayToDivide.length > 1) {
	      
	      List<int[]> partitionedArray = partitionArray();
	      //allocation of task to two child
	      DivideTask task1 = new DivideTask(partitionedArray.get(0));
	      DivideTask task2 = new DivideTask(partitionedArray.get(1));
	      invokeAll(task1, task2);	// fork process
	      
	      //Wait for results from both the tasks
	      int[] array1 = task1.join();
	      int[] array2 = task2.join();
	      
	      //Initialize a merged array
	      int[] mergedArray = 
	              new int[array1.length + array2.length];
	      
	      mergeArrays(task1.join(), task2.join(), mergedArray);

	      return mergedArray;
	    }
	    return arrayToDivide;
	  }
	  //defining partition array
	  private List<int[]> partitionArray(){
	    
	    int [] partition1 = Arrays.copyOfRange(arrayToDivide, 0,
	              arrayToDivide.length / 2);
	      
	    int [] partition2 = Arrays.copyOfRange(arrayToDivide,
	              arrayToDivide.length / 2,
	              arrayToDivide.length);
	    return Arrays.asList(partition1,partition2);
	    
	  }
	  //merge process
	  private void mergeArrays(
	          int[] array1, 
	          int[] array2, 
	          int[] mergedArray) {
	    
	    int i = 0, j = 0, k = 0;
	    //merging both the arrays
	    while ((i < array1.length) && (j < array2.length)) {
	    
	      if (array1[i] < array2[j]) {
	        mergedArray[k] = array1[i++];
	      } else {
	        mergedArray[k] = array2[j++];
	      }
	      
	      k++;
	    }
	    
	    if (i == array1.length) {
	      
	      for (int a = j; a < array2.length; a++) {
	        mergedArray[k++] = array2[a];
	      }
	      
	    } else {
	      
	      for (int a = i; a < array1.length; a++) {
	        mergedArray[k++] = array1[a];
	      }
	      
	    }
	  }
	}
