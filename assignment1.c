/* NAME: VIKAS KUMAR  
ROLL NUMBER: CSB24043 
*/ 
#include <stdio.h> 
#include <time.h> 
#define REPEAT 100000  
void linearSearch(int arr[], int size, int target) { 
 clock_t start = clock(); 
 int flag = 0; 
 for (int r = 0; r < REPEAT; r++) { 
 for (int i = 0; i < size; i++) { 
 if (arr[i] == target) { 
 if (r == 0) {  
 printf("Linear Search: Number found at index %d\n", i);  } 
 flag = 1; 
 break; 
 } 
 } 
 } 
1
 if (!flag) { 
 printf("Linear Search: Number not found\n"); 
 } 
 clock_t end = clock(); 
 double time_taken = (double)(end - start) / CLOCKS_PER_SEC;  printf("Linear Search Execution Time: %f seconds\n\n", time_taken); } 
int binarySearch(int arr[], int size, int target) { 
 clock_t start = clock(); 
 int index = -1; 
 for (int r = 0; r < REPEAT; r++) { 
 int lb = 0, ub = size - 1; 
 while (lb <= ub) { 
 int mid = lb + (ub - lb) / 2; 
 if (arr[mid] == target) { 
 index = mid; 
 break; 
 } else if (target > arr[mid]) { 
 lb = mid + 1; 
2
 } else { 
 ub = mid - 1; 
 } 
 } 
 } 
 clock_t end = clock(); 
 double time_taken = (double)(end - start) / CLOCKS_PER_SEC;  printf("Binary Search Execution Time: %f seconds\n\n", time_taken); 
 return index; 
} 
void findPairs(int arr[], int size, int x, int y) { 
 clock_t start = clock(); 
 int flag = 0; 
 for (int r = 0; r < REPEAT; r++) { 
 for (int i = 0; i < size; i++) { 
 for (int j = 0; j < size; j++) { 
 if (arr[i] == x && arr[j] == y) { 
 if (r == 0) {  
 printf("Pair found: (%d, %d) at indices (%d, %d)\n", 3
 x, y, i, j); 
 } 
 flag = 1; 
 } 
 } 
 } 
 } 
 if (!flag) { 
 printf("Pair not found\n"); 
 } 
 clock_t end = clock(); 
 double time_taken = (double)(end - start) / CLOCKS_PER_SEC;  printf("Find Pairs Execution Time: %f seconds\n\n", time_taken); } 
int main() { 
 int arr[] = {1,2,3,4,5,6,7,8,9,10,11,22,33,44,55,66,77,88,99};  int size = sizeof(arr) / sizeof(arr[0]); 
 int target, x1, x2; 
 printf("Enter any number to find in this array: "); 4
 scanf("%d", &target); 
 linearSearch(arr, size, target); 
 int index = binarySearch(arr, size, target); 
 if (index != -1) 
 printf("Binary Search: Number found at index %d\n\n", index);  else 
 printf("Binary Search: Number not found\n\n"); 
 printf("Enter two numbers to find out pairs in this array: ");  scanf("%d %d", &x1, &x2); 
 findPairs(arr, size, x1, x2); 
 return 0; 
} 
/*output 
Enter any number to find in this array: 6 
Linear Search: Number found at index 5 
Linear Search Execution Time: 0.001407 seconds 
Binary Search Execution Time: 0.002295 seconds 
5
Binary Search: Number found at index 5 
Enter two numbers to find out pairs in this array: 5 8 
Pair found: (5, 8) at indices (4, 7) 
Find Pairs Execution Time: 0.051949 seconds second input------------------- 
Enter any number to find in this array: 44 Linear Search: Number found at index 13 Linear Search Execution Time: 0.002453 seconds 
Binary Search Execution Time: 0.001657 seconds Binary Search: Number found at index 13 
Enter two numbers to find out pairs in this array: 1 888 
Pair not found 
Find Pairs Execution Time: 0.046161 seconds */ 
