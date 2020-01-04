<#import "../parts/common.ftlh" as c>
<#import "../parts/login.ftlh" as l>

<@c.page >
    <@c.pagetitle "Профиль пользователя ${user.username}"/>
    <div class="form-group row">
        <label for="inputEmail" class="col-sm-2 col-form-label">E-mail:</label>
        <div class="col-sm-10">
            <input type="email" readonly class="form-control" name="email" id="inputEmail" value="some@some.org">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Роль:</label>
    <div class="col-sm-10">
        ${user.getUserRoles()}
    </div>
    </div>
    <a href="/user/${user.id}/edit">Редактировать</a>
</@c.page>