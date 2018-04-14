      ! A singly linked list in fortran
      module List
        implicit none
        ! node struct
        type Node
          integer :: content
          type(Node), pointer :: next => null ()
        end type Node

        ! members
        type(Node), pointer :: head
        type(Node), pointer :: tail
        type(Node), pointer :: current

        contains
          ! appending subroutines
          subroutine pushHead (newContent)
            implicit none
            integer, intent(in) :: newContent
            type(Node), pointer :: temp
            if (.not. associated(head)) then
              allocate(head)
              head%content = newContent
              tail => head
              current => head
            else
              allocate(temp)
              temp%content = newContent
              temp%next => head
              head => temp
              temp => null ()
            endif
          end subroutine pushHead

          subroutine pushTail (newContent)
            implicit none 
            integer, intent(in) :: newContent
            type(Node), pointer :: temp
            if (.not. associated(head)) then
              allocate(head)
              head%content = newContent
              tail => head
              current => head
            else
              allocate(temp)
              temp%content = newContent
              tail%next => temp
              tail => temp
              temp => null ()
            endif
          end subroutine pushTail
          
          subroutine pushCurrent (newContent)
            implicit none
            integer, intent(in) :: newContent
            type(Node), pointer :: temp
            if (.not. associated(head)) then
              allocate(head)
              head%content = newContent
              tail => head
              current => head
            else
              allocate(temp)
              temp%content = newContent
              temp%next => current%next
              current%next => temp
              if (associated(current,tail)) then
                tail => temp
              endif
              current => temp
              temp => null ()
            endif
          end subroutine pushCurrent

          ! iterating subroutine
          subroutine next ()
            implicit none
            if (associated(current)) then
              if (associated(current%next)) then
                current => current%next
              else
                current => head
              endif
            endif
          end subroutine next
          
          ! popping functions
          function popHead () result (content)
            implicit none
            integer :: content
            type(Node), pointer :: temp
            if (associated(head)) then
              content = head%content
              if (associated(head%next)) then
                temp => head%next
                if (associated(head,current)) then
                  current => temp
                endif
                deallocate(head)
                head => temp
              endif
            else
              content = 0
            endif
          end function popHead

          function popTail () result (content)
            implicit none
            integer :: content
            type(Node), pointer :: temp
            if (associated(head)) then
              if (associated(head,tail)) then
                content = popHead()
              else
                content = tail%content
                temp => head
                do while (.not. associated(temp%next,tail))
                  temp => temp%next
                end do
                if (associated(current,tail)) then
                  current => temp
                endif
                temp%next => null ()
                deallocate(tail)
                tail => temp
                temp => null ()
              endif
            else
              content = 0
            endif
          end function popTail

          function popCurrent () result (content)
            implicit none
            integer :: content
            type(Node), pointer :: temp
            if (associated(head)) then
              if (associated(head,current)) then
                content = popHead()
              else if (associated(tail,current)) then
                content = popTail()
              else
                content = current%content
                temp => head
                do while (.not. associated(temp%next,current))
                  temp => temp%next
                end do
                temp%next => current%next
                deallocate(current)
                current => temp
                temp => null ()
              endif
            else
              content = 0
            endif
          end function popCurrent
 
          ! printing function
          subroutine printList ()
            implicit none
            type(Node), pointer :: temp
            if(associated(head)) then
              print *,head%content
              temp => head%next
              do while (associated(temp))
                print *,temp%content
                temp => temp%next
              end do
            endif
          end subroutine

          ! destructor
          subroutine destroy ()
            implicit none
            if (associated(head)) then
              current => head
              do while (associated(current))
                current => head%next
                deallocate(head)
                head => current
              end do
            endif
          end subroutine destroy
      end module List
