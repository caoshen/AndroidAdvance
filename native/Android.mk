# 当前目录
LOCAL_PATH := $(call my-dir)

# 清除全局变量
include $(CLEAR_VARS)

# so包的名称
LOCAL_MODULE := native

# 源文件
LOCAL_SRC_FILES := src/main/cpp/native-lib.cpp

# 此变量列出了在构建共享库或可执行文件时使用的额外链接器标记。利用此变量，您可使用 -l 前缀传递特定系统库的名称。例如，以下示例指示链接器生成在加载时链接到 /system/lib/libz.so 的模块：
# LOCAL_LDLIBS := -lz
LOCAL_LDLiBS := -L$(SYSROOT)/usr/lib -llog

# 构建共享库，当然还有其他几种方式，如可执行的库，Android需要的就是共享库
include $(BUILD_SHARED_LIBRARY)
