.Ltext0:
	.globl	main
	main:
	.LFB0:
movq %rax,5
movq %rcx,3
addq %rax,%rcx
pushq %rax
pushq message
call printf
addq esp,8
ret
