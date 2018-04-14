      program test
        use List
        implicit none
        integer :: i
        i = 5
        call pushHead(2)
        call pushTail(i)
        call pushCurrent(8)
        call next()
        call pushCurrent(1)
        call pushHead(9)
        call pushTail(7)
        print *,"The List is:"
        call printList()
        print *,"Popping head then tail:"
        print *,"Head: ",popHead()," Tail: ",popTail()
        call next() ! move current to a non tail or head ptr
        call next()
        print *,"Popping current: ",popCurrent()
        print *,"The List is:"
        call printList
        call destroy()
      end program
