# A simple farenheit conversion program

if(interactive()) {
    main()
}

faren.to.cel <- function(vec) {  ## Return a farenheit vector from a vector containing celcius values
    for(i in 1:length(vec)) {
        vec[i] <- (9 / 5) * vec[i] + 32
    }
    vec
}

main <- function() {  ## Accept interactive input to perform the conversion
    start <- readline("Enter a number to start from: ")
    end <- readline("Enter a number to end at: ")
    vec <- start:end
    print(cbind(matrix(vec, length(vec), 1, TRUE), faren.to.cel(vec)))
}
