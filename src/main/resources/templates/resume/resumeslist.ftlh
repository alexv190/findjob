<#import "../parts/common.ftlh" as c>
<#import "../parts/login.ftlh" as l>

<@c.page>
    <ul class="nav justify-content-end">
        <li class="nav-item m-2">
            <form method="get" class="form-inline m-2 my-lg-0">
                <input class="form-control mr-sm-2" name="search"  type="search" placeholder="Найти" aria-label="Search" value="${search!}">
                <button class="btn btn-outline-success my-sm-0" type="submit">Найти</button>
            </form>
        </li>
        <li class="nav-item m-2">
            <a href="/addResume"  class="btn btn-primary ">Добавить резюме</a>
        </li>
    </ul>
    <@c.pagetitle  "Ищут работу"/>
        <#list resumesList as resume>
            <div class="card m-2">
                <div class="card-body row m-2">
                    <div class="col-sm-10">
                        <a href="/resume/${resume.id}" class="card-link"><h5 class="card-title">${resume.position}</h5></a>
                        ${resume.fio}
                        <p class="card-text">Зарплата: ${(resume.salary)!"-"}</p>
                        <p class="card-text">${resume.resumeText}</p>
                    </div>
                    <div class="col-sm-2">
                        <img src="/static/person.png" style="max-height: 80px;"/>
                    </div>
                </div>
            </div>
        </#list>
</@c.page>