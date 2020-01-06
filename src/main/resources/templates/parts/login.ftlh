<#import "common.ftlh" as c>
<#include "security.ftlh">


<#macro _csrffield>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
</#macro>

<#macro loginform path isRegisterForm>
    <@c.showmessageifexist/>
    <form action="${path}" method="post" class="mt-3">
        <div class="form-group row">
            <label for="inputUsername" class="col-sm-2 col-form-label">Логин:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control ${(usernameError??)?string('is-invalid','')}" name="username" id="inputUsername" value="${(user.username)!""}">
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-10">
                <input type="password" class="form-control ${(passwordError??)?string('is-invalid','')}" name="password" id="inputPassword">
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label for="passwordConfirm" class="col-sm-2 col-form-label">Повторите пароль:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control ${(passwordConfirmError??)?string('is-invalid','')}" name="passwordConfirm" id="inputPasswordConfirm">
                    <#if passwordConfirmError??>
                        <div class="invalid-feedback">
                            ${passwordConfirmError}
                        </div>
                    </#if>
                </div>
            </div>
            <div class="form-group row">
                <label for="inputEmail" class="col-sm-2 col-form-label">E-mail:</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control ${(emailError??)?string('is-invalid','')}" name="email" id="inputEmail" placeholder="some@some.org" value="${(user.email)!""}">
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
            <@registration_role_radio />
        </#if>
        <@_csrffield />
        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary"><#if isRegisterForm>Зарегистрироваться<#else>Войти</#if></button>
            </div>
        </div>
    </form>
    <#if !isRegisterForm><a href="/registration">Регистрация нового пользователя</a></#if>
</#macro>

<#macro registration_role_radio>
    <fieldset class="form-group">
        <div class="row">
            <legend class="col-form-label col-sm-2 pt-0">Роль:</legend>
            <div class="col-sm-10">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="r_role" id="r_employee" value="employee" checked>
                    <label class="form-check-label" for="r_employee">
                        Соискатель
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="r_role" id="r_employer" value="employer">
                    <label class="form-check-label" for="r_employer">
                        Работодатель
                    </label>
                </div>
            </div>
        </div>
    </fieldset>
</#macro>

<#macro logout>
    <form action="/logout" method="post" class="form-inline my-2">
        <@_csrffield />
        <button class="btn btn-primary" type="submit">Выйти</button>
    </form>
</#macro>

<#macro logoutautohide>
    <#if sec_known>
        <@logout></@logout>
    </#if>
</#macro>