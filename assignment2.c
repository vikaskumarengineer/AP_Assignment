#include <stdio.h>
#include <stdlib.h>

// Space Complexity O(1) - Constant Space
void constantSpace(int n) {
    printf("O(1) - Constant Space: ");
    // Sirf fixed number of variables use hote hain
    int singleVariable = n;
    printf("Uses only 1 variable (fixed size)\n");
}

// Space Complexity O(n) - Linear Space
void linearSpace(int n) {
    printf("O(n) - Linear Space: ");
    // Array of size n - space grows with input
    int *arr = (int*)malloc(n * sizeof(int));
    
    for(int i = 0; i < n; i++) {
        arr[i] = i + 1;
    }
    
    printf("Uses array of size %d (memory: %d bytes)\n", n, n * sizeof(int));
    free(arr); // Memory free karo
}

// Space Complexity O(n^2) - Quadratic Space
void quadraticSpace(int n) {
    printf("O(n^2) - Quadratic Space: ");
    // 2D array (matrix) - space grows quadratically
    int **matrix = (int**)malloc(n * sizeof(int*));
    
    for(int i = 0; i < n; i++) {
        matrix[i] = (int*)malloc(n * sizeof(int));
        for(int j = 0; j < n; j++) {
            matrix[i][j] = i + j;
        }
    }
    
    printf("Uses %d x %d matrix (memory: %d bytes)\n", n, n, n * n * sizeof(int));
    
    // Free memory
    for(int i = 0; i < n; i++) {
        free(matrix[i]);
    }
    free(matrix);
}

// Function to calculate and display memory usage
void analyzeSpaceComplexity() {
    int sizes[] = {10, 100, 500, 1000};
    int num_sizes = 4;
    
    printf("\n========== SPACE COMPLEXITY ANALYSIS ==========\n\n");
    
    for(int i = 0; i < num_sizes; i++) {
        int n = sizes[i];
        printf("Input Size (n) = %d\n", n);
        printf("----------------------------------------\n");
        
        // O(1) - Constant Space
        constantSpace(n);
        
        // O(n) - Linear Space
        linearSpace(n);
        
        // O(n^2) - Quadratic Space
        if(n <= 500) {  // Avoid too large sizes for O(n^2)
            quadraticSpace(n);
        } else {
            printf("O(n^2) - Quadratic Space: Skipped (memory would be %d MB)\n", 
                   (n * n * sizeof(int)) / (1024 * 1024));
        }
        
        printf("\n");
    }
    
    printf("========== ANALYSIS SUMMARY ==========\n");
    printf("1. O(1): Space remains constant (e.g., single variable)\n");
    printf("2. O(n): Space increases linearly (e.g., array of size n)\n");
    printf("3. O(n^2): Space increases quadratically (e.g., n x n matrix)\n");
    printf("\nNote: Space complexity affects memory usage!\n");
}

int main() {
    analyzeSpaceComplexity();
    return 0;
}