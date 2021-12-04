import numpy as np
import timeit

NUM_RUNS = 10

def mat_trig_upper_dot(mat, v, y, n):

    for i in range(n-1,-1,-1):
        for j in range(n-1, i-1, -1):
            y[i] += mat[i][j] * v[j]

    return y


def mat_trig_upper_axpy(mat, v, y, n):
    for j in range(n-1,-1,-1):
        for i in range(j+1):
            y[i] += mat[i][j] * v[j]

    return y

def mat_trig_lower_dot(mat, v, y, n):
    for i in range(n):
        for j in range(i+1):
            y[i] += mat[i][j] * v[j]

    return y


def mat_trig_lower_axpy(mat, v, y, n):
    for j in range(n):
        for i in range(n-1, j-1, -1):
            y[i] += mat[i][j] * v[j]

    return y


def mat_sym_trig_upper_dot(mat, v, y, n):
    for i in range(n):
        for j in range(n):
            if i > j:
                y[i] += mat[j][i] * v[j]
            else:
                y[i] += mat[i][j] * v[j]
    
    return y


def mat_vector_dot(mat, v, y, n):
    for i in range(n):
        for j in range(n):
            y[i] += mat[i][j] * v[j]
    return y

def assertion(func, mat, v, n):

    y = np.zeros(n, dtype=int)

    # numpy
    dot = np.dot(mat, v)

    # mine
    my_dot = func(mat, v, y, n)

    return np.array_equal(dot, my_dot)


def make_tests():
    mat_trig_upper = np.array([
        [1,2,3],
        [0,4,5],
        [0,0,6]
    ])


    mat_trig_lower = np.array([
        [1,0,0],
        [2,3,0],
        [4,5,6]
    ])

    mat_sym = np.array([
        [1,2,3],
        [2,5,6],
        [3,6,7]
    ])

    mat = np.array([
        [1,2,3],
        [4,5,6],
        [7,8,9]
    ])

    v = np.array([1,0,1])
    n = len(v)

    # Testes
    assert assertion(mat_trig_upper_dot,mat_trig_upper,v,n)
    print("Matriz Triangular Superior Dot - [!] PASSOU")

    assert assertion(mat_trig_lower_dot,mat_trig_lower,v,n)
    print("Matriz Triangular Inferior Dot - [!] PASSOU")

    assert assertion(mat_sym_trig_upper_dot,mat_sym,v,n)
    print("Matriz Simétrica Dot - [!] PASSOU")

    assert assertion(mat_vector_dot,mat,v,n)
    print("Matriz-Vetor Dot - [!] PASSOU\n\n")


def benchmark(func, msg=None, *args):
    duration = timeit.Timer(lambda: func(*args)).timeit(number = NUM_RUNS)

    avg_duration = duration/NUM_RUNS

    if msg:
        print(f'[+] {msg}: Demorou, aproximadamente, {avg_duration} segundos')

if "__main__" == __name__:
    
    make_tests()

    n = 3000
    v = np.random.randint(low=1, high=5, size=n, dtype=int)
    mat = np.random.randint(low=1, high=10, size=(n, n), dtype=int)

    mat_trig_upper = np.triu(mat)
    mat_trig_lower = np.tril(mat)
    #mat_sym = np.tril(mat) + np.tril(mat, -1).T

    
    print("### Dot: Matriz Triangular Superior ###")

    benchmark(np.dot, "Numpy func", mat_trig_upper, v)

    y = np.zeros(n, dtype=int)
    benchmark(mat_vector_dot, "Naive func", mat_trig_upper, v, y, n)

    y = np.zeros(n, dtype=int)
    benchmark(mat_trig_upper_dot, "Efficient func", mat_trig_upper, v, y, n)


    print("### Dot: Matriz Triangular Inferior ###")

    benchmark(np.dot, "Numpy func", mat_trig_lower, v)

    y = np.zeros(n, dtype=int)
    benchmark(mat_vector_dot, "Naive func", mat_trig_lower, v, y, n)

    y = np.zeros(n, dtype=int)
    benchmark(mat_trig_lower_dot, "Efficient func", mat_trig_lower, v, y, n)



    print("### AxPy: Matriz Triangular Superior ###")

    benchmark(np.dot, "Numpy func", mat_trig_upper, v)
    
    y = np.zeros(n, dtype=int)
    benchmark(mat_vector_dot, "Naive func", mat_trig_upper, v, y, n)

    y = np.zeros(n, dtype=int)
    benchmark(mat_trig_upper_axpy, "Efficient func", mat_trig_upper, v, y, n)


    print("### AxPy: Matriz Triangular Inferior ###")

    benchmark(np.dot, "Numpy func", mat_trig_lower, v)

    y = np.zeros(n, dtype=int)
    benchmark(mat_vector_dot, "Naive func", mat_trig_lower, v, y, n)

    y = np.zeros(n, dtype=int)
    benchmark(mat_trig_lower_axpy, "Efficient func", mat_trig_lower, v, y, n)