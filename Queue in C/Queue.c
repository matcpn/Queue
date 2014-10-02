#include "stdio.h"
#include "stdlib.h"

typedef struct QueueElement {
	int data;
	struct QueueElement* next_element;
} QueueElement;

typedef struct Queue {
	int size;
	int number_of_elements;
	QueueElement* head;
	QueueElement* tail;
} Queue;

void enqueue(int data, Queue *queue);
int dequeue(Queue* queue);
Queue* queue_factory(int size);
void queue_destructor(Queue* queue);
QueueElement* queue_element_factory(int data);
void queue_element_destructor(QueueElement* queue_element);
void print_queue(Queue* queue);

void main(int childargc, char* childargv[]) {
	Queue* test_queue = queue_factory(3);

	//testing enqueue
	printf("Expected: Empty Queue\t\tActual: ");
	print_queue(test_queue);
	printf("\n");
	enqueue(1, test_queue);
	printf("Expected: 1\t\tActual: ");
	print_queue(test_queue);
	printf("\n");
	enqueue(2, test_queue);
	printf("Expected: 1 2\t\tActual: ");
	print_queue(test_queue);
	printf("\n");
	enqueue(3, test_queue);
	printf("Expected: 1 2 3\t\tActual: ");
	print_queue(test_queue);
	printf("\n");
	printf("Expected: Error: Enqueue on a full queue\t\tActual: ");
	enqueue(4, test_queue);

	//testing dequeue
	printf("Expected: 1 \t\tActual: ");
	printf("%i", dequeue(test_queue));
	printf("\n");
	printf("Expected: 2 \t\tActual: ");
	printf("%i", dequeue(test_queue));
	printf("\n");
	printf("Expected: 3 \t\tActual: ");
	printf("%i", dequeue(test_queue));
	printf("\n");
	printf("Expected: Error: Dequeue on an empty queue \t\tActual: ");
	dequeue(test_queue);

	queue_destructor(test_queue);
}

//since there are no constructors in C, here are two initializers
Queue* queue_factory(int size) {
	Queue* queue;
	if ( (queue = malloc(sizeof(Queue))) == NULL ) {
		printf("Malloc failed.\n");
		return;
	}
	queue->size = size;
	queue->head = NULL;
	queue->tail = NULL;
}

void queue_destructor(Queue* queue) {
	free(queue);
}

QueueElement* queue_element_factory(int data) {
	QueueElement* queue_element;
	if ( (queue_element = malloc(sizeof(QueueElement))) == NULL ) {
		printf("Malloc failed.\n");
		return;
	}
	queue_element->data = data;
	return queue_element;
}

void queue_element_destructor(QueueElement* queue_element) {
	free(queue_element);
}

//the data you want to add to the queue, and the queue you want to add it to
void enqueue(int data, Queue *queue) {
	if (queue->number_of_elements == queue->size) {
		printf("Error: Enqueue on a full queue\n");
		return;
	}
	QueueElement* queue_element = queue_element_factory(data);
	if (queue->head == NULL) {
		queue->head = queue_element;
		queue->tail = queue_element;
		queue->number_of_elements++;
		return;
	}
	else {
		queue->tail->next_element = queue_element;
		queue->tail = queue_element;
		queue->number_of_elements++;
		return;
	}
}

//the data you want to add to the queue, and the queue you want to dequeue from
int dequeue(Queue* queue) {
	if (queue->number_of_elements == 0) {
		printf("Error: Dequeue on an empty queue\n");
		return;
	}
	int element_data = queue->head->data;
	QueueElement* queue_head = queue->head;
	queue->head = queue->head->next_element;
	queue_element_destructor(queue_head);
	queue->number_of_elements--;
	return element_data;
}

void print_queue(Queue* queue) {
	if (queue->head == NULL) {
		printf("Empty Queue");
		return;
	}
	QueueElement *current_elem = queue->head;
	while(current_elem->next_element != NULL) {
		printf("%i ", current_elem->data);
		current_elem = current_elem->next_element;
	}
	printf("%i ", current_elem->data);
	return;
}

