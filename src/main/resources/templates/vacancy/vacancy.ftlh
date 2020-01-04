<#import "../parts/common.ftlh" as c>

<@c.page >
    <@c.pagetitle "Вакансия ${vacancy.position}"/>
    <div>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Компания: ${vacancy.company}</h5>
                <p class="card-text">Описание: ${vacancy.vacancyText}</p>
            </div>
        </div>
    </div>

    <a href="/vacancy/${vacancy.id}/edit">Редактировать</a>
</@c.page>