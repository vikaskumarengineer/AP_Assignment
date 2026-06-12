

#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <semaphore.h>
#include <unistd.h>

#define BUFFER_SIZE 5
#define NUM_PRODUCERS 2
#define NUM_CONSUMERS 2
#define ITEMS_PER_PRODUCER 6

int buffer[BUFFER_SIZE];
int in = 0;
int out = 0;
int item_id = 0;

sem_t empty_slots;
sem_t filled_slots;
pthread_mutex_t buffer_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t item_id_mutex = PTHREAD_MUTEX_INITIALIZER;

void print_buffer()
{
printf(" Buffer: [");

1

for (int i = 0; i < BUFFER_SIZE; i++) {
int idx = (out + i) % BUFFER_SIZE;
if (i < BUFFER_SIZE)
printf("%3d", buffer[idx]);
if (i < BUFFER_SIZE - 1) printf(",");
}
printf(" ]\n");
}

void *producer(void *arg)
{
int id = *(int *)arg;
for (int i = 0; i < ITEMS_PER_PRODUCER; i++) {
pthread_mutex_lock(&item_id_mutex);
int item = ++item_id;
pthread_mutex_unlock(&item_id_mutex);

usleep((rand() % 300 + 100) * 1000);

printf("[Producer %d] Waiting for empty slot... (item=%d)\n", id, item);
sem_wait(&empty_slots);

pthread_mutex_lock(&buffer_mutex);
buffer[in] = item;

2

printf("[Producer %d] Produced item=%d at slot %d\n", id, item, in);
in = (in + 1) % BUFFER_SIZE;
print_buffer();
pthread_mutex_unlock(&buffer_mutex);

sem_post(&filled_slots);
printf("[Producer %d] Signaled: filled_slots incremented\n", id);
}
printf("[Producer %d] Done.\n", id);
return NULL;
}

void *consumer(void *arg)
{
int id = *(int *)arg;
int total = (ITEMS_PER_PRODUCER * NUM_PRODUCERS) / NUM_CONSUMERS;
for (int i = 0; i < total; i++) {
usleep((rand() % 500 + 200) * 1000);

printf("[Consumer %d] Waiting for filled slot...\n", id);
sem_wait(&filled_slots);

pthread_mutex_lock(&buffer_mutex);
int item = buffer[out];

3

printf("[Consumer %d] Consumed item=%d from slot %d\n", id, item, out);
buffer[out] = 0;
out = (out + 1) % BUFFER_SIZE;
print_buffer();
pthread_mutex_unlock(&buffer_mutex);

sem_post(&empty_slots);
printf("[Consumer %d] Signaled: empty_slots incremented\n", id);
}
printf("[Consumer %d] Done.\n", id);
return NULL;
}

int main(void)
{
srand(42);

sem_init(&empty_slots, 0, BUFFER_SIZE);
sem_init(&filled_slots, 0, 0);

pthread_t prod_threads[NUM_PRODUCERS];
pthread_t cons_threads[NUM_CONSUMERS];
int prod_ids[NUM_PRODUCERS];
int cons_ids[NUM_CONSUMERS];

4

printf("=== Producer-Consumer using Semaphores ===\n");
printf("Buffer size: %d | Producers: %d | Consumers: %d | Items each: %d\n\n",
BUFFER_SIZE, NUM_PRODUCERS, NUM_CONSUMERS, ITEMS_PER_PRODUCER);

for (int i = 0; i < NUM_PRODUCERS; i++) {
prod_ids[i] = i + 1;
pthread_create(&prod_threads[i], NULL, producer, &prod_ids[i]);
}
for (int i = 0; i < NUM_CONSUMERS; i++) {
cons_ids[i] = i + 1;
pthread_create(&cons_threads[i], NULL, consumer, &cons_ids[i]);
}

for (int i = 0; i < NUM_PRODUCERS; i++)
pthread_join(prod_threads[i], NULL);
for (int i = 0; i < NUM_CONSUMERS; i++)
pthread_join(cons_threads[i], NULL);

sem_destroy(&empty_slots);
sem_destroy(&filled_slots);
pthread_mutex_destroy(&buffer_mutex);
pthread_mutex_destroy(&item_id_mutex);

5

printf("\n=== All threads finished. Final item_id produced: %d ===\n", item_id);
return 0;
}
