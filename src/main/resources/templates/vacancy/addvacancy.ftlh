<#import "../parts/common.ftlh" as c>
<#import "../parts/login.ftlh" as l>

<@c.page>
    <@c.pagetitle "Добавить вакансию" />
<form method="post" action="addVacancy">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Компания:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control"  name="company" placeholder="Компания">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Должность:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control"  name="position" placeholder="Должность">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Вакансия:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control"  name="vacancyText" placeholder="Вакансия" style="height: 340px;">
        </div>
    </div>
    <div class="form-group row">
      <button type="submit" class="btn btn-primary">Добавить</button>
    </div>
    <@l._csrffield />
</form>
</@c.page>

