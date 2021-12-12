package matrizes;

/*
 * State: Em progresso
 */

public class Determinant {

	static double Mat2x2Determinant(double[][] mat) {

		return (mat[0][0] * mat[1][1]) - (mat[1][0] * mat[0][1]);

	}

	static double MatSarrusDeterminant(double[][] mat) {

		double determinant = 0.0;

		double mainDiag = mat[0][0] * mat[1][1] * mat[2][2];
		double firstTriag = mat[0][1] * mat[1][2] * mat[2][0];
		double secondTriag = mat[0][2] * mat[1][0] * mat[2][1];

		determinant += mainDiag + firstTriag + secondTriag;

		double secondDiag = mat[2][0] * mat[1][1] * mat[0][2];
		firstTriag = mat[2][1] * mat[1][2] * mat[0][0];
		secondTriag = mat[2][2] * mat[1][0] * mat[0][1];

		determinant += -secondDiag - firstTriag - secondTriag;

		return determinant;
	}

	static double MatLaplaceDeterminant(double[][] mat) {

		double det = 0.0;
		
		// Coluna ou linha que vamos querer
		double[] vector = new double[mat.length];
		
		int[] indicesZeros = whatLineOrColumn(mat);
		
		// indice usado no expoente de -1 da formula
		int indice = 0;
		boolean isRow = false;
		
		if(indicesZeros[0] != -1) {
			indice = indicesZeros[0];
			isRow = true;
			vector = getVector(mat, indicesZeros[0], -1);
		}
		else {
			indice = indicesZeros[1];
			vector = getVector(mat, -1, indicesZeros[1]);
		}

		
		// Iterar vetor e calcular determinante
		for(int i = 0; i < vector.length; i++) {
			
			// Elemento zero resultará em zero, podemos ignorar
			if(vector[i] == 0) {
				continue;
			}
			
			double[][] newMat = isRow ? getMatrixExcludeRowCol(mat, indice, i) : 
										getMatrixExcludeRowCol(mat, i, indice);
			
			
			det += vector[i] * Math.pow(-1, indice+i) * calculateDeterminant(newMat);
			
		}
		
		return det;
	}

	public static void main(String[] args) {
		
		// Gerar uma matriz aleatória com tamanho 4 e
		// número minimo -20 e máximo 20
		double [][]matrizA = generateRandomMatrix(10, 100);
		
		
		printMatrix(matrizA);
		
		// Verificar se é uma matriz válida para calcular determinante
		if(!validateMatrix(matrizA)) {
			return;
		}
		
		double det = calculateDeterminant(matrizA);
		
		System.out.printf("O determinante é: %.2f\n", det);

	}

	static double calculateDeterminant(double[][] mat) {

		double det = 0.0;

		// 2x2
		if(mat.length == 2) {
			det = Mat2x2Determinant(mat);
		}
		// 3x3
		else if(mat.length == 3) {
			det = MatSarrusDeterminant(mat);
		}
		// 4x4 ou superior
		else {
			det = MatLaplaceDeterminant(mat);
		}

		return det;
	}

	static double[][] getMatrixExcludeRowCol(double[][] mat, int row, int col) {
		/*
		 * Retorna uma matriz nova, onde a linha e a coluna passadas não estarão presentes
		 */
		
		double[][] newMat = new double[mat.length-1][mat.length-1];
		
		// Como vamos passar uma linha e coluna da matriz original
		// precisamos ter contadores específicao para a nova matriz
		int matRow = 0;
		int matCol = 0;
		
		for(int i = 0; i < mat.length; i++) {
			
			if(i == row) {
				continue;
			}
			
			for(int j = 0; j < mat[i].length; j++) {
				
				if(j == col) {
					continue;
				}
				
				newMat[matRow][matCol] = mat[i][j];
				
				matCol++;
			}
			
			matCol = 0;
			matRow++;
		}
		
		return newMat;
		
	}
	
	static double[] getVector(double[][] mat, int row, int col) {
		
		double[] vector = new double[mat.length];
		
		if(row != -1) {
			
			for(int j = 0; j < mat.length; j++) {
				vector[j] = mat[row][j];
			}
			
		}
		else {
			for(int i = 0; i < mat.length; i++) {
				vector[i] = mat[i][col];
			}
		}
		
		return vector;
	}
	
	static int[] whatLineOrColumn(double[][] mat){
		/*
		 * Escolhe qual linha ou coluna com mais zeros (evita cálculos)
		 * Retorna no índice 0 -> índice da linha com mais zeros
		 * Retorna no índice 1 -> índice da coluna com mais zeros
		 */
		
		int[] indiceZeros = {-1, -1};
		
		int[] indiceLineZeros = mostZerosOnLine(mat);
		int[] indiceColumnZeros = mostZerosOnColumn(mat);
		
		if(indiceLineZeros[1] >= indiceColumnZeros[1]) {
			indiceZeros[0] = indiceLineZeros[0];
		}
		else {
			indiceZeros[1] = indiceColumnZeros[0];
		}
		
		return indiceZeros;
	}

	static boolean validateMatrix(double[][] mat) {

		// Vericar se temos colunas inconstantes
		if(!validateColumns(mat)) {
			System.out.println("Matriz A tem um número de colunas inconstante.");
			return false;
		}

		// Verificar se é quadrada
		else if(!isSquare(mat)) {
			System.out.println("Matriz A não é quadrada.");
			return false;
		}

		return true;

	}

	static boolean validateColumns(double[][] mat) {
		/* Verifica se temos sempre o mesmo número de colunas */

		// Tamanho da primeira linha
		int numCols = mat[0].length;

		for(int i = 1; i < mat.length; i++) { 

			if(mat[i].length != numCols) {
				return false;
			}

		}

		return true;

	}

	static boolean isSquare(double[][] mat) {

		return mat.length == mat[0].length;

	}

	static int[] mostZerosOnLine(double[][] mat){
		/*
		 * Retorna no índice 0 -> linha com mais zeros
		 * Retorna no índice 1 -> número de zeros
		 */

		int[] indicesZeros = new int[2];
		
		// Itera linhas e colunas da matriz
		for(int i = 0; i < mat.length; i++) {

			int contLineZeros = 0;

			for(int j = 0; j < mat.length; j++) {

				if(mat[i][j] == 0) {
					contLineZeros++;
				}
			}

			if(contLineZeros > indicesZeros[1]) {
				indicesZeros[1] = contLineZeros;
				indicesZeros[0] = i;
			}

		}
		
		return indicesZeros;
	}
	
	static int[] mostZerosOnColumn(double[][] mat){
		/*
		 * Retorna no índice 0 -> coluna com mais zeros
		 * Retorna no índice 1 -> número de zeros
		 */

		int[] indicesZeros = new int[2];
		
		// Itera linhas e colunas da matriz
		for(int j = 0; j < mat.length; j++) {

			int contColumnZeros = 0;

			for(int i = 0; i < mat.length; i++) {

				if(mat[i][j] == 0) {
					contColumnZeros++;
				}
			}

			if(contColumnZeros > indicesZeros[1]) {
				indicesZeros[1] = contColumnZeros;
				indicesZeros[0] = j;
			}

		}
		
		return indicesZeros;
	}
	
	static double[][] generateRandomMatrix(int length, int maxNumber){
		
		double[][] mat = new double[length][length];
		
		for(int i = 0; i < length; i++) {
			
			for(int j = 0; j < length; j++) {
				
				double randomNumber = Math.random() * maxNumber;
				
				mat[i][j] = randomNumber <= maxNumber/2.0 ? randomNumber : -randomNumber;
				
			}
		}
		
		return mat;
		
	}
	
	static void printMatrix(double[][] mat) {
		
		for(int i = 0; i < mat.length; i++) {
			
			for(int j = 0; j < mat[i].length; j++) {
				
				System.out.printf("%8.2f ", mat[i][j]);
				
			}
			
			System.out.println();
		}
		
		System.out.println();
	}
}
