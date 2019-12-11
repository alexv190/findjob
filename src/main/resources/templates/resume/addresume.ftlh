<#import "../parts/common.ftlh" as c>
<#import "../parts/login.ftlh" as l>

<@c.page>
    <@c.pagetitle "Добавить резюме" />
<form method="post" action="addResume">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Должность:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control"  name="position" placeholder="Должность">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">ФИО:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control"  name="fio" placeholder="ФИО">
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Резюме:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" style="height: 340px;" name="resumeText">
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

