package io.github.caoshen.androidadvance.kotlin.delegation

import io.github.caoshen.androidadvance.kotlin.delegation.DataRepository

class GuideViewModel {
    var isFirstLaunch by DataRepository::isFirstLaunch
}