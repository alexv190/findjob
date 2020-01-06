<#import "../parts/common.ftlh" as c>
<#import "../parts/login.ftlh" as l>

<@c.page>
    <@c.pagetitle "Добавить вакансию" />
<form method="post" action="addVacancy">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Компания:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control ${(companyError??)?string('is-invalid','')}"  name="company" placeholder="Компания" value="${(vacancy.company)!""}">
            <#if companyError??>
                <div class="invalid-feedback">
                    ${companyError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Должность:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control ${(positionError??)?string('is-invalid','')}"  name="position" placeholder="Должность" value="${(vacancy.position)!""}">
            <#if positionError??>
                <div class="invalid-feedback">
                    ${positionError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Вакансия:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control ${(vacancyTextError??)?string('is-invalid','')}"  name="vacancyText" placeholder="Вакансия" value="${(vacancy.vacancyText)!""}" style="height: 340px;">
            <#if vacancyTextError??>
                <div class="invalid-feedback">
                    ${vacancyTextError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
      <button type="submit" class="btn btn-primary">Добавить</button>
    </div>
    <@l._csrffield />
</form>
</@c.page>

