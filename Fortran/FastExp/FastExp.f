      ! Fast exponentiation program
      ! Author: Cody Lewis
      ! Date: 31-MAR-2018
      ! Modified: 05-APR-2018
      ! now accepts command line arguments
      program FastExp
      implicit none
        integer(kind=16) :: base,expon,modulus,fExp
        integer :: argc ! argument count
        integer :: int(3) ! for string to int conversion
        character(len=32) :: argv(3) ! argument values
        argc = 1
        call get_command_argument(argc,argv(argc))
        if(len_trim(argv(1)) == 0) then
          print *,"Enter your base number:"
          read *,base
          print *,"Enter your enponent:"
          read *,expon
          print *,"Enter your modulus:"
          read *,modulus
        else
          do argc=2,3
            call get_command_argument(argc,argv(argc))
            if(len_trim(argv(argc)) == 0) then
              print *,"Not enough arguments"
              stop
            end if
          end do
          read(argv,*) int
          base = int(1)
          expon = int(2)
          modulus = int(3)
        end if
        print *,"Your answer is ",fExp(base,expon,modulus)
      end program FastExp

      ! the fast exponentiation algorithm
      function fExp(base,expon,modulus) result (res)
        implicit none
        integer(kind=16),intent(in) :: base,expon,modulus
        integer(kind=16) :: b,e,m ! base,expon,modulus copies 
        integer(kind=16) :: res
        if (modulus == 1) then
          res = 0
        else
          res = 1
          b = modulo(base,modulus)
          e = expon
          m = modulus
          do while (e > 0)
            if (modulo(e,2) == 1) then
              res = modulo((res*b),m)
              e = e - 1
            else
              b = modulo((b*b),m)
              e = e / 2
            end if
          end do
        end if
      end function fExp
