<#import "../parts/common.ftlh" as c>

<@c.page >
    <@c.pagetitle "Список пользователей"/>
    <#list users as user>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">${user.username}</h5>
                <a href="/user/${user.id}" class="btn btn-primary">Подробнее</a>
            </div>
            <div class="card-footer text-muted">
                Последний раз заходил 2 days ago
            </div>
        </div>
    </#list>
    <a href="/registration">Регистрация нового пользователя</a>
</@c.page>