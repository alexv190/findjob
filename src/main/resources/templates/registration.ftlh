<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <@c.pagetitle "Регистрация"/>
    <@l.loginform  "/registration" true></@l.loginform>
</@c.page>