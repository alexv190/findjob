<#import "../parts/common.ftlh" as c>

<@c.page >
    <@c.pagetitle "Резюме ${resume.fio}"/>
    <div>
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">Должность: ${resume.position}</h5>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Зарплата: ${resume.salary}</li>
                </ul>
                <p class="card-text">Резюме: ${resume.resumeText}</p>
            </div>

        </div>
    </div>

    <a href="/user/${resume.id}/edit">Редактировать</a>
</@c.page>