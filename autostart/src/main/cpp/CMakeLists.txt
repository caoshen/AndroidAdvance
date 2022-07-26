cmake_minimum_required(VERSION 3.4.1)

project("autostart")

add_library(autostart SHARED autostart.cpp)

find_library(log-lib log)

target_link_libraries(autostart ${log-lib})