      ! Program to demonstrate byte allocation
      program byte
      implicit none
        real,parameter :: pi = 3.14159264 ! constant
        integer(kind=16) :: extremeLong ! 16 bytes
        complex :: z
        character(len=20) :: string
        print *,huge(extremeLong)
        z = (3.0,-5.0)
        print *,z
        string = "Hello, World!"
        print *,string
        print *,pi*z
      end program byte
