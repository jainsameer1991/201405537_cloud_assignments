section .text
	global main
	extern printf
main:
mov eax,5
mov ebx,3
add eax,ebx
push eax
push message
call printf
add esp,8
ret
