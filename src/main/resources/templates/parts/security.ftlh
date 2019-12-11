
<#assign
    sec_known = Session.SPRING_SECURITY_CONTEXT??
>

<#if sec_known >
    <#assign
        sec_user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
        sec_name = sec_user.getUsername()
        sec_isAdmin = sec_user.isAdmin()
        sec_roles = sec_user.getUserRoles()
        >
    <#else>
    <#assign
        sec_name = "Гость"
        sec_isAdmin = false
        >
</#if>