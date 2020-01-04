<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-light" style="background-color: #e3f2fd;">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="/">Домой</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/resumesList">Резюме соискателей</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="/vacanciesList">Вакансии работодателей</a>
            </li>
            <#if sec_isAdmin>
            <li class="nav-item active">
                <a class="nav-link" href="/userList">Список пользователей</a>
            </li>
            </#if>
        </ul>

        <div class="navbar-text mr-3">
            <#if sec_roles??>${sec_roles}</#if>
            <#if sec_known><a href="/user/${sec_user.id}">${sec_name}</a></#if>
        </div>
        <@l.logoutautohide />
    </div>
</nav>