      ! shows switch statements and Strings
      program select
        implicit none
        integer :: i
        character(len=40) :: str
        print *,"Input your marks: "
        read *,i
        select case (i)
          case (91:100)
            print *,"Excellent!"
          case (81:90)
            print *,"Good Job"
          case (61:80)
            print *,"Not bad"
          case (41:60)
            print *,"Okay"
          case (:40)
            print *,"Better luck next time"
        end select
        str = "better go on"
        str = "You "//str
        print *,str
        print *,"go is found at ",index(str,"go")
      end program
