package matrizes;


/*
 * State: Em progresso
 */

public class GaussElimination {

	public static boolean validateColumns(double[][]mat) {
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

	public static void printMat(double[][] mat) {

		for(int i = 0; i < mat.length; i++) {
			for(int j = 0; j < mat[i].length; j++) {
				System.out.printf("%10.2f", mat[i][j]);
			}

			System.out.println();
		}

	}



	public static void main(String[] args) {

		double [][]matA = {

				{1,1,5,11},
				{2,1,7,15},
				{2,0,4,8}
		};


		if(!validateColumns(matA)) {
			System.out.println("Matriz A tem um número de colunas inconstante.");
		}

		normalizePivots(matA);
		
		findUpperTriMat(matA);

		printMat(matA);
	}


	public static void findUpperTriMat(double[][] mat) {

		for(int j = 0; j < mat[0].length; j++) {
			
			// Linha que será multiplicada pelo fator e somada às outras linhas
			int rowToMul = j;
			
			for(int i = j+1; i < mat.length; i++) {
				
				double factor = -mat[i][j];
				
				for(int k = 0; k < mat[i].length; k++) {
					
					mat[i][k] += factor * mat[rowToMul][k];
					
				}
				
			}
			
			// Normalizar novamente os valores
			normalizePivots(mat);
			
		}

	}

	public static void normalizePivots(double[][] mat) {

		for(int i = 0; i < mat.length; i++) {
			
			if(mat[i][i] == 0) {
				continue;
			}
			
			for(int j = 0; j < mat[i].length; j++) {

				mat[i][j] /= mat[i][i];

			}

		}

	}

}
