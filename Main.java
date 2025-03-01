static long solve(long N) {
    if(N==1) {
        return 1;
    }
    
    // Count ribbons needed to cover from largest to smallest
    long ribbonsNeeded = 0;
    long sumUncovered = (N * (N + 1)) / 2; // Sum of all sizes from 1 to N
    long currentSize = N + 1; // Start with Bob's largest ribbon size
    
    while(sumUncovered > 0 && currentSize > 0) {
        // Take this ribbon
        ribbonsNeeded++;
        
        if(currentSize > sumUncovered) {
            // If current ribbon is bigger than sum of remaining sizes,
            // we can cut it to cover all remaining sizes
            sumUncovered = 0;
        } else {
            // Otherwise, use this ribbon for its size if we need it
            if(currentSize <= N) {
                sumUncovered -= currentSize;
            }
        }
        currentSize--;
    }
    
    return ribbonsNeeded;
}