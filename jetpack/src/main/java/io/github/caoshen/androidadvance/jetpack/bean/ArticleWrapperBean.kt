package io.github.caoshen.androidadvance.jetpack.bean

class ArticleWrapperBean(bean: WxArticleBean) : SdkBean<WxArticleBean>(bean) {

    val showName = "Name:${bean.name}"

    val isShow: String = if (bean.visible == 1) {
        "show"
    } else {
        "hide"
    }

    override fun toString(): String {
        return "ArticleWrapperBean(showName='$showName', isShow='$isShow')"
    }

}