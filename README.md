# security
 通用权限系统

关于SpringSecurity
我们只需要重点关注两个过滤器即可：
    登录认证：usernamePasswordAuthenticationFilter
    权限授权：FilterSecurityInterceptor
说明：springSecurity 的核心逻辑全在这一套过滤器中，过滤器里会调用各种组件完成功能，掌握了这些过滤器
和组件你就掌握了springSecurity！ 这个框架的使用方式就是对这些过滤器和组件进行扩展。![](../../../Downloads/security/笔记/images/6.权限管理/image-20220620115942257.png)
