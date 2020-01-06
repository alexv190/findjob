<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page "<script src=\"https://www.google.com/recaptcha/api.js\" async defer></script>">
    <@c.pagetitle "Регистрация"/>
    <@l.loginform  "/registration" true></@l.loginform>
</@c.page>