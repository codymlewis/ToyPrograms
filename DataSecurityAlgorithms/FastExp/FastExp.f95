      ! FastExp.f95
      ! Author: Cody Lewis
      ! Date: 31-MAR-2018
      ! Description:
      ! A program to find a modular exponent in O(logn) time
      program FastExp ! Fortran has wierd standards for spacing
        integer :: base,expon,modulus
        print *,"Enter your base"
        read *, base
        print *,"Enter your exponent"
        read *,expon
        print *,"Enter your modulus"
        read *,modulus
        call fExp(base,expon,modulus)
      end program FastExp

      subroutine fExp(base,expon,modulus)
        implicit none
        integer :: base,expon,modulus,res
        if (modulus == 1) then
          print *,"Your answer is ",0
          stop
        end if
        res = 1
        base = modulo(base,modulus)
        do while (expon > 0)
          if (modulo(expon,2) == 1) then
            res = modulo((res*base),modulus)
            expon = expon - 1
          else
            base = modulo((base*base),modulus)
            expon = expon / 2
          end if
        end do
        print *,"Your answer is ",res
      end subroutine fExp
