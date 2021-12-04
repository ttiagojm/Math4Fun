# 📝Notas

O script `prod_mat_triag_vec.py` foi feito para testar as propriedades de matrizes triangulares, quando feito um produto entre uma matriz e um vetor.

<hr>

⚫Para isso criei diversas funções que calculam os produtos de diferentes matrizes triangulares.

⚫As assertions servem apenas para verificar se as funções dão o mesmo resultado que a biblioteca de álgebra linear (**Numpy**).

⚫Os benchmarks permitem verificar que a função que faz o *produto comum (naive)* entre matriz e vetor, demora o dobro do tempo, em comparação às funções que exploram as propriedades das matrizes triangulares (*efficient*).

⚫O `np.dot` do **Numpy** está lá só para humilhar a performance destes algoritmos implementados de forma simples e rudimentar(a única que sei neste).

⚫Normalmente, em matrizes não transpostas, fazer o produto *matriz-vetor*, utilizando o método *axpy (alpha times X plus Y)*, é mais performático do que o simples *dot product* entre cada linha da matriz e o vetor. Isto, devido à forma de como matrizes são armazenadas na memória.

⚫Não consegui comprovar o ponto acima, não sei se porque:

- Implementei incorretamente o método, mesmo dando um resultado correto
- Python/Numpy armazenam listas/numpy_arrays de forma diferente
- Por estar a usar uma linguagem interpretada 