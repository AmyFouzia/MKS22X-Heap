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

  public static void heapify(int[] data){
      //- convert the array into a valid heap. [ should be O(n) ]
      //start at 2nd to last row
      for(int i = (data.length-1)/2; i>=0; i--){
        pushDown(data, data.length, i);
      }
  }

  public static void heapsort(int[] data){
      /*- sort the array [ should be O(nlogn) ] :
     converting it into a heap
     removing the largest value n-1 times (remove places at end of the sub-array).*/
     heapify(data);

     //2nd to last row
     for(int i = data.length-1; i>=0; i--){
       swap(data, 0, i);
       pushDown(data, i, 0);
     }
   }

   public static void swap(int[] data, int a, int b){
     int track = data[b];
     data[b] = data[a];
     data[a] = track;
    }

    public static void main(String[]args){
     System.out.println("Size\t\tMax Value\tmerge /builtin ratio ");
     int[]MAX_LIST = {1000000000,500,10};
     for(int MAX : MAX_LIST){
       for(int size = 31250; size < 2000001; size*=2){
         long qtime=0;
         long btime=0;
         //average of 5 sorts.
         for(int trial = 0 ; trial <=5; trial++){
           int []data1 = new int[size];
           int []data2 = new int[size];
           for(int i = 0; i < data1.length; i++){
             data1[i] = (int)(Math.random()*MAX);
             data2[i] = data1[i];
           }
           long t1,t2;
           t1 = System.currentTimeMillis();
           MyHeap.heapsort(data2);
           t2 = System.currentTimeMillis();
           qtime += t2 - t1;
           t1 = System.currentTimeMillis();
           Arrays.sort(data1);
           t2 = System.currentTimeMillis();
           btime+= t2 - t1;
           if(!Arrays.equals(data1,data2)){
             System.out.println("FAIL TO SORT!");
             System.exit(0);
           }
         }
         System.out.println(size +"\t\t"+MAX+"\t"+1.0*qtime/btime);
       }
       System.out.println();
     }
   }

}
