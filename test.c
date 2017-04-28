#include <stdio.h>

void foo() {
    int i;
    printf("%d ", i++);
}

void bar() {
    int i;
    i++;
}

int main() {
    int j;

    for (j = 1; j <= 10; j++) {
        foo();
        bar();
    }
}
