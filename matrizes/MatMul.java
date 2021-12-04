package matrizes;

/*
 * @State: Pronto (por agora)
 */

public class MatMul {

	static boolean validateColumns(double[][]mat) {
		// Verificar se temos sempre o mesmo número de colunas

		// Tamanho da primeira linha
		int numCols = mat[0].length;

		for(int i = 1; i < mat.length; i++) { 

			if(mat[i].length != numCols) {
				return false;
			}

		}

		return true;

	}

	static boolean validateMatMul(double[][] matA, double[][]matB) {

		return matA[0].length == matB.length;

	}

	static void printResult(double[][] matResult) {
		
		for(int i = 0; i < matResult.length; i++) {

			// Itera colunas da matriz B
			for(int j = 0; j < matResult[0].length; j++) {

				System.out.printf("%10.2f", matResult[i][j]);

			}

			System.out.printf("\n");
		}
		
	}
	
	public static void main(String[] args) {

		double [][]matA = {
				{1, 5, -6},
				{0, 3, -4},
				{0, 3, -4}
		};


		double [][]matB = {
				{1}, 
				{-1},
				{3},
		};

		boolean validMatMul = true;

		// Validar colunas de cada matriz
		if(!validateColumns(matA)) {
			validMatMul = false;
			System.out.println("Matriz A tem um número de colunas inconstante.");
		}
		else if(!validateColumns(matB)) {
			validMatMul = false;
			System.out.println("Matriz B tem um número de colunas inconstante.");
		}
		// Validar se número de colunas de A = número de linhas de B
		else if(!validateMatMul(matA, matB)) {
			validMatMul = false;
			System.out.println("Número de colunas da matriz A != do número de linhas da matriz B");
		}


		// Se tudo for válido, calculamos
		if(validMatMul) {

			double [][]matResult = new double[matA.length][matB[0].length];

			// Multiplicação de matrizes
			matMul(matResult, matA, matB);
			
			printResult(matResult);

		}

	}

	public static double dotProduct(double[][] matA, double[][] matB, int row, int col) {

		double dotProd = 0;

		for(int k = 0; k < matB[0].length; k++) {
			dotProd += matA[row][k] * matB[k][col];
		}

		return dotProd;
	}


	public static void matMul(double[][] matResult, double[][] matA, double[][] matB) {


		// Itera linhas da matriz A
		for(int i = 0; i < matA.length; i++) {

			// Itera colunas da matriz B
			for(int j = 0; j < matB[0].length; j++) {

				matResult[i][j] = dotProduct(matA, matB, i, j);

			}
		}

	}

}
