<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page >
    <@c.pagetitle "Войти"/>
    <@l.loginform "/login" false></@l.loginform>
</@c.page>