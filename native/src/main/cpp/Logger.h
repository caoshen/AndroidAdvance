//
// Created by caoshen on 2022/3/30.
//

#ifndef ANDROIDADVANCE_LOGGER_H
#define ANDROIDADVANCE_LOGGER_H

#include <android/log.h>

#define LOGGER(fmt, ...) __android_log_print(ANDROID_LOG_ERROR, "RAPHAEL", fmt, ##__VA_ARGS__)
#endif //ANDROIDADVANCE_LOGGER_H
