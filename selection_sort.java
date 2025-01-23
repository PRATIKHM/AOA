55import java.util.*;
public class selection_sort{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] arr= new int[10]; 
        int min,key,i,j,min_j;
        System.out.println("Enter the size of the array:");
        int n = sc.nextInt();
        System.out.println("Enter the elements of the array:");
        for( i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        for( i=0;i<n-1;i++){
            min_j=i;
            min=arr[i];
            for( j=i+1;j<n;j++){
                key= arr[i];
                if(arr[j]<min){
                    min_j=j;
                    min=arr[j];

                }
            }
            arr[min_j]=arr[i];
            arr[i]=min;
        }
        System.out.println("Sorted array is:");
        for( i=0;i<n;i++){
            System.out.print(arr[i]+" ");
        }
}
}