      ! A program that takes 2 values, swaps them then adds them
      program swap
        implicit none
        integer :: a,b
        print *,"Enter a value for a"
        read *,a
        print *,"Enter a value for b"
        read *,b
        print *,"a: ",a," b: ",b," to"
        call swapInts(a,b)
        print *,"a: ",a," b: ",b," Sum: ",(a+b)
      end program swap

      ! method that swaps two integers
      subroutine swapInts(a,b)
        implicit none
        integer :: a,b
        a = a + b
        b = a - b
        a = a - b
      end subroutine swapInts
