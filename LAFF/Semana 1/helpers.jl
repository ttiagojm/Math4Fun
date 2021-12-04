## Criar uma matrix linha ou coluna
Base.:Matrix(arr::AbstractArray, m::Integer, n::Integer) = begin
    
    arr_len = length(arr)

    !(m == 1 || n == 1) && throw(DimensionMismatch("Uma das dimensões tem de ser 1"))

    !(m == arr_len || n == arr_len) && throw(DimensionMismatch("Uma das dimensões tem de ter o tamanho do vector"))
    
    matrix = zeros(Number , m, n)

    for i in 1:m
        for j in 1:n
            matrix[i,j] = m == arr_len ? arr[i] : arr[j]
        end
    end

    matrix
end

verifydimensions(x::T, y::T) where T <: Matrix{Number} = begin
    my, ny = size(y)
    mx, nx = size(x)

    # Verificar se têm o mesmo tamanho
    !(mx in (my, ny) && nx in (my, ny)) && throw(DimensionMismatch("Os vetores têm de ter o mesmo tamanho"))

    (my, ny)
end