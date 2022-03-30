adb shell setprop dalvik.vm.force-java-zygote-fork-loop true
adb shell stop
adb shell start

adb shell setprop wrap.com.example.nativelib '"LIBC_DEBUG_MALLOC_OPTIONS=backtrace\ leak_track\ fill logwrapper"'
adb shell setprop wrap.com.example.nativelib '"LIBC_DEBUG_MALLOC_OPTIONS=backtrace"'
adb shell am force-stop com.example.nativelib


adb shell stop
adb shell setprop libc.debug.malloc.program com.example.nativelib
adb shell setprop libc.debug.malloc.options '"backtrace=8 leak_track backtrace_dump_on_exit"'
adb shell start


adb shell stop
adb shell setprop libc.debug.malloc.options backtrace
adb shell setprop libc.debug.malloc.program com.example.nativelib
adb shell export LIBC_DEBUG_MALLOC_OPTIONS=backtrace
adb shell export LIBC_DEBUG_MALLOC_ENABLE=1
adb shell start
————————————————
版权声明：本文为CSDN博主「qq62」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/qq_42139383/article/details/109851811