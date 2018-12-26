package com.example.plaso.customview.observer;

public class Single {
    private Single(){};
    private static class SingleHolder{
        private static Single single = new Single();
    }

    public static Single getInstance(){
        return SingleHolder.single;
    }

    public void maxSum(int[] array){
        int sum = array[0];
        int n = array[0];
        for(int i=1; i<array.length; i++){
            if(n<0){
                n = array[i];
            }else {
                n += array[i];
            }

            if(n>sum) sum=n;
        }
    }

    public void quickSort(int[] array,int L,int R){
        int i = L;
        int j = R;
        int target = array[array.length/2];

        while (i<=j){

            while (array[i]<target){
                i++;
            }

            while (array[j]>target){
                j--;
            }

            if(i<=j){
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
                i++;
                j--;
            }
        }

        if(i<L) quickSort(array,L,i);

        if(j<R) quickSort(array,j,R);


    }
}


