include("helpers.jl")

function laff_scal(alpha::T, x::T) where T <: Matrix{Number}
    # Como alpha é um escalar
    # apesar de estar representado como uma matriz
    # pegamos apenas o primeiro, e único, elemento da matriz
    x .* alpha[1]

end

## Testar ##
x = Matrix([1,2,3], 1, 3)
alpha = Matrix([0.5], 1, 1)

laff_scal(alpha, x)