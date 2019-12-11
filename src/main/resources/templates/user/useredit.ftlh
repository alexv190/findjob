<#import "../parts/common.ftlh" as c>
<#import "../parts/login.ftlh" as l>

<@c.page>
    <@c.pagetitle  "Редактировать пользователя ${user.username}"/>
    <#if message??>
        <div class="row">
            <div class="col alert alert-primary">
                <h5>${message}</h5>
            </div>
        </div>
    </#if>
    <form action="/user/update/${user.id}" method="post" class="mt-3">
        <div class="form-group row">
            <label for="inputUsername" class="col-sm-2 col-form-label">Логин:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" name="username" id="inputUsername" value="${user.username}">
            </div>
        </div>
        <div class="form-group row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" name="password" id="inputPassword" value="${user.password}">
            </div>
        </div>
        <#list roles as role>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" name="${role.name()}" ${user.roles?seq_contains(role)?string("checked","")}>
                <label class="form-check-label" for="exampleCheck1">${role.getUiName()}</label>
            </div>
        </#list>

        <@l._csrffield />
        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
        </div>
    </form>
</@c.page>