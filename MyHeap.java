import java.util.*;

public class MyHeap{

  private static void pushDown(int[]data, int size, int index){
      /*- size  is the number of elements in the data array.
      - push the element at index i downward into the correct position. This will swap with the larger of the child nodes provided thatchild is larger. This stops when a leaf is reached, or neither child is larger. [ should be O(logn) ]
      - precondition: index is between 0 and size-1 inclusive
      - precondition: size is between 0 and data.length-1 inclusive.*/

      boolean isSorted = false; //keep track of order

      while(!isSorted){
        int rightKid = index * 2 + 2;
        int leftKid = index * 2 + 1;
        int max = 0;

        //if no kid exit while loop
        if(leftKid >= size && rightKid >= size){
          isSorted = true;
        }
        else{
          max = data[index];
        }

        //if right kid, check and set max
        if(rightKid < size){
          max = Math.max(max, data[rightKid]);
        }

        //if left kid, check and set max
        if(leftKid < size){
          max = Math.max(max, data[leftKid]);
        }

        //check if max = parent
        if(max == data[index]){
          isSorted = true;
        }

        //swap values after comparisons
        //right
        else if(rightKid < size && max == data[rightKid]){
          swap(data, index, rightKid);
          index = rightKid;
        }
        //left
        else if(leftKid < size && max == data[leftKid]){
          swap(data, index, leftKid);
          index = leftKid;
        }
      }
  }

  private static void pushUp(int[]data, int index){
      /*- push the element at index i up into the correct position. This will swap it with the parent node until the parent node is larger or the root is reached. [ should be O(logn) ]
      - precondition: index is between 0 and data.length-1 inclusive.*/

      boolean isSorted = false;

      while(!isSorted){
        int parent = (index - 1) /2;

        //no parent
        if(parent < 0){
          isSorted = true;
        }

        //kid > parent
        else{
          if(data[index] > data[parent]){
            swap(data, index, parent);
            index = parent;
          }
          else{
            isSorted = true;
          }
        }
      }
    }

  public static void heapify(int[]){
      //- convert the array into a valid heap. [ should be O(n) ]
  }

  public static void heapsort(int[]){
      /*- sort the array [ should be O(nlogn) ] :
     converting it into a heap
     removing the largest value n-1 times (remove places at end of the sub-array).*/
   }

   public static void swap(int[] data, int a, int b){
     int track = data[b];
     data[b] = data[a];
     data[a] = track;
    }

}
