cmake_minimum_required(VERSION 3.4.1)

project("native")

add_library(native-lib SHARED native-lib.cpp)

find_library(log-lib log)

target_link_libraries(native-lib ${log-lib})

# gradlew :native:externalNativeBuildDebug
# debug|armeabi-v7a :hello
# debug|armeabi-v7a :======================
# debug|armeabi-v7a :1234
set(var hello)
set(var2 1234)

message(${var})
message("======================")
message(${var2})