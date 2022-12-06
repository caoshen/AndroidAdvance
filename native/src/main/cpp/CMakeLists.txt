cmake_minimum_required(VERSION 3.4.1)

project("native")

# https://github.com/bytedance/bhook/blob/main/README.zh-CN.md
# bhook
find_package(bytehook REQUIRED CONFIG)

add_library(native-lib SHARED native-lib.cpp)
add_library(native-catcher SHARED native-catcher.cpp)

find_library(log-lib log)

# https://blog.csdn.net/weixin_37625173/article/details/116850396
target_link_libraries(native-lib ${log-lib})
target_link_libraries(native-catcher ${log-lib})
target_link_libraries(native-lib bytehook::bytehook)

if (USEASAN)
    set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fsanitize=address -fno-omit-frame-pointer")
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fsanitize=address -fno-omit-frame-pointer")
    set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -fsanitize=address")
    set(CMAKE_STATIC_LINKER_FLAGS "${CMAKE_STATIC_LINKER_FLAGS} -fsanitize=address")
endif (USEASAN)

# gradlew :native:externalNativeBuildDebug
# debug|armeabi-v7a :hello
# debug|armeabi-v7a :======================
# debug|armeabi-v7a :1234
set(var hello)
set(var2 1234)

message(${var})
message("======================")
message(${var2})