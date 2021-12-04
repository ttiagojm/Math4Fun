include("helpers.jl")

laff_dot(x::T, y::T) where T <: Matrix{Number} = begin
    
    my, ny = verifydimensions(x, y)

    x = reshape(x, my, ny)
    
    sum(x .* y)
end

## Testar ##

x = Matrix([1,2,3], 1, 3)
y = Matrix([4,5,6], 3, 1)

laff_dot(x, y)