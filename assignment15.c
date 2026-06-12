
#Counter without syn.
#include <stdio.h>
#include <pthread.h>

#define NUM_THREADS 5
#define INCREMENTS 1000000

long long counter = 0;

void *increment(void *arg)
{
int id = *(int *)arg;
printf("[Thread %d] started\n", id);
for (long i = 0; i < INCREMENTS; i++) {
counter++;
}
printf("[Thread %d] finished\n", id);
return NULL;
}

int main(void)
{
pthread_t threads[NUM_THREADS];

1

int ids[NUM_THREADS];

printf("=== Part 1: WITHOUT Mutex (Race Condition) ===\n");
printf("Threads: %d | Increments per thread: %d\n\n", NUM_THREADS, INCREMENTS);

for (int i = 0; i < NUM_THREADS; i++) {
ids[i] = i + 1;
if (pthread_create(&threads[i], NULL, increment, &ids[i]) != 0) {
perror("pthread_create");
return 1;
}
}

for (int i = 0; i < NUM_THREADS; i++) {
pthread_join(threads[i], NULL);
}

long long expected = (long long)NUM_THREADS * INCREMENTS;
printf("\nExpected counter : %lld\n", expected);
printf("Actual counter : %lld\n", counter);
printf("Lost updates : %lld\n", expected - counter);
printf(">>> Race condition detected!\n");

return 0;

2

}

--------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------
#Part 2 : mutex with sync
#include <stdio.h>
#include <pthread.h>

#define NUM_THREADS 5
#define INCREMENTS 1000000

long long counter = 0;
pthread_mutex_t counter_mutex = PTHREAD_MUTEX_INITIALIZER;

void *increment(void *arg)
{
int id = *(int *)arg;
printf("[Thread %d] started\n", id);
for (long i = 0; i < INCREMENTS; i++) {
pthread_mutex_lock(&counter_mutex);
counter++;
pthread_mutex_unlock(&counter_mutex);
}
printf("[Thread %d] finished\n", id);
return NULL;

3

}

int main(void)
{
pthread_t threads[NUM_THREADS];
int ids[NUM_THREADS];

printf("=== Part 2: WITH Mutex (Correct Synchronization) ===\n");
printf("Threads: %d | Increments per thread: %d\n\n", NUM_THREADS, INCREMENTS);

for (int i = 0; i < NUM_THREADS; i++) {
ids[i] = i + 1;
if (pthread_create(&threads[i], NULL, increment, &ids[i]) != 0) {
perror("pthread_create");
pthread_mutex_destroy(&counter_mutex);
return 1;
}
}

for (int i = 0; i < NUM_THREADS; i++) {
pthread_join(threads[i], NULL);
}

pthread_mutex_destroy(&counter_mutex);

4

long long expected = (long long)NUM_THREADS * INCREMENTS;
printf("\nExpected counter : %lld\n", expected);
printf("Actual counter : %lld\n", counter);

if (counter == expected)
printf(">>> Counter is CORRECT! Mutex worked perfectly.\n");
else
printf(">>> Unexpected mismatch.\n");

return 0;
}
