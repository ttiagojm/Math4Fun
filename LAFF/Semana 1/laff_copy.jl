include("helpers.jl")

function laff_copy(x::T, y::T) where T <: Matrix{Number}
    
    my, ny = verifydimensions(x, y)
    
    # Converter x para o formato de y
    x = reshape(x, my, ny)

    # Copiar x para y
    y = copy(x)

    y

end

## Testar ##

## Usaremos matrizes, pois não há propriamente vetores linha e coluna
## em Julia
x = Matrix([1,2,3], 3, 1)
y = Matrix([4,5,6], 1, 3)

y_out = laff_copy(x, y)