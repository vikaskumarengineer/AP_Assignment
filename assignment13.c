#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char *data;
    size_t length;
    size_t capacity;
} StringBuffer;

StringBuffer* sb_init(size_t initial_capacity) {
    StringBuffer *sb = (StringBuffer*)malloc(sizeof(StringBuffer));
    if (sb == NULL) {
        return NULL;
    }
    
    sb->data = (char*)malloc(initial_capacity + 1);
    if (sb->data == NULL) {
        free(sb);
        return NULL;
    }
    
    sb->data[0] = '\0';
    sb->length = 0;
    sb->capacity = initial_capacity;
    
    return sb;
}

void sb_append(StringBuffer *sb, const char *str) {
    if (sb == NULL || str == NULL) {
        return;
    }
    
    size_t str_len = strlen(str);
    size_t new_len = sb->length + str_len;
    
    if (new_len >= sb->capacity) {
        size_t new_capacity = sb->capacity * 2;
        while (new_capacity <= new_len) {
            new_capacity *= 2;
        }
        
        char *new_data = (char*)realloc(sb->data, new_capacity + 1);
        if (new_data == NULL) {
            return;
        }
        
        sb->data = new_data;
        sb->capacity = new_capacity;
    }
    
    strcpy(sb->data + sb->length, str);
    sb->length = new_len;
}

void sb_free(StringBuffer *sb) {
    if (sb == NULL) {
        return;
    }
    
    if (sb->data != NULL) {
        free(sb->data);
    }
    
    free(sb);
}

int main() {
    StringBuffer *sb = sb_init(16);
    char input[256];
    int choice;
    
    printf("Dynamic String Buffer Demo\n");
    printf("1. Append text\n");
    printf("2. Show current string\n");
    printf("3. Show length and capacity\n");
    printf("4. Exit\n");
    
    do {
        printf("\nEnter choice: ");
        scanf("%d", &choice);
        getchar();
        
        switch(choice) {
            case 1:
                printf("Enter text to append: ");
                fgets(input, sizeof(input), stdin);
                input[strcspn(input, "\n")] = 0;
                sb_append(sb, input);
                printf("Appended! Buffer grew if needed.\n");
                break;
                
            case 2:
                printf("Current string: %s\n", sb->data);
                break;
                
            case 3:
                printf("Length: %zu, Capacity: %zu\n", sb->length, sb->capacity);
                break;
                
            case 4:
                printf("Exiting...\n");
                break;
                
            default:
                printf("Invalid choice!\n");
        }
    } while(choice != 4);
    
    sb_free(sb);
    return 0;
}
