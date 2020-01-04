<#import "../parts/common.ftlh" as c>
<#import "../parts/login.ftlh" as l>

<@c.page >
    <ul class="nav justify-content-end">
        <li class="nav-item m-2">
            <form class="form-inline m-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Найти" aria-label="Search">
                <button class="btn btn-outline-success my-sm-0" type="submit">Найти</button>
            </form>
        </li>
        <li class="nav-item m-2">
            <a href="/addVacancy"  class="btn btn-primary ">Добавить вакансию</a>
        </li>
    </ul>
     <@c.pagetitle "Список вакансий"/>
        <#list vacanciesList as vacancy>
            <div class="card m-2">
                <div class="card-body m-2">
                    <h5 class="card-title">${vacancy.company}</h5>
                    <h6 class="card-title">${vacancy.position}</h6>
                    <p class="card-text">${vacancy.vacancyText}</p>
                    <a href="/vacancy/${vacancy.id}" class="btn btn-primary">Подробнее</a>
                </div>
            </div>
        </#list>
</@c.page>