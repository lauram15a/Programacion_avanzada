
#include <iostream>
#include <unistd.h>
using namespace std;

bool esPrimo(int n);
void mensaje (int n);

int main(int argc, char* argv[])
{
    int n = 2;
    int num = 6;
    int numero = 15;
    int numeroo = 17;

    mensaje(n);
    mensaje(num);
    mensaje(numero);
    mensaje(numeroo);
}

bool esPrimo(int n)
{
    if(n < 1) {
        return false;
    } else if((n == 1 || n == 2)) {
        return true;
    } else {
        int m = 0;
        for(int i = 2; i < n; i++) {
            if(n % i == 0) {
                m++;
            }
        }
        if(m == 0) {
            return true;
        } else {
            return false;
        }
    }
}

void mensaje(int n)
{
    if(esPrimo(n)) {
        cout << n << " si es primo" << endl; 
    } else {
        cout << n << " no es primo" << endl; 
    }
}