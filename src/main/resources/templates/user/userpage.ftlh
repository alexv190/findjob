<#import "../parts/common.ftlh" as c>

<@c.page >
    <@c.pagetitle "Профиль пользователя ${user.username}"/>
    <a href="/user/${user.id}/edit">Редактировать</a>
</@c.page>