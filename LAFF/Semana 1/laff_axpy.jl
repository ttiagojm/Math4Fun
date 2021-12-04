include("helpers.jl")

function laff_axpy(alpha::Number, x::T, y::T) where T <: Matrix{Number}
    
    my, ny = verifydimensions(x, y)
    
    # Redimensionar o x para o formato de y
    x = reshape(x, my, ny)

    x * alpha .+ y
end


## Testar ##
x = Matrix([1,2,3], 3, 1)
y = Matrix([4,5,6], 1, 3)

laff_axpy(0.5, x, y)