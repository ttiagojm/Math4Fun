include("laff_dot.jl")

laff_norm2(x::T) where T <: Matrix{Number} = sqrt(laff_dot(x,x))

## Testar ##
x = Matrix([5,-2], 1, 2)
laff_norm2(x)