
# Número primo

def esPrimo(n):
    if n < 1:
        return False
    elif n == 1 or n == 2:
        return True
    else:
        m = 0
        for i in range (2, n):
            if n % i == 0:
                m = m + 1
        if m == 0:
            return True
        else:
            return False

def mensaje(n):
    if esPrimo(n):
        print (n, " sí es primo")
    else:
        print (n, " no es primo")

#main
n = 5
num = 4

mensaje(n)
mensaje(num)
        
