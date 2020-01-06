<#import "../parts/common.ftlh" as c>
<#import "../parts/login.ftlh" as l>

<@c.page>
    <@c.pagetitle "Добавить резюме" />
<form method="post" action="addResume">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Должность:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control ${(positionError??)?string('is-invalid','')}"  name="position" placeholder="Должность" value="${(resume.position)!""}">
            <#if positionError??>
                <div class="invalid-feedback">
                    ${positionError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">ФИО:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control ${(fioError??)?string('is-invalid','')}"  name="fio" placeholder="ФИО" value="${(resume.fio)!""}">
            <#if fioError??>
                <div class="invalid-feedback">
                    ${fioError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Резюме:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control ${(resumeTextError??)?string('is-invalid','')}" style="height: 340px;" name="resumeText" value="${(resume.resumeText)!""}">
            <#if resumeTextError??>
                <div class="invalid-feedback">
                    ${resumeTextError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Зарплата:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control"  name="salary" placeholder="Зарплата">
        </div>
    </div>
    <div class="form-group row">
      <button type="submit" class="btn btn-primary">Добавить</button>
    </div>
    <@l._csrffield />
</form>
</@c.page>

