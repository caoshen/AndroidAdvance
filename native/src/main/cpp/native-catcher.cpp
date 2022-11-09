//
// Created by caoshen on 2022/7/19.
//
#include <signal.h>
#include <setjmp.h>
#include <pthread.h>

/**
 * 捕捉系统崩溃信号
 */

// 定义代码跳转锚点
sigjmp_buf JUMP_ANCHOR;
volatile sig_atomic_t err_cnt = 0;

void signal() {
//    struct sigaction sa;
//    sa.sa_handler
}