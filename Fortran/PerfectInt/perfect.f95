      ! A program to find if specified integer is 'Perfect'
      program perfect
        integer :: n,k,sigma
        logical :: isPerfect
        print *,"Input your integer:"
        read *,n
        k = 1
        sigma = 0
        do while(sigma < n .and. k < n)
          if(modulo(n,k) == 0) then ! find if proper divisor
            sigma = sigma + k
            if(sigma == n) then
              isPerfect = .true.
            end if
            k = k + 1
          else
            k = k + 1
            continue
          end if
        end do
        if(isPerfect) then
          print *,"Your number ",n," is perfect"
        else
          print *,"Your number ",n," is not perfect"
        end if
      end program perfect
